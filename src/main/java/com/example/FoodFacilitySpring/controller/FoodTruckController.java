package com.example.FoodFacilitySpring.controller;

import com.example.FoodFacilitySpring.entities.Reader;
import com.example.FoodFacilitySpring.entities.DistanceCalculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

// Definição da classe controladora = Controladores são componentes do Spring MVC que lidam com solicitações HTTP e retornam as respostas apropriadas.
@Controller
public class FoodTruckController {

    // Mapeamento para a página inicial
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    // Mapeamento para encontrar food trucks
    @GetMapping("/findFoodTrucks")
    public String findFoodTrucks(@RequestParam("latitude") double userLatitude, @RequestParam("longitude") double userLongitude, Model model) {

        Locale.setDefault(Locale.US);

        // Lista para armazenar objetos Reader
        List<Reader> list = new ArrayList<>();

        // Caminho para o arquivo CSV
        String path = "C:\\temp\\Mobile_Food_Facility_Permit.csv";

        // Variáveis para leitura do arquivo CSV
        String linha = "";
        String divisor = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // Ler a primeira linha do arquivo (cabeçalho) e descartar
            linha = br.readLine();

            // Loop para ler cada linha do arquivo CSV
            while ((linha = br.readLine()) != null) {
                // Dividir a linha em partes usando o delimitador ","
                String[] vect = linha.split(divisor);

                // Extrair informações relevantes de cada parte
                String nome = vect[1];
                String tipo = vect[2];
                String endereco = vect[5];
                String tipoCardapio = vect[11];
                Double latitude;
                Double longitude;

                try {
                    // Tentar converter a string de latitude para um valor numérico
                    latitude = Double.parseDouble(vect[14]);
                } catch (NumberFormatException e) {
                    // Se falhar, definir latitude como 0.0
                    latitude = 0.0;
                }

                try {
                    // Tentar converter a string de longitude para um valor numérico
                    longitude = Double.parseDouble(vect[15]);
                } catch (NumberFormatException e) {
                    // Se falhar, definir longitude como 0.0
                    longitude = 0.0;
                }

                // Criar um objeto Reader com as informações lidas
                Reader reader = new Reader(nome, tipo, endereco, tipoCardapio, latitude, longitude);

                // Adicionar o objeto Reader à lista
                list.add(reader);
            }

            // Calcular distâncias entre o usuário e os food trucks
            for (Reader reader : list) {
                double dist = DistanceCalculator.calculoDistancia(userLatitude, userLongitude, reader.getLatitude(), reader.getLongitude());

                // Definir a distância calculada no objeto Reader
                reader.setDistancia(dist);
            }

            // Ordenar a lista de food trucks com base na distância
            Collections.sort(list);

            // Adicionar os 5 primeiros food trucks ao modelo
            model.addAttribute("foodTrucks", list.subList(0, Math.min(5, list.size())));

        } catch (IOException e) {
            // Lidar com exceções de leitura do arquivo
            System.out.println("Error: " + e.getMessage());
        }

        // Retornar o nome da visão (view) para renderizar
        return "index";
    }
}
