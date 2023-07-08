package org.zmz.sb3.nettychat.ch3.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
        Channel channel = ctx.channel();
        if (msg instanceof HttpRequest) {
            LOG.info("{}", channel.remoteAddress());
            ByteBuf content = Unpooled.copiedBuffer(">>> Hello Netty >>>", CharsetUtil.UTF_8);
            FullHttpResponse httpResponse =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            HttpHeaders headers = httpResponse.headers();
            headers.set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            headers.set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            ctx.writeAndFlush(httpResponse);
        }

    }
}
