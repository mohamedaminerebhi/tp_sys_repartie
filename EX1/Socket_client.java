package tp_sys_repartie;

import java.io.*;
import java.net.*;
import java.util.Scanner;
class Socket_client {
public static void main(String argv[]) {
int port = 0;
String host = "";
Scanner keyb = new Scanner(System.in);
// demander le nom de domaine et le port de serveur
System.out.print("Nom du serveur : ");
host = keyb.next();
System.out.print("Port du serveur : ");
try {
port = keyb.nextInt();
} catch (NumberFormatException e) {
System.err.println("Le second paramètre n'est pas un entier.");
System.exit(-1);
}
// demander la connexion au serveur
try {
// demander l adresse du serveur
InetAddress adr = InetAddress.getByName(host);
// créer une socket client
Socket socket = new Socket(adr, port);
//initialiser le flux des messages d'entrée et sortie
ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
// envoyer un message
output.writeObject(new String("ma première socket"));
// recevoir un message
String chaine = (String) input.readObject();

System.out.println(" recu du serveur : " + chaine); } catch
(Exception e) {
System.err.println("Erreur : " + e);
}
}
}