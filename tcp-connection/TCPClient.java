
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket()) {
            // the socket instance resource is created in the special try , which releases the resource after the try block is executed
            socket.connect(new InetSocketAddress("127.0.0.1", 5001),1000);
            // the connect takes 2 params , 1: endpoint , 2: timeout in miliseconds
            // Since we dont have an address we are creating one using InetSocketAddress
            // InetSocketAddress - Creates a socket address from a hostname and a port number.
            System.out.println("Connection Successful!");
        }
    }
}
