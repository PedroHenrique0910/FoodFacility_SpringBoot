package com.example.FoodFacilitySpring.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader implements Comparable<Reader> {

    private String nome;
    private String tipo;
    private String endereco;
    private String tipoCardapio;
    private Double latitude;
    private Double longitude;
    private Double distancia; // Adição do campo de distância

    public Reader() {
    }

    public Reader(String nome, String tipo, String endereco, String tipoCardapio, Double latitude, Double longitude) {
        this.nome = nome;
        this.tipo = tipo;
        this.endereco = endereco;
        this.tipoCardapio = tipoCardapio;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoCardapio() {
        return tipoCardapio;
    }

    public void setTipoCardapio(String tipoCardapio) {
        this.tipoCardapio = tipoCardapio;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        String distanciaFormatada;
        if (distancia >= 1000) {
            double distanciaEmKm = distancia / 1000.0;
            distanciaFormatada = String.format("%.2f", distanciaEmKm) + " km";
        } else {
            distanciaFormatada = String.format("%.2f", distancia) + " metros";
        }

        return  "Distância: " + distanciaFormatada + "\n";
    }



    // Implementação do método compareTo para a ordenação
    @Override
    public int compareTo(Reader outroReader) {
        return this.distancia.compareTo(outroReader.getDistancia());
    }
}