package TCPServerModule;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class TCPServerModule {
    public static ArrayList<InetAddress> getClientIPTable() {
        return new ArrayList<InetAddress>() {{
            try {
                add(InetAddress.getLocalHost());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }};
    }
}
