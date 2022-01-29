import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final String host = "localhost";
    private static final int port = 8090;

    public static void main(String[] args) {

        try (Socket clientSocket = new Socket(host, port); // создаём новый сокет
             // для вывода инфы создаётся новый объект OutputStream
             PrintWriter out = new
                     PrintWriter(clientSocket.getOutputStream(),true);
             // для принятия инфы каждый раз создаётся новый объект InputStreamReader
             BufferedReader in = new
                     BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            // отправляем поток с текстом
            out.println("Netology");
            // принимаем обратный поток
            String resp = in.readLine();
            // печатаем в консоли
            System.out.println(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
