package ClientAPI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final String address;
    private final int port;
    private Socket socket;

    public Client(String address, int port){
        this.address = address;
        this.port = port;
    }

    public void startContact() {
        try {
            socket = new Socket(address, port);
            Input input = new Input(new DataInputStream(socket.getInputStream()));
            Output output = new Output(new DataOutputStream(socket.getOutputStream()));
            Thread inputThread = new Thread(input);
            Thread outputThread = new Thread(output);
            inputThread.start();
            outputThread.start();
            inputThread.join();
            outputThread.join();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.shutdownInput();
                socket.shutdownOutput();
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
