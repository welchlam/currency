package com.welch.hadoop.main;

import com.welch.hadoop.type.LogRecordType;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class LogProcessStatistic extends Configured implements Tool{
	
	public int run(String[] args) throws Exception {
		if(args.length != 1){
			System.err.println("please input jobId");
			return -1;
		}
		
		String jobId = args[0];
		Cluster cluster = new Cluster(getConf());
		Job job = cluster.getJob(JobID.forName(jobId));
		if (job == null) {
			System.err.printf("No job with ID %s found.\n", jobId);
			return -1;
		}
		if (!job.isComplete()) {
			System.err.printf("Job %s is not complete.\n", jobId);
			return -1;
		}
		
		Counters counters = job.getCounters();
		long validRecord = counters.findCounter(LogRecordType.VALID).getValue();
		long invalidRecord = counters.findCounter(LogRecordType.INVALID).getValue();
		long total = counters.findCounter(TaskCounter.MAP_INPUT_RECORDS).getValue();
		
		System.out.printf("Total processed records %d, valid records %d, invalid records %d, percentage processed %.2f%%\n", total, validRecord, invalidRecord, 100.0 * validRecord / total);
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception{
		int exitCode = ToolRunner.run(new LogProcessStatistic(), args);
		System.exit(exitCode);
	}
}
