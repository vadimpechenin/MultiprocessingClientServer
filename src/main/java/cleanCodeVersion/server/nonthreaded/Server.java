package cleanCodeVersion.server.nonthreaded;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import cleanCodeVersion.common.MessageUtils;


/**
 * Однопоточная архитектура сервера
 */
public class Server implements Runnable{
    ServerSocket serverSocket;
    volatile boolean keepProcessing = true;

    public Server(int port, int millisecondsTimeout) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(millisecondsTimeout);
    }

    @Override
    public void run() {
        System.out.printf("nonthreaded.Server Starting\n");

        while (keepProcessing) {
            try {
                System.out.printf("accepring client\n");
                Socket socket = serverSocket.accept();
                System.out.printf("got client\n");
                process(socket);
            } catch (Exception e){
                handle(e);
            }
        }
    }

    private void handle(Exception e){
        if (!(e instanceof SocketException)){
            e.printStackTrace();
        }
    }

    public void stopProcessing(){
        keepProcessing = false;
        closeIgnoringException(serverSocket);
    }


    void process(Socket socket){
        if (socket == null)
            return;
        try {
            System.out.printf("nonthreaded.Server: getting message\n");
            String message = MessageUtils.getMessage(socket);
            System.out.printf("nonthreaded.Server: got message: %s\n", message);
            Thread.sleep(1000);
            System.out.printf("nonthreaded.Server: sending reply: %s\n", message);
            MessageUtils.sendMessage(socket, "Processed: " + message);
            System.out.printf("nonthreaded.Server: sent\n");
            closeIgnoringException(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeIgnoringException(Socket socket) {
        if (socket != null)
            try {
                socket.close();
            } catch (IOException ignore) {
            }

    }

    private void closeIgnoringException(ServerSocket serverSocket) {
        if (serverSocket != null)
            try {
                serverSocket.close();
            } catch (IOException ignore) {
            }

    }

}
