import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Render {

    Database db = new Database();

    //txt to string
    private static String readLineByLineJava8(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public String renderAllGames() {
        String index = "";
        {
            try {
                index = String.valueOf(Jsoup.parse(new File(
                        "C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\index1.html"),
                        "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String gameHtml = "";
        {
            try {
                gameHtml = String.valueOf(Jsoup.parse(new File(
                        "C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\game.html"),
                        "UTF-8"));
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
                index = String.valueOf(Jsoup.parse(new File(
                        "C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\index1.html"),
                        "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String gamePageHtml = "";

        {
            try {
                gamePageHtml = String.valueOf(Jsoup.parse(new File(
                        "C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\gamePage.html"),
                        "UTF-8"));
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
    public String renderCalendar() {
        String calendar = "";
        try {
            calendar = String.valueOf(Jsoup.parse(new File(
                    "C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\calendar.html"),
                    "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = "C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\jstemplate1.txt";
        String jsTemplate = readLineByLineJava8(path);


        String jsEventArray = "";
        ArrayList<CalendarObject> events = db.getOrders();
        for (CalendarObject event : events) {

            jsEventArray += jsTemplate.replace("{title}",event.getTitle())
                    .replace("{startDate}",event.getStart())
                    .replace("{endDate}",event.getEnd())
                    .replace("{colour}",event.getColor());


        }
        return calendar.replace("{events}",jsEventArray);
    }

    public String renderContacts() {
        String index = "";
            try {
                index = String.valueOf(Jsoup.parse(new File(
                        "C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\index1.html"),
                        "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        String contactPageHtml = "";
        try {
            contactPageHtml = String.valueOf(Jsoup.parse(new File(
                    "C:\\Users\\admin\\Desktop\\Baigiamasis\\Java\\src\\main\\resources\\html\\contacts.html"),
                    "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index.replace("{games}",contactPageHtml);
    }

}

