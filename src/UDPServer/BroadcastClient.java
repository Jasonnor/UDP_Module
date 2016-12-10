package UDPServer;

import java.net.InetAddress;
import java.util.ArrayList;

import org.json.*;

public class BroadcastClient {

    public void startUDPBroadCast() throws Exception {
        ArrayList<InetAddress> clientAddresses = TCPServerModule.getClientIPTable();
        ArrayList<JSONObject> updateInfo = CentralizedDataCenter.getUpdateInfo();
        ArrayList<JSONObject> encodeInfo = new ArrayList<>();
        for (JSONObject info : updateInfo) {
            Object character = info.getJSONObject("Character");
            Object item = info.getJSONObject("Item");
            JSONObject newInfo = new JSONObject();
            newInfo.append("Command", "ADD");
            newInfo.append("Character", character);
            newInfo.append("Item", item);
            encodeInfo.add(newInfo);
        }
        Broadcast broadcast = new Broadcast(clientAddresses, encodeInfo);
        broadcast.start();
    }
}
