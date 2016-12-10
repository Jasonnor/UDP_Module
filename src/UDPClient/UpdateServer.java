package UDPClient;

public class UpdateServer extends Thread {

    public static void main(String[] args) throws Exception {
        UpdateServer updateServer = new UpdateServer();
        updateServer.run();
    }

    public void run() {
        try {
            initUPDServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUPDServer() throws Exception {
        
    }
}
