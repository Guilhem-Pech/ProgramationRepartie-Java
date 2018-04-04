import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {

        ClientTcpEcho client = new ClientTcpEcho("10.203.9.89",50013);

        ServeurTcpEchoMulti serveur = new ServeurTcpEchoMulti();
        ClientSmtp clientSmtp = new ClientSmtp("139.124.187.23", 25, "Pluton");
        ClientPop3 clientPop3 = new ClientPop3("139.124.187.23", 110);

        clientPop3.connection("pech", "pech");
        clientPop3.listMail();
        clientPop3.readMail(15);

        //client.lancer();
        //serveur.demarrer();
        //clientSmtp.send("JESUS.OFFICIEL<jesus.officiel@heaven.hell>","pech","Invitation","Bonjour, J'ai le plaisir de vous annoncer que vous pouvez dès maintenant venir au paradis");



    }

}
