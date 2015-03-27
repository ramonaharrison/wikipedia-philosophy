package nyc.c4q.ramonaharrison;

/**
 * Access Code 2.1
 * Ramona Harrison
 * Main.java
 * Takes a Wikipedia URL as input and follows the first link in each subsequent article until
 * the wikipedia.org/wiki/philosophy page is reached
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    static int linkCounter = 0;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter a Wikipedia URL:");
            String input = sc.next();
            if (!isValidUrl(input)) {
                System.out.println("Please format input as a valid URL, for example: http://en.wikipedia.org/wiki/Nocturnality\n");
                continue;
            }
            System.out.println(seekPhilosophy(input));
        }
    }

    public static String seekPhilosophy(String article) throws IOException {
        String firstLink = "";

        // breaks the loop and resets counter when philosophy article is reached
        if (article.equalsIgnoreCase("http://en.wikipedia.org/wiki/philosophy")) {
            int total = linkCounter;
            linkCounter = 0;
            return "\nYou've reached Wikipedia's Philosophy entry in " + total + " links.\n";

        // ... or if more than 100 links have been tried
        } else if (linkCounter > 100) {
            linkCounter = 0;
            return "\nToo far away...\n";

        } else {
            URL url = HTTP.stringToURL(article);            // builds URL from string
            Document doc = Jsoup.parse(url, 100000);        // parses wiki page, times out after 100 seconds
            Elements links = doc.select("p > a");           // selects only links within <p> tags

            // chooses the first suitable link
            for (int i = 0; i < links.size(); i++) {
                if (isFirstRealLink(links.get(i).toString())) {
                    firstLink = links.get(i).toString();
                    break;
                }
            }

            // builds next string URL, prints article title to the console, increments counter
            String nextArticle = "http://en.wikipedia.org" + firstLink.substring(9, firstLink.indexOf("\"", 10));
            printTitle(nextArticle);
            linkCounter++;

            return seekPhilosophy(nextArticle);
        }

    }

    public static void printTitle(String article) {
        // formats article titles for printing to the console
        System.out.println("         ▼\n      " + article.substring(29).replace("_", " "));
    }

    public static boolean isValidUrl(String input) {
        // filters out improperly formatted URLs
        return input.startsWith("http://en.wikipedia.org/wiki/");
    }

    public static boolean isFirstRealLink(String link) {
        // filters out language and pronunciation links
        return (link.contains("wiki") && !link.contains("Greek") && !link.contains("Latin") && !link.contains("wiktionary"));
    }

}
