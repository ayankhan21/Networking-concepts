import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer{
    public void main(String[] args) throws IOException {
        System.out.println("Hello, World!");

        try {
            // the socket instance resource is created in the special try , which releases the resource after the try block is executed

            ServerSocket serverSocket = new ServerSocket(5001);

            System.out.println("Listening to clients....");
            Socket clientSocket = serverSocket.accept(); // Listens for a connection to be made to this socket and accepts it.
            // The method blocks until a connection is made.
            
            String clientSocketIP = clientSocket.getInetAddress().toString(); // getting the IP address of the client
            int clientSocketPort = clientSocket.getPort(); // getting the port number of the client
            System.out.println("[IP: " + clientSocketIP + " ,Port: " + clientSocketPort +"]  " + "Client Connection Successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}