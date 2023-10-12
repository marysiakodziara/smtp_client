package ClientAPI;

import java.io.DataOutputStream;
import java.util.Scanner;

class Output implements Runnable {
    private final Scanner scanner = new Scanner(System.in);
    private boolean stopRequested = false;

    private final DataOutputStream out;

    Output(DataOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        try {
            while (!stopRequested && !SharedState.exceptionThrown) {
                String message = scanner.nextLine();
                out.write(message.getBytes());
                if (message.equals("QUIT")) {
                    stopRequested = true;
                }
            }
            scanner.close();
        } catch (Exception e) {
            scanner.close();
            SharedState.setExceptionThrown();
            e.printStackTrace();
        }
    }
}
