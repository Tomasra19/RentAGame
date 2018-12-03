import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import static spark.route.HttpMethod.get;


public class Main {
    public static int id = 1;

    public static void main(String[] args) {
        Render r = new Render();

        Spark.staticFiles.location("/public");
        Spark.port(1234);
        Spark.get("/home", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {

                return r.renderAllGames();
            }
        });
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

