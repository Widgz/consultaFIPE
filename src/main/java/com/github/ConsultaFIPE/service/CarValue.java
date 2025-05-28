package com.github.ConsultaFIPE.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CarValue(String Modelo, String Marca, String AnoModelo, String Valor, String Combustivel) {
}
