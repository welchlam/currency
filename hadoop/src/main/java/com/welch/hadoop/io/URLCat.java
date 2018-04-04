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
		String url = "";
		InputStream in = null;
		try{
			in = new URL(url).openStream();
			IOUtils.copy(in, System.out);
		}finally{
			IOUtils.closeQuietly(in);
		}
	}
}
