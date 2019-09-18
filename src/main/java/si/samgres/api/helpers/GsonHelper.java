package si.samgres.api.helpers;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonHelper {
    private static Gson gson;

    private static Gson getGson() {
        initialize();

        //return
        return gson;
    }

    private static void initialize() {
        if (gson == null) { //ensure singleton
            gson = new Gson();
        }
    }

    public static String toJson(Object object) {
        initialize();

        //end result
        String json = "";

        //convert
        json = getGson().toJson(object);

        return json;
    }

    public static <T> T fromJson(String json, Class<T> targetObjectClass) {
        initialize();

        //end result
        T object;

        //try converting
        try {
            object = getGson().fromJson(json, targetObjectClass);
        }
        catch (Exception e) {
            //TODO: log somewhere

            object = null;
        }

        return object;
    }
}
