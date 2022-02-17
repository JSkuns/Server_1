import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int port = 8090;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(port)) { // пытаемся создать сервер-сокет
            System.out.println("Server start");
            Socket clientSocket = serverSocket.accept(); // ожидаем подключения
            // для вывода инфы используем объект OutputStream, autoFlush - добавляем данные
            // для принятия инфы каждый раз создаётся новый объект InputStreamReader
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (!clientSocket.isClosed()) {
                // пишем что соединение принято, порт такой-то
                System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());
                // строка name приняла поток и прочитала
                String name = in.readLine();
                // отправляем обратно ответ
                out.printf("Hi %s, your port is %d%n", name, clientSocket.getPort());
            }
            // закрываем сокет клиента
            clientSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}