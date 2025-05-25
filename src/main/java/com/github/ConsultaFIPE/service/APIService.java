package com.github.ConsultaFIPE.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class APIService {

    private String vehicleType;
    private String coreAdress = "https://parallelum.com.br/fipe/api/v1/";
    private String carModel;
    Scanner sc = new Scanner(System.in);

    public void setVehicleType () {
        System.out.println("Selecione a opção correspondente ao tipo de veículo que deseja buscar o preço:");
        System.out.println("1- Carro\n" +
                "2- Moto\n" +
                "3- Caminhão");
        System.out.print("Opcão: ");
        int tipo = sc.nextInt();
        sc.nextLine();
        if (tipo == 1) {
            this.vehicleType = "carros";
        } else if (tipo == 2) {
            this.vehicleType = "motos";
        } else if (tipo == 3) {
            this.vehicleType = "caminhoes";
        } else {
            System.out.println("Opção inválida, por favor tente novamente.");
        }
        this.coreAdress = this.coreAdress + this.vehicleType + "/marcas";
    }

    public String getCoreAdress() {
        return coreAdress;
    }

    public String APIConsulting (String webAddress) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(webAddress))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public void setCarModel() {
        System.out.println("Por favor, digite o código referente à marca do veículo: ");
        String modelo = sc.nextLine();
        this.carModel = modelo;
        this.coreAdress = this.coreAdress + "/" + this.carModel + "/modelos";
    }
}
