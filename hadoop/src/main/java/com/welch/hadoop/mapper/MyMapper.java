package com.welch.hadoop.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMapper extends Mapper<Object, Text, Text, IntWritable>{
	
	private IntWritable one = new IntWritable(1);
    private Text text = new Text();
    
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] words = line.split(" ");
		for(String word: words){
			if(word.startsWith("a") || word.startsWith("A")){
				text.set(word);
				context.write(text, one);
			}
		}
	}
}
