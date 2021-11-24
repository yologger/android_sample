import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String args[]) {

        int port = 8888;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening on port " + port + "...");

            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress() + " connected.");

                TcpHandler handler = new TcpHandler(socket);
                Thread thread = new Thread(handler);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
