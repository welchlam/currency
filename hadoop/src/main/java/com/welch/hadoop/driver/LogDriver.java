package com.welch.hadoop.driver;

import com.welch.hadoop.mapper.LogMapper;
import com.welch.hadoop.reducer.LogReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class LogDriver extends Configured implements Tool {

	public int run(String[] arg0) throws Exception {
		if(arg0.length !=2 ){
			System.err.printf("Usage: %s [generic options] <input> <output>\n", this.getClass().getSimpleName());
			ToolRunner.printGenericCommandUsage(System.err);
			return -1;
		}
		
		Job job = Job.getInstance(new Configuration());
		job.setJobName("Log cout by date");
		job.setJarByClass(this.getClass());
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		
		job.setMapperClass(LogMapper.class);
		job.setCombinerClass(LogReducer.class);
		job.setReducerClass(LogReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception{
		int exitCode = ToolRunner.run(new LogDriver(), args);
		System.exit(exitCode);
	}
}
