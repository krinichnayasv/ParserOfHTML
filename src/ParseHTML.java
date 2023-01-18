import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParseHTML {

  private static String html = "";
  private static ArrayList<String> imageURL = new ArrayList<>();


    public ParseHTML() {

    }

    public static String parseFile (String path) {

        try {
            Document document = Jsoup.connect("https://lenta.ru").get();
            html = document.toString();
             }catch (IOException ex) {ex.getStackTrace();}
        return html;
    }


    public static ArrayList<String> getImageUrls() {

        Document doc = Jsoup.parse(html);
        String url ="";

        Elements elements = doc.select("img");
        for (Element el : elements) {
            url = el.attr("abs:src").trim();
            if (!url.isEmpty()) {
            imageURL.add(url); }
                  }
        return imageURL;
    }

    public static void getImages () {

        for (String url : imageURL) {
            try {
                URL urlImage = new URL(url);
                BufferedImage image = ImageIO.read(urlImage);
                String name = getNameImage(url);
                String format = getFormatName(url);

                File file = new File("images/" + name);
                if (!file.exists()) {
                    file.createNewFile();
                }
                ImageIO.write(image, format, file);
                System.out.println(name);
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        }

    }

    public static String getNameImage (String url) {
        String name = "";
        int end = url.lastIndexOf(".",url.length()-1);
        int start = url.lastIndexOf("/", end);
        name = url.substring(start+1);
        return name;
    }

    public static String getFormatName (String url) {
        String format = "";
        int end = url.length()-1;
        int start = url.lastIndexOf(".", end);
        format = url.substring(start+1);
        return format;
    }

}
