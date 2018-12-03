import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Render {

    Database db = new Database();

    public String renderAllGames() {

        String index = "";
        {
            try {
                index = String.valueOf(Jsoup.parse(new File("C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\index1.html"), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String gameHtml = "";

        {
            try {
                gameHtml = String.valueOf(Jsoup.parse(new File("C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\game.html"), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String gameArrayHtml = "";
        ArrayList<Game> gamesList = db.getGames();
        for (Game game : gamesList) {
            gameArrayHtml += gameHtml.replace("{URL}", game.getImgURL())
                    .replace("{platform}", game.getPlatform())
                    .replace("{name}", game.getName())
                    .replace("{price}", String.valueOf(game.getPrice()))
                    .replace("{id}", String.valueOf(game.getId()));

        }
        return index.replace("{games}", gameArrayHtml);
    }

    public String renderGamePage (int id) {
        String index = "";
        {
            try {
                index = String.valueOf(Jsoup.parse(new File("C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\index1.html"), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String gamePageHtml = "";

        {
            try {
                gamePageHtml = String.valueOf(Jsoup.parse(new File("C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\gamePage.html"), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Game game = db.getGameById(id);
        System.out.println(game.getName());
            gamePageHtml = gamePageHtml.replace("{URL}", game.getImgURL())
                    .replace("{platform}", game.getPlatform())
                    .replace("{name}", game.getName())
                    .replace("{price}", String.valueOf(game.getPrice()))
                    .replace("{description}", game.getDescription());


        return index.replace("{games}", gamePageHtml);

    }

}

