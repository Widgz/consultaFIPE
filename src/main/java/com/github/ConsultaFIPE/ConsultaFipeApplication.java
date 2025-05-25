package com.github.ConsultaFIPE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ConsultaFIPE.service.APIService;
import com.github.ConsultaFIPE.service.CarBrand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ConsultaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var apiService = new APIService();
		apiService.setVehicleType();
		ObjectMapper mapper = new ObjectMapper();
		String json = apiService.APIConsulting(apiService.getCoreAdress());
		List<CarBrand> carBrands = Arrays.asList(mapper.readValue(json, CarBrand[].class));
		for (int i = 0; i < carBrands.size(); i++) {
			System.out.println("Código: " + carBrands.get(i).codigo() +
					" Marca: " + carBrands.get(i).nome());
		}
		apiService.setCarModel();
		System.out.println(apiService.APIConsulting(apiService.getCoreAdress()));
	}
}
