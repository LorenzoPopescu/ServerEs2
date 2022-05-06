/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveres2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loren
 */
public class Server {
    ServerSocket ss;
    Socket sc;
    BufferedWriter bw;
    BufferedReader br;
    public Server(int porta){
        try {
            ss = new ServerSocket(porta);
            ss.setReuseAddress(true);
            System.out.println("Server in ascolto...");    
        }catch (IOException ex) {
            System.err.println("errore nella creazione del server e nell'avvio del servizio");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    public void ascolto(){
        try {
             while (true) {
                // socket object to receive incoming client
                // requests
                Socket client = ss.accept();
                // Displaying that new client is connected
                // to server
                System.out.println("New client connected" + client.getInetAddress().getHostAddress());
                // create a new thread object
                ClientHandler clientSock = new ClientHandler(client);
                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        finally {
            if (ss != null) {
                try {
                    ss.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }    
}


