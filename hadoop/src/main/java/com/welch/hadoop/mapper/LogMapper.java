package com.welch.hadoop.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

//import com.welch.hadoop.type.LogRecordType;

public class LogMapper extends Mapper<Object, Text, Text, IntWritable> {
	private IntWritable one = new IntWritable(1);
    private Text text = new Text();
    
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		if(line.startsWith("2017")){
			String date = line.substring(0,10);
			text.set(date);
			//context.getCounter(LogRecordType.VALID).increment(1);
			context.write(text, one);
		}else{
			//context.getCounter(LogRecordType.INVALID).increment(1);
		}
	}
}
