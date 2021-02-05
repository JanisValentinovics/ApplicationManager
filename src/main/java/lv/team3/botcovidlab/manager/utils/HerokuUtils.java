package lv.team3.botcovidlab.manager.utils;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

public class HerokuUtils {
    public static InputStream getFirebaseSettings() {
        return new ByteArrayInputStream(System.getenv("FIREBASE_SETTINGS").getBytes());
    }
}
