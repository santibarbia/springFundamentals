package com.tipre.springboot.batch.config;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.tipre.springboot.batch.model.Product;
import com.zaxxer.hikari.util.DriverDataSource;

@Configuration
public class BatchConfig {
	
	@Autowired
	private StepBuilderFactory sbf;
	
	@Autowired
	private JobBuilderFactory jbf;
	
	@Bean
	public Step step() throws MalformedURLException {
		return sbf.get("s1")
				.<Product, Product>chunk(3)
				.reader(reader())
				.processor(procesor())
				.writer(writer())
				.build();
	}
	
	@Bean
	public Job job() throws MalformedURLException {
		return jbf.get("j1")
				.incrementer(new RunIdIncrementer())
				.start(step())
				.build();
		
	}
	
	@Bean
	public ItemReader<Product> reader() throws MalformedURLException{
		FlatFileItemReader<Product> reader = new FlatFileItemReader<>();//Se encarga de leer el archivo
		reader.setResource(new UrlResource("file:C:\\Users\\Win 10\\Desktop\\Proyectos-Freelance\\Spring Cursos\\fundamentalsSpring\\batchcsvtodb\\src\\main\\java\\com\\tipre\\springboot\\batch\\model\\products.csv"));
		
		DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(); 
		lineTokenizer.setNames("id","name","description","price"); //Se encarga de separar y asignar cada uno de los items separados por comas
		BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>(); 
		
		fieldSetMapper.setTargetType(Product.class); // Se encarga de asociar cada linea y campo del archivo separados por coma, en un tipo de clase.
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		reader.setLineMapper(lineMapper);
		
		return reader;
		
	}
	
	
	@Bean
	public ItemProcessor<Product, Product> procesor(){
		
		
		return (producto)->{
			producto.setPrice(producto.getPrice()-producto.getPrice()*10/100);
			return producto;
		};
		
		
	}
	
	@Bean
	public ItemWriter<Product>writer(){
		
		JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
		writer.setDataSource(dataSource());
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
		writer.setSql("INSERT INTO product (ID,NAME,DESCRIPTION,PRICE) VALUES (:id,:name,:description,:price)");
		
		
		return writer;
		
	}
	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		
		return dataSource;
	}

}
