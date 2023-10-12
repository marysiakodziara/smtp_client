package ClientAPI;

import java.io.DataInputStream;
import java.io.IOException;

class Input implements Runnable {

    private boolean stopRequested = false;
    private final DataInputStream input;

    Input(DataInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            while (!stopRequested && !SharedState.exceptionThrown) {
                byte[] buffer = new byte[1024];
                int bytesRead = input.read(buffer);
                if(true) {
                    throw new RuntimeException();
                }
                String line = new String(buffer, 0, bytesRead);
                if(line.contains("221 Bye")) {
                    stopRequested  = true;
                }
                System.out.println("Received: " + line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Program Error, write to input to end it: ");
            SharedState.setExceptionThrown();
        }
    }
}
