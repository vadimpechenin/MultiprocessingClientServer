package cleanCodeVersion;

import cleanCodeVersion.client.ClientTest;
import cleanCodeVersion.server.nonthreaded.Server;

import java.io.IOException;

/**
 * Главная программа запуска клиента-сервера
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //  стартуем сервер на порту 3345

        try {
            Server server = new Server(3345, 10000);
            server.run();
            ClientTest client = new ClientTest(3345);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
