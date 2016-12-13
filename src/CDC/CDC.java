package CDC;

import org.json.JSONObject;

import java.util.ArrayList;

public class CDC {

    public static ArrayList<JSONObject> getUpdateInfo() {
        return new ArrayList<JSONObject>() {{
            JSONObject info = new JSONObject();
            info.append("Character", "Jason Wu");
            info.append("Item", "Bomb");
            add(info);
        }};
    }
}
