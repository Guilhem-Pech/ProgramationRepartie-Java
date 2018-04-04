import java.io.*;
import java.net.Socket;

public class ClientSmtp {

    private String serveurSmtp;
    private int port;
    private String hostname;

    public ClientSmtp(String serveurSmtp, int port, String hostname) {
        this.serveurSmtp = serveurSmtp;
        this.port = port;
        this.hostname = hostname;

    }

    public void send(String from, String to, String subject, String body) {
        try {
            Socket sockClient = new Socket(serveurSmtp, port);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockClient.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(sockClient.getInputStream()));

            String serverAwnser = br.readLine();
            System.out.println(serverAwnser);

            write("EHLO " + this.hostname, bw, br);
            write("MAIL FROM: " + from, bw, br);
            write("RCPT TO: " + to, bw, br);
            write("DATA", bw, br);
            write("FROM: " + from + '\n' +
                    "TO: " + to + '\n' +
                    "SUBJECT: " + subject + '\n' +
                    body + '\n' +
                    '.', bw, br);
            br.close();
            bw.close();
            sockClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(String message, BufferedWriter bw, BufferedReader br) throws IOException {
        bw.write(message);
        bw.newLine();
        bw.flush();

        for (String mesg = br.readLine(); ; mesg = br.readLine()) {
            if (mesg.isEmpty() || mesg.charAt(3) == ' ')
                break;
            if (mesg.charAt(0) == '4' || mesg.charAt(0) == '5') {
                throw new IOException(mesg);
            }
        }
    }
}
