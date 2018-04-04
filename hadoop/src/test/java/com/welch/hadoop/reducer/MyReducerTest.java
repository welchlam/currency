package com.welch.hadoop.reducer;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.*;

public class MyReducerTest {
	
	@Test
	public void returnSumTest(){
		try {
			new ReduceDriver <Text,IntWritable,Text,IntWritable>()
			.withReducer(new LogReducer())
			.withInput(new Text("2017-02-08"), Arrays.asList(new IntWritable(3), new IntWritable(5)))
			//.withInputKey(new Text("2017-02-08"))
			//.withInputValues(Arrays.asList(new IntWritable(3), new IntWritable(5)))
			.withOutput(new Text("2017-02-08"), new IntWritable(8))
			.runTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
