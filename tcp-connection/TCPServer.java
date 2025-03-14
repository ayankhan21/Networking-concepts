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

            while (true) {
                String clientMessage = dataIn.readUTF(); // Read message

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }

                String[] clientMessageArray = clientMessage.split("_");

                if (clientMessageArray == null || clientMessageArray.length < 3) {
                    dataOut.writeUTF("Invalid input format. Expected format: operation_num1_num2");
                    dataOut.flush();
                    continue;
                }

                String operation = clientMessageArray[0].toLowerCase();
                int num1;
                int num2;
                try {
                    num1 = Integer.parseInt(clientMessageArray[1]);
                    num2 = Integer.parseInt(clientMessageArray[2]);
                } catch (NumberFormatException e) {
                    dataOut.writeUTF("Invalid number format. Please provide valid integers.");
                    dataOut.flush();
                    continue;
                }

                int result = switch (operation) {
                    case "add" -> num1 + num2;
                    case "sub" -> num1 - num2;
                    case "mul" -> num1 * num2;
                    case "div" -> num2 != 0 ? num1 / num2 : 0; // Avoid division by zero
                    default -> throw new IllegalArgumentException("Invalid operation");
                };

                dataOut.writeUTF("Result: " + result);
                dataOut.flush();
            }

            // Lets create an input format which will perfrom certain opertaions based on the input



            String serverMessage = "Hello from the server!";
            dataOut.writeUTF(serverMessage); // writeUTF writes a string to the output stream

            // dataIn.close();
            // dataOut.close();
            // clientSocket.close();
            // serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}