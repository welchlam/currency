package com.welch.hadoop.mapper;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.*;

public class MyMapperTest {
	
	@Test
	public void processValidRecord(){
		Text value = new Text("2017-02-08 14:15:47,933 INFO  [http-bio-8080-exec-10] window.LoginWindow (LoginWindow.java:100) - Validating User login 43559634");
		try {
			new MapDriver<Object, Text, Text, IntWritable>()
			.withMapper(new LogMapper())
			.withInput(new Text("1"), value)
			.withOutput(new Text("2017-02-08"), new IntWritable(1))
			.runTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
