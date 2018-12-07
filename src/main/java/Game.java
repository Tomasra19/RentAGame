

public class Game {
    private int id;
    private String name;
    private String platform;
    private int price;
    private String imgURL;
    private String description;



    public Game(int id, String name, String platform, int price, String imgURL, String description) {
        this.id = id;
        this.name = name;
        this.platform = platform;
        this.price = price;
        this.imgURL = imgURL;
        this.description = description;
    }
    public Game(){

    }

    @Override
    public String toString() {
        return this.name + " " + this.platform+ this.price+ this.price+ this.imgURL;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
