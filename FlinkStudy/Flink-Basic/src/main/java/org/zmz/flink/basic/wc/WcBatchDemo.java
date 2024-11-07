package org.zmz.flink.basic.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class WcBatchDemo {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // 从文件中读取数据
        DataSource<String> source = env.readTextFile("flink/input/wc.txt");
        // 切分 转换
        FlatMapOperator<String, Tuple2<String, Integer>> flatMapOperator = source.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
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
        AggregateOperator<Tuple2<String, Integer>> sum = flatMapOperator.groupBy(0).sum(1);

        // 输出
        sum.print();
    }
}
