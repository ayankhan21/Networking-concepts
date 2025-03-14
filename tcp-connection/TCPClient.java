
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket()) {


            Scanner scanner = new Scanner(System.in);


            // the socket instance resource is created in the special try , which releases the resource after the try block is executed
            socket.connect(new InetSocketAddress("127.0.0.1", 5001),1000);
            System.out.println(new InetSocketAddress("127.0.0.1", 5001) + "this is the endpoint that is made from ip & port");
            // the connect takes 2 params , 1: endpoint , 2: timeout in miliseconds
            // Since we dont have an address we are creating one using InetSocketAddress
            // InetSocketAddress - Creates a socket address from a hostname and a port number.
            System.out.println("Connection Successful!");


            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            // dataOut.writeUTF("Hello from the client!"); // writeUTF writes a string to the output stream

            while (true) {
                System.out.print("Enter message to send (or type 'exit' to quit): ");
                String userInput = scanner.nextLine(); // Read user input from terminal

                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Closing connection...");
                    break; // Exit loop if user types 'exit'
                }

                dataOut.writeUTF(userInput); // Send user input to server
                dataOut.flush();

                String serverMessage = dataIn.readUTF(); // Receive response
                System.out.println("Server: " + serverMessage);
            }

            String serverMessage = dataIn.readUTF(); // readUTF reads a string from the input stream
            System.out.println("Server: " + serverMessage);

            dataIn.close();
            dataOut.close();   
            scanner.close(); 
        }
    }
}
