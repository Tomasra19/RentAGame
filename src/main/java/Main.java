import spark.*;


public class Main {

    public static void main(String[] args) {

        Render r = new Render();
        Database db = new Database();
        //Spark
        Spark.staticFiles.location("/public");
        Spark.port(1234);
        //Home route
        Spark.get("/home", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return r.renderAllGames();
            }
        });

        //Date input from clientside
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
        //Calendar Route
        Spark.get("/calendar", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return r.renderCalendar();
            }
        });
        //Contacts Route
        Spark.get("/contacts", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return r.renderContacts();
            }
        });
        //Game route
        Spark.get("/:id", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String id = request.params(":id");
                int idInt = Integer.parseInt(id);
                return r.renderGamePage(idInt);
            }
        });
    }
}

