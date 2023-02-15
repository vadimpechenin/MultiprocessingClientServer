package cleanCodeVersion.client;

import cleanCodeVersion.common.MessageUtils;

import java.io.IOException;
import java.net.Socket;

public class ClientTest {
    private final int PORT;
    private int message;
    public ClientTest(int port) throws IOException {
        PORT = port;
        message = (int) (Math.random()*1000);
        connectSendReceive(message);
    }

    private void connectSendReceive(int i){
        try {
            Socket socket = new Socket ("localhost", PORT);
            MessageUtils.sendMessage(socket, Integer.toString(i));
            MessageUtils.getMessage(socket);
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
