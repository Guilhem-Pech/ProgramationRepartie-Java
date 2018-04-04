package Chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientChatTcp {
    private String ip;
    private int port;

    public ClientChatTcp(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void demarrer() throws IOException {
        Socket client = new Socket(ip,port);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        Scanner entreeClavier = new Scanner(System.in);
        String input;
        while((input = in.readLine()) != null && !input.equals("bye")){
            System.out.println("Message reçu: "+ input);
            System.out.print("Votre message :");
            String message = entreeClavier.nextLine();
            write(message,out);
        }
    }

    private void write(String msg, BufferedWriter bw) throws IOException {
        bw.write(msg);
        bw.newLine();
        bw.flush();
    }
}
