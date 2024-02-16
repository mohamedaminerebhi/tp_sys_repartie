package tp_sys_repartie;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    private static int clientIdCounter = 0;

    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);

        // le serveur attend les connexions
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le paramètre n'est pas un entier.");
            System.err.println("Usage : java ServeurUDP port-serveur");
            System.exit(-1);
        }

        try {
            // creation d une instance de socket serveur
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur démarré sur le port " + port);
            while (true) {
                // accepter les connexions
                Socket socket = serverSocket.accept();
                System.out.println("Nouvelle connexion acceptée.");
                // initialiser le flux des message d'entrée et de sortie
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                // Recevoir les données du client
                Personne personne = (Personne) input.readObject();
                // Générer un nouvel identifiant de client
                int clientId = generateClientId();
                // Ajouter l'identifiant à la personne
                personne.setId(clientId);
                // Envoyer l'identifiant au client
                output.writeInt(clientId);
                output.flush();
                // afficher l adresse et le port du client qui a envoyé le message
                System.out.println("Message reçu de : " + socket.getInetAddress() + ":" + socket.getPort());
            }
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }

    private static synchronized int generateClientId() {
        return ++clientIdCounter;
    }
}

