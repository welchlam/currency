package com.welch.hadoop.main;

import javax.annotation.PostConstruct;

public class Test {
	
	@PostConstruct
	public void abc(){
		System.out.println("abc");
	}
	
	public static void main(String[] args){
		new Test();
	}
}
