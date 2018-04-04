import java.io.*;
import java.net.Socket;

public class ClientTP4 {

    private String ip;
    private int port;
    private Socket sockClient;

    public ClientTP4(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        sockClient = new Socket(ip, port);
    }

    public void getFile(String fileName, String dest) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockClient.getOutputStream()));
        bw.write(fileName);
        bw.newLine();
        bw.flush();
        InputStream inServeur = sockClient.getInputStream();
        byte buf[] = new byte[512];
        int nbOctet = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(dest);
        while ((nbOctet = inServeur.read(buf)) != -1) {
            fileOutputStream.write(buf, 0, nbOctet);
        }
    }

}
