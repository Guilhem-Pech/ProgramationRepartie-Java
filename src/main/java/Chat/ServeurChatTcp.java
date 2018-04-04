package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServeurChatTcp {

    private int port;

    public ServeurChatTcp(int port) {
        this.port = port;
    }

    public void demarrer() throws IOException {
        Socket client;
        BufferedWriter out;
        BufferedReader in;
        Scanner entreeClavier = new Scanner(System.in);
        ServerSocket serverSocket = new ServerSocket(this.port);
        for(;;){
            client = serverSocket.accept();
            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            write("Vous êtes connecté !",out);

            String input;
            while ((input=in.readLine()) != null && !input.equals("bye")){
                System.out.println("Message reçu: "+ input + '.');
                System.out.print("Votre message :");
                String message = entreeClavier.nextLine();
                write(message,out);
                entreeClavier.
            }
            client.close();
        }
    }

    private void write(String msg, BufferedWriter bw) throws IOException {
        bw.write(msg);
        bw.newLine();
        bw.flush();
    }

}
