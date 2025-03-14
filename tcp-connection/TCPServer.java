import java.io.DataInputStream;
import java.io.DataOutputStream;
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

            DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());

            // We are creating a data input and output stream to read and write data to the client.

            String clientMessage = dataIn.readUTF(); // readUTF reads a string from the input stream
            System.out.println("Client: " + clientMessage);

            String serverMessage = "Hello from the server!";
            dataOut.writeUTF(serverMessage); // writeUTF writes a string to the output stream

            dataIn.close();
            dataOut.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}