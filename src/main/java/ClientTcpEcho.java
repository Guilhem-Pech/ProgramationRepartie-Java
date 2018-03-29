import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientTcpEcho {
    private String ip;
    private int port;

    public ClientTcpEcho(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void lancer() throws IOException {
        System.out.println("Connection au serveur ... ");
        String line;
        Socket sockClient = new Socket(ip,port);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockClient.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(sockClient.getInputStream()));
        System.out.println("Serveur connecté!");
        Scanner clavier = new Scanner(System.in);
        String toSend;
        String bufReceived;
        while (true) {
            System.out.print("Entrez un bidule au clavier: ");
            toSend = clavier.nextLine();
            if (toSend.equals("quit"))
                break;
            else {
                bw.write(toSend);
                bw.newLine();
                bw.flush();
                bufReceived = br.readLine();
                System.out.println(bufReceived);
            }
        }
        System.out.println("Déconnexion ... ");
        br.close();
        bw.close();
        sockClient.close();

    }
}
