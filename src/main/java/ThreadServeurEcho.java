import java.io.*;
import java.net.Socket;

public class ThreadServeurEcho extends Thread {
    private Socket client;

    public ThreadServeurEcho(Socket socket) {
        this.client = socket;
    }


    @Override
    public void run() {
        BufferedReader in;
        BufferedWriter out;
        try {
            in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(
                    client.getOutputStream()));
            String chaine;
            while ((chaine = in.readLine()) != null) {
                out.write(chaine.toUpperCase());
                out.newLine();
                out.flush();
            }
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
