import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {


    private Socket socket;
    //Scanner scanner = new Scanner(socket.getInputStream());
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public  EchoClient() throws IOException {
        try {
            openConnection();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }


    private void openConnection() throws IOException {
        socket = new Socket(EchoConstants.HOST, EchoConstants.PORT);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.equals(EchoConstants.STOP_WORD)) {
                        break;
                    }
                    outputStream.writeUTF(strFromServer);


                }


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new EchoClient();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

}
