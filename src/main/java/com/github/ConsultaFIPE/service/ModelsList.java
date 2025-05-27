package com.github.ConsultaFIPE.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelsList(List<CarProperty> modelos) {
}
