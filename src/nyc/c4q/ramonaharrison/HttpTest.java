package nyc.c4q.ramonaharrison;

import java.net.URL;
import java.util.Scanner;
import org.jsoup.Jsoup;

/**
 * Access Code 2.1
 * Ramona Harrison
 * HttpTest.java
 */

public class HttpTest {

    static int links = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("enter URL:");
            String input = sc.next();
            if (input == null)
                continue;
            getPhilosophy(input);
        }

    }

    public static String getPhilosophy(String stringUrl) {

        if (stringUrl.equalsIgnoreCase("http://en.wikipedia.org/wiki/philosophy"))
            return "You've reached Wikipedia's Philosophy entry in " + links + "links.";
        else {

            URL url = HTTP.stringToURL(stringUrl);
            String document = HTTP.get(url);

            int bold = findBold(document);
            int start = findStart(document, bold);

            String link = grabURL(document, start);
            String nextUrl = buildNextUrl(link);

            if (stringUrl.equalsIgnoreCase(nextUrl)) {
                link = grabURL(document, start + 10);
                System.out.println(link);
                nextUrl = buildNextUrl(link);
                links += 1;
                return getPhilosophy(nextUrl);

            } else {
                System.out.println(link);
                links += 1;
                return getPhilosophy(nextUrl);
            }
        }
    }

    public static String buildNextUrl(String link) {
        return ("http://en.wikipedia.org" + link);

    }

    public static int findBold(String document) {
        return document.indexOf("<b>", 0);

    }

    public static int findStart(String document, int bold) {
        int isA = 0;
        int isThe = 0;
        //int wasOne = 0;
        //int wasA = 0;
        //int isOne = 0;
        //int isAn = 0;
        //int isAny = 0;

        isA = document.indexOf(" is a ", bold);
        isThe = document.indexOf(" is the ", bold);

        if (isA < isThe) {
            return isA;
        } else {
            return isThe;
        }
    }

    public static String grabURL(String document, int fromIndex) {
        int firstQuote = document.indexOf("\"/wiki/", fromIndex);
        int secondQuote = document.indexOf("\"", firstQuote + 1);
        return document.substring(firstQuote + 1, secondQuote);
    }
}

