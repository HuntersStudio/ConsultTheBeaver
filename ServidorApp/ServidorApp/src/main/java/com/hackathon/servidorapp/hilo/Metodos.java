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

    public static void init() {
        String texto = "Te voy a pedir informacion sobre una actividad en la naturaleza en una zona que indicare mas tarde, donde busco que me digas "
                + "lugares a los que ir clasificados en 3 tipos segun el riego de incendios de la zona sinedo 1 bajo riesgo, 2 riesgo medio y 3 alto riesgo.\n"
                + "Interesa que me digas el lugar, el tipo de actividades que puedan hacer y las que estan prohibidas en base a la epoca del año que te comunique"
                + "Por ejemplo, en verano hay zonas en las que esta prohibido hacer fuegos, busca informacion sobre eso y comunicamelas.\n"
                + "A parte de los lugares a los que ir, aconsejame sobre actitudes que hacer para prevenir incendios accidentales que pueden ocurrir en "
                + "la activad que te propondre.\n"
                + "La respuesta tiene que estar en español.\n"
                + "Si te doy informacion poco precisa o no relacionada con el tema de forma que no puedas dar una respuesta de calidad notificame la informacion"
                + " que necesitas para una respuesta de alta calidad.\n"
                + "La estructura seria la siguiente:\n"
                + "- Nivel de riesgo de incendio\n"
                + "  - Lugares a los que ir:\n"
                + "     - Informacion sobre el sitio.\n"
                + "     - Actividades que poder hacer y las que estan prohibidas.\n"
                + "- Actitudes para prevenir incendios.\n"
                + "- Despedida del usuario.\n"
                + "\n"
                + "Ahora mismo te voy a pasar la infromacion de la actividad a realiza, el lugar en el que me encuentro para que me recomiendes en base a las zonas cercana"
                + ", la epoca en la que lo voy a realizar y las personas que vamos a ir.\n"
                + "En caso de que falte algun campo indicado no muestres nada mas que un mensaje reclamando al usuario que lo introduzca en el prompt.\n";

        String jsonString = "{\"model\":\"llama2\",\"prompt\":\" " + texto + " \",\"stream\":false}";
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String consulta(String info) {

        String jsonString = "{\"model\":\"llama2\",\"prompt\":\" " + info + " \",\"stream\":false}";
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
