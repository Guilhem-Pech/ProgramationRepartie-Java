

import java.io.*;
import java.net.Socket;

public class ClientPop3 {
    private String ip;
    private int port;
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;


    public ClientPop3(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        this.socket = new Socket(ip, port);
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        if (!checkIfOk(br.readLine()))
            throw new IOException();

    }

    private boolean checkIfOk(String msg) {
        return msg.startsWith("+OK");
    }

    public void connection(String user, String pass) throws IOException {
        write("user " + user, true, false);
        write("pass " + pass, true, false);
    }

    private void write(String message, boolean oneLine, boolean show) throws IOException {
        write(message, oneLine, show, br, bw);
    }

    private void write(String message, boolean oneLine, boolean show, BufferedReader bufferedReader, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        for (String mesg = bufferedReader.readLine(); ; mesg = bufferedReader.readLine()) {
            if (show)
                System.out.println(mesg);
            if (!mesg.isEmpty() && mesg.charAt(0) == '-')
                throw new IOException(mesg);
            if (oneLine || (!mesg.isEmpty() && mesg.charAt(0) == '.'))
                break;
        }
    }

    public void listMail() throws IOException {
        write("list", false, true);
    }

    public void readMail(int number) throws IOException {
        write("retr " + number, false, true);
    }

}
