package com.welch.hadoop.main;

import com.welch.hadoop.mapper.LogMapper;
import com.welch.hadoop.reducer.LogReducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class LogMain {
	public static void main(String[] args) throws Exception {
		if(args.length<2){
			System.err.println("Usage: LogMain <input path> <output path>");
			System.exit(-1);
		}
		
		Job job = Job.getInstance();
		job.setJarByClass(LogMain.class);
		job.setJobName("Log infor count by date");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(LogMapper.class);
		//job.setCombinerClass(LogReducer.class);
		job.setReducerClass(LogReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
