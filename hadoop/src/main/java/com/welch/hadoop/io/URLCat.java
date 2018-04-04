package com.welch.hadoop.io;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;

import java.io.InputStream;
import java.net.URL;

public class URLCat {
	static{
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}
	
	public static void main(String[] args) throws Exception{
		String url = "hdfs://GBL06063.systems.uk.hsbc:9000/user/welch/output4/part-r-00000";
		InputStream in = null;
		try{
			in = new URL(url).openStream();
			IOUtils.copy(in, System.out);
		}finally{
			IOUtils.closeQuietly(in);
		}
	}
}
