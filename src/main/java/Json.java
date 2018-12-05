import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Json {
    public void writeArrayToJson (ArrayList<CalendarObject> orders) {
        Gson gson = new Gson();

        try {
            Writer writer = new FileWriter("C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\public\\json.json");
            gson.toJson(orders, writer);
            writer.flush(); //flush data to file   <---
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
