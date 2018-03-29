import java.io.IOException;
import java.net.ServerSocket;

public class main {

    public static void main (String[] args){

        ClientTcpEcho client = new ClientTcpEcho("10.203.9.89",50013);

        ServeurTcpEchoMulti serveur = new ServeurTcpEchoMulti();
        try {
            //client.lancer();
            serveur.demarrer();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
