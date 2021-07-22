package com.tipre.springboot.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tipre.springboot.jobListener.MyJobListener;
import com.tipre.springboot.processor.Processor;
import com.tipre.springboot.reader.Reader;
import com.tipre.springboot.writer.Writer;

@Configuration
@EnableAutoConfiguration
public class BatchConfig {
	
	@Autowired
	private StepBuilderFactory sbf;
	@Autowired
	private JobBuilderFactory jbf;
	
	
	@Bean
	public Job job() {
		return jbf.get("Job1")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.start(step())
				.build();
	}
	
	@Bean
	public Step step() {
		
		return sbf.get("step1")
				.<String,String>chunk(2)
				.reader(reader())
				.writer(writer())
				.processor(processer())
				.build();
		
	}
	
	@Bean
	public Reader reader() {
		
		return new Reader();
	}
	
	@Bean
	public Writer writer() {
		return new Writer();
	
	}
	
	@Bean
	public Processor processer() {
		return new Processor();
		
	}
	
	@Bean
	public MyJobListener listener() {
		return new MyJobListener();
	}
	
}
