package workspare.sparktesst.src.main.java.flink;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

import java.util.ArrayList;
import java.util.List;

public class FlinkMapDemo {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> source = env.fromElements("I love beijing", "I love china", "Beijing is the captial of china");

        source.map(new RichMapFunction<String, List<String>>() {

            @Override
            public List<String> map(String value) throws Exception {

                String[] s = value.split(" ");
                ArrayList<String> result = new ArrayList<>();
                for(String ele:s){
                    result.add("单词是：" + ele);
                }
                return result;
            }
        }).print();

    }

}