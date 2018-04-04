package Chat;

import java.io.IOException;

public class main {
    public static void main(String[] args){
        ServeurChatTcp serveurChatTcp = new ServeurChatTcp(2000);
        ClientChatTcp clientChatTcp = new ClientChatTcp("127.0.0.1",2000);
        try {
            //serveurChatTcp.demarrer();
            clientChatTcp.demarrer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
