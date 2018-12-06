import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class Json {
    public void writeArrayToJson (ArrayList<CalendarObject> orders) {

        Gson gson = new GsonBuilder().create();
        try (Writer writer = new FileWriter(
                "C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\public\\json.json")){
            gson.toJson(orders, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
