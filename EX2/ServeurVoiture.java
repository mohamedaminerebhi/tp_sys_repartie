package tp_sys_repartie;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServeurVoiture {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);

        // Demander le port d'écoute
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le paramètre n'est pas un entier.");
            System.exit(-1);
        }

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Recevoir l'objet Voiture du client
            voiture voitureDuClient = (voiture) input.readObject();

            // donner la quantite du carburant a verser 
            int quantite=keyb.nextInt();
            // Modifier la quantité de carburant de la voiture
            voitureDuClient.setCarburant(quantite);

            // Envoyer la voiture modifiée au client
            output.writeObject(voitureDuClient);
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
