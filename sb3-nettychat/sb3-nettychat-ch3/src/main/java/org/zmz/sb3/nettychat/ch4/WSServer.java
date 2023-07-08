package org.zmz.sb3.nettychat.ch4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class WSServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            ChannelFuture channelFuture = serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast("httpServerCodec", new HttpServerCodec())
                                    .addLast("chunkedWriteHandler", new ChunkedWriteHandler())
                                    .addLast("httpObjectAggregator", new HttpObjectAggregator(Integer.MAX_VALUE))
                                    .addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/ws"))
                                    .addLast("customWsHandler", new SimpleChannelInboundHandler<TextWebSocketFrame>() {
                                        private static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
                                            String content = msg.text();
                                            log.info("接收到消息: {}", content);
                                            clients.writeAndFlush(new TextWebSocketFrame("[服务器]在 "
                                                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                                                    + " 接收到消息: " + content));
                                        }

                                        @Override
                                        public void handlerAdded(ChannelHandlerContext ctx) {
                                            clients.add(ctx.channel());
                                        }

                                        @Override
                                        public void handlerRemoved(ChannelHandlerContext ctx) {
                                            String shortText = ctx.channel().id().asShortText();
                                            String longText = ctx.channel().id().asLongText();
                                            log.info("客户端断开, channel 长Id : {} , 短Id: {}", longText, shortText);
                                        }
                                    });
                        }
                    }).bind(9081).sync();
            log.info("NettyServer已启动,监听端口9081");
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
