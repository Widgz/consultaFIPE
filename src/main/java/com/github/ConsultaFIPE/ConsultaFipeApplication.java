package com.github.ConsultaFIPE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ConsultaFIPE.service.APIService;
import com.github.ConsultaFIPE.service.CarProperty;
import com.github.ConsultaFIPE.service.ModelsList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class ConsultaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		var apiService = new APIService();
		apiService.setVehicleType();
		ObjectMapper mapper = new ObjectMapper();
		String carBrandsJson = apiService.APIConsulting(apiService.getBrandURL());
		List<CarProperty> carBrands = Arrays.asList(mapper.readValue(carBrandsJson, CarProperty[].class));
		for (int i = 0; i < carBrands.size(); i++) {
			System.out.println("Cód: " + carBrands.get(i).codigo() +
					" Marca: " + carBrands.get(i).nome());
		}

		apiService.setCarModel();
		String carModelsJson = apiService.APIConsulting(apiService.getModelsURL());
		ModelsList carModels = mapper.readValue(carModelsJson, ModelsList.class);
		carModels.modelos().forEach(m -> System.out.println("Cód: " + m.codigo() + "  Descrição: " + m.nome()));

		System.out.println("Por favor, digite o trecho do nome do veículo para consulta:");
		String searchedModel = sc.nextLine();

		List<CarProperty> filteredModels = carModels.modelos().stream()
				.filter(m -> m.nome().toLowerCase().contains(searchedModel.toLowerCase()))
				.collect(Collectors.toList());

		filteredModels.forEach(System.out::println);

		System.out.println("Por favor, digite o código do modelo para consultar valores:");
		String modelYear = sc.nextLine();

		apiService.setModelYear(modelYear);

		String valueJson = apiService.APIConsulting(apiService.getYearURL());

		List<CarProperty> values = Arrays.asList(mapper.readValue(valueJson, CarProperty[].class));

		values.forEach(System.out::println);
	}
}
