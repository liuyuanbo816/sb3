package org.zmz.flink.basic.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class WcStreamDemo {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 从文件中读取数据
        DataStreamSource<String> source = env.readTextFile("flink/input/wc.txt");
        // 切分 转换
        SingleOutputStreamOperator<Tuple2<String, Integer>> streamOperator = source.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> out) {
                String[] items = s.split(" ");

                for (String item : items) {
                    Tuple2<String, Integer> tuple2 = Tuple2.of(item, 1);
                    out.collect(tuple2);
                }
            }
        });

        // 按照 word 分组 聚合
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = streamOperator.keyBy(new KeySelector<Tuple2<String, Integer>, Object>() {

            @Override
            public Object getKey(Tuple2<String, Integer> value) {
                return value.f0;
            }
        }).sum(1);

        // 输出
        sum.print();

        // 启动
        env.execute();
    }
}
