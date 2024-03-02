/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.servidorapp.hilo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class Metodos {

    public static String consulta(String info) {

        String jsonString = "{\"model\":\"llama2\",\"prompt\":\""+info+"\",\"stream\":false}";
        String apiUrl = "http://127.0.0.1:11434/api/generate";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            os.write(jsonString.getBytes());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parsear la respuesta y mostrar el campo "response"
                String ollamaResponse = response.toString();
                String responseObject = ollamaResponse.substring(ollamaResponse.indexOf("\"response\":") + 12,
                        ollamaResponse.indexOf("\",\"done\""));

                responseObject = responseObject.replace("\\n", "\n");

                System.out.println("Respuesta de OLLAMA: ");
                System.out.println(responseObject);

                return responseObject;
            } else {
                System.out.println("Error al enviar la solicitud. Código de respuesta: " + responseCode);

                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
