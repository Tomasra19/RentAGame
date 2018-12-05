import spark.*;

import java.util.ArrayList;




public class Main {

    public static String startDate;
    public static void main(String[] args) {
        Render r = new Render();
        Database db = new Database();
        Json json = new Json();

        Spark.staticFiles.location("/public");
        Spark.port(1234);
        Spark.get("/home", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {

                return r.renderAllGames();
            }
        });

        //calendorius

        //Date inputas is clientside
        Spark.post("/post", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String name = request.queryParams("name");
                String platform = request.queryParams("platform");
                String startDate = request.queryParams("startDate");
                String returnDate = request.queryParams("returnDate");
                System.out.println(name + returnDate + startDate);
                db.insertOrder(name, platform, startDate,returnDate);
                return "Pavyko";
            }
        });
        Spark.get("/calendar", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ArrayList<CalendarObject> order = db.getOrders();
                json.writeArrayToJson(order);
                return r.renderCalendar();
            }
        });
        //prekes route
        Spark.get("/:id", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String id = request.params(":id");
                int idInt = Integer.parseInt(id);
                return r.renderGamePage(idInt);
            }
        });

//        db.insertOrder("dasda", "dasdas","asdsad");
//        Gson gson = new Gson();
//        try {
//            Writer writer = new FileWriter("C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\public\\asda.json");
//
//            gson.toJson(232,writer);
//            writer.flush(); //flush data to file   <---
//            writer.close(); //close write
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}

