/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.hackathon.servidorapp;

import com.hackathon.servidorapp.hilo.HilosServidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author aleja
 */
public class ServidorApp {

    public static void main(String[] args) {
        final int puerto = 8888;
        
        
        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto " + puerto);

          do {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress().getHostAddress());
                //clienteSocket.setTimeout();
                
                
                // Crea un nuevo hilo para manejar al cliente
                HilosServidor clientHandler = new HilosServidor(clienteSocket);            
                clientHandler.start();
                
                
            }while( true);
          
          

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
