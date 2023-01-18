
public class Main {

    public static void main(String[] args) {

        ParseHTML.parseFile("data/lentaRU.html");
        ParseHTML.getImageUrls();
        ParseHTML.getImages();
    }
}
