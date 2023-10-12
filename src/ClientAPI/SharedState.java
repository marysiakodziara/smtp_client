package ClientAPI;

class SharedState {

    public static volatile boolean exceptionThrown = false;
    public static void setExceptionThrown() {
        exceptionThrown = true;
    }

    private SharedState(){}
}
