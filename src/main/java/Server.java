import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int port = 8090;

    public static void main(String[] args) throws IOException {
        System.out.println("Server start");
        while (true) {
            ServerSocket serverSocket = new ServerSocket(port); // создаём сокет
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            // для вывода инфы используем объект OutputStream, autoFlush - добавляем данные
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            // для принятия инфы каждый раз создаётся новый объект InputStreamReader
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // пишем что соединение принято, порт такой-то
            System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());
            // строка name приняла поток и прочитала
            final String name = in.readLine();
            // вывод
            out.printf("Hi %s, your port is %d%n", name, clientSocket.getPort());
            // сокет закрываем
            serverSocket.close();
        }
    }
}