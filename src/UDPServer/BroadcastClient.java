package UDPServer;

import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class BroadcastClient extends Thread {

    int playerCount = 1;

    public static void main(String[] args) throws Exception {
        BroadcastClient broadcastClient = new BroadcastClient();
        broadcastClient.start();
    }

    public void run() {
        try {
            startUDPBroadCast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startUDPBroadCast() throws Exception {
        ArrayList<InetAddress> clientAddresses = new ArrayList<>();
        do {
            if (clientAddresses.size() > 0)
                sleep(100);
            clientAddresses = TCPServerModule_getClientIPTable();
        } while (clientAddresses.size() < playerCount);
        ArrayList<JSONObject> firstEncodeInfo = getEncodeInfo("ADD");
        Broadcast firstBroadcast = new Broadcast(clientAddresses, firstEncodeInfo);
        firstBroadcast.start();
        while (true) {
            ArrayList<JSONObject> encodeInfo = getEncodeInfo("UPDATE");
            Broadcast broadcast = new Broadcast(clientAddresses, encodeInfo);
            broadcast.start();
            sleep(200);
        }
    }

    private ArrayList<JSONObject> getEncodeInfo(String command) {
        ArrayList<JSONObject> updateInfo = CentralizedDataCenter_getUpdateInfo();
        boolean infoCorrect = true;
        do {
            if (!infoCorrect)
                infoCorrect = true;
            for (JSONObject info : updateInfo) {
                infoCorrect &= info.has("Character");
                infoCorrect &= info.has("Item");
            }
        } while (!infoCorrect);
        ArrayList<JSONObject> encodeInfo = new ArrayList<>();
        for (JSONObject info : updateInfo) {
            Object character = info.get("Character");
            Object item = info.get("Item");
            JSONObject newInfo = new JSONObject();
            newInfo.append("Command", command);
            newInfo.append("Character", character);
            newInfo.append("Item", item);
            encodeInfo.add(newInfo);
        }
        return encodeInfo;
    }

    // Fake methods
    ArrayList<InetAddress> TCPServerModule_getClientIPTable() {
        return new ArrayList<InetAddress>() {{
            try {
                add(InetAddress.getLocalHost());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }};
    }

    ArrayList<JSONObject> CentralizedDataCenter_getUpdateInfo() {
        return new ArrayList<JSONObject>() {{
            JSONObject info = new JSONObject();
            info.append("Character", "Jason Wu");
            info.append("Item", "Bomb");
            add(info);
        }};
    }
}
