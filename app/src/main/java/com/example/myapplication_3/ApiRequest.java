package com.example.myapplication_3;

// import module

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class ApiRequest {
    // implementer les méthodes
    private String apiKey;
    public void Apirequest(String apiKey) {
        this.apiKey = apiKey;
    }

    public String generateText(String prompt) throws IOException {
        // url de l'api
        URL url = new URL("");

        // Création de la connexion à l'URL
        URLConnection connection = url.openConnection();

        // Configuration du header
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);

        // Création des données du POST
        String data = "{\n" +
                "  \"prompt\": \"" + prompt + "\",\n" +
                "  \"max_tokens\": 32\n" +
                "}";

        // Envoi des données de la requête POST
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(data.getBytes());

        // Réponse de l'API GPT-3
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();

        return stringBuilder.toString();
    }
}
