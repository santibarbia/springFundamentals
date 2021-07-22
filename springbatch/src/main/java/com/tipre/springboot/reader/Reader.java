package com.tipre.springboot.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {
	
	private String[] cursos = {"Java Web Service","End to End project", "Angular"};
	private int count = 0;
	
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		System.out.println("Inside Read Method");
		
		if(count < cursos.length) {
			return cursos[count++];
		}else {
			count = 0;
		}
		
		return null;
	}
	
	

}
