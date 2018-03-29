import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTcpEcho {
    public void demarrer() throws IOException {
        Socket client;
        BufferedWriter out;
        BufferedReader in;
        ServerSocket serveur = new ServerSocket(50007);
        for (int i = 0; i < 10; i++) {
            client = serveur.accept();
            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String bufIn;
            while ((bufIn=in.readLine()) != null){
                out.write(bufIn.toUpperCase());
                out.newLine();
                out.flush();
            }
            client.close();
        }
        serveur.close();
    }
}
