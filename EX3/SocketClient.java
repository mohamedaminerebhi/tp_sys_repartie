package tp_sys_repartie;

import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
public class SocketClient { 
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
 System.err.println("Le second paramètre n'est pas un entier.");  System.exit(-1); 
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
// Demander les informations sur la personne
System.out.print("Entrez l'âge de la personne : ");
int age = keyb.nextInt();
keyb.nextLine(); // Consommer la nouvelle ligne
System.out.print("Entrez le nom de la personne : ");
String nom = keyb.nextLine();
//Créer un objet Personne
Personne personne = new Personne(age, nom, 0); // L'ID sera fixé par le serveur
// Envoyer la personne au serveur
output.writeObject(personne);
output.flush();
// Recevoir l'identifiant du client du serveur
int clientId = input.readInt();
System.out.println("Identifiant du client : " + clientId);  }
catch (Exception e) { 
 System.err.println("Erreur : " + e); 
 } 
 } 
}

