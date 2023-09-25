package com.inditex.testjava2020.esinditexrates;

import com.inditex.testjava2020.esinditexrates.helper.PopulateRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EsInditexRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsInditexRatesApplication.class, args);
	}

}
