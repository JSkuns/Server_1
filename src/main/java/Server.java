import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int port = 8090;

    public static void main(String[] args) {

        ServerSocket serverSocket = null; // создаём сокет
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server start");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (true) {
                Socket clientSocket = null; // ждем подключения
                if (serverSocket != null) {
                    clientSocket = serverSocket.accept();
                }
                // для вывода инфы используем объект OutputStream, autoFlush - добавляем данные
                PrintWriter out = null;
                // для принятия инфы каждый раз создаётся новый объект InputStreamReader
                BufferedReader in = null;
                if (clientSocket != null) {
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // пишем что соединение принято, порт такой-то
                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());
                }
                // строка name приняла поток и прочитала
                String name = null;
                if (in != null) {
                    name = in.readLine();
                }
                // вывод
                if (clientSocket != null) {
                    out.printf("Hi %s, your port is %d%n", name, clientSocket.getPort());
                }
                // сокет закрываем
//                serverSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}