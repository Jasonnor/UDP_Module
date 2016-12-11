package UDPServer;

import org.json.JSONObject;

import java.net.InetAddress;
import java.util.ArrayList;

public class BroadcastClient extends Thread {

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
        ArrayList<InetAddress> clientAddresses = TCPServerModule_getClientIPTable();
        ArrayList<JSONObject> firstEncodeInfo = updateInfo("ADD");
        Broadcast firstBroadcast = new Broadcast(clientAddresses, firstEncodeInfo);
        firstBroadcast.start();
        while (true) {
            ArrayList<JSONObject> encodeInfo = updateInfo("UPDATE");
            Broadcast broadcast = new Broadcast(clientAddresses, encodeInfo);
            broadcast.start();
            sleep(200);
        }
    }

    private ArrayList<JSONObject> updateInfo(String command) {
        ArrayList<JSONObject> updateInfo = CentralizedDataCenter_getUpdateInfo();
        ArrayList<JSONObject> encodeInfo = new ArrayList<>();
        for (JSONObject info : updateInfo) {
            Object character = info.getJSONObject("Character");
            Object item = info.getJSONObject("Item");
            JSONObject newInfo = new JSONObject();
            newInfo.append("Command", command);
            newInfo.append("Character", character);
            newInfo.append("Item", item);
            encodeInfo.add(newInfo);
        }
        return encodeInfo;
    }

    // Fake methods
    private ArrayList<InetAddress> TCPServerModule_getClientIPTable() {
        return new ArrayList<>();
    }

    private ArrayList<JSONObject> CentralizedDataCenter_getUpdateInfo() {
        return new ArrayList<>();
    }
}
