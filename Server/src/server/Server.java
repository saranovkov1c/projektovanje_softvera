package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import niti.ObradaKlijentskihZahteva;

/**
 * 
 * 
 * @author SARA
 */
public class Server extends Thread {

    boolean kraj = false;
    ServerSocket serverSocket;

    List<ObradaKlijentskihZahteva> klijenti;

    public Server() {
        klijenti = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            while (!kraj) {
                Socket s = serverSocket.accept();
                System.out.println("Klijent je povezan");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                klijenti.add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            System.out.println("Server je ugasen!");
        }
    }

    public void zaustaviServer() throws IOException {
        kraj = true;
        try {
            serverSocket.close();
            for (ObradaKlijentskihZahteva okz : klijenti) {
                okz.prekini();
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
