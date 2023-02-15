package habrVersion;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Имитация множественного обращения клиентов к серверу
 * Для имитации множественного обращения клиентов к серверу, создадим и запустим
 * (после запуска серверной части) фабрику Runnable клиентов которые будут подключаться серверу
 * и писать сообщения в цикле
 * https://habr.com/ru/post/330676/
 */
public class Main {

    // private static ServerSocket server;

    public static void main(String[] args) throws IOException, InterruptedException {

        // запустим пул нитей в которых колличество возможных нитей ограничено -
        // 10-ю.
        ExecutorService exec = Executors.newFixedThreadPool(10);
        int j = 0;

        // стартуем цикл в котором с паузой в 10 милисекунд стартуем Runnable
        // клиентов,
        // которые пишут какое-то количество сообщений
        while (j < 10) {
            j++;
            exec.execute(new TestRunnableClientTester());
            Thread.sleep(10);
        }

        // закрываем фабрику
        exec.shutdown();
    }
}
