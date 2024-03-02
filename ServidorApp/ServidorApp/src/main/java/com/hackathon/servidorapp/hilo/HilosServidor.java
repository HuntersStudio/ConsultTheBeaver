/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackathon.servidorapp.hilo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleja
 */
public class HilosServidor extends Thread{
    private Socket clienteSocket;

    public HilosServidor(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }
    
     @Override
    public void run() {
       
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
           
            dis = new DataInputStream(clienteSocket.getInputStream());
            dos = new DataOutputStream(clienteSocket.getOutputStream());
            System.out.println("Esperando comando");
            int mensaje = dis.readInt();
            
            switch(mensaje){
                case 1:
                    String texto = dis.readUTF();
                    dos.writeUTF(Metodos.consulta(texto));
                    break;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(HilosServidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dis.close();
                dos.close();
            } catch (IOException ex) {
                Logger.getLogger(HilosServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
