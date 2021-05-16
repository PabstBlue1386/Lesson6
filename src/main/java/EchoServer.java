import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class EchoServer {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(EchoConstants.PORT)) {
            System.out.println("Сервер запущен! Ожидает соединения..");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился!");
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String strFromClient = inputStream.readUTF();
                if (strFromClient.equals(EchoConstants.STOP_WORD)) {
                    break;
                }
                outputStream.writeUTF("Echo client: " + strFromClient);

            }
            while (true) {
                String strFromClient = scanner.nextLine();
                if (strFromClient.equals(EchoConstants.STOP_WORD)) {
                    break;
                }
                outputStream.writeUTF(strFromClient);

            }
            System.out.println("Сервер выключается..");

        } catch (IOException o) {
            o.printStackTrace();
        }

    }
}

