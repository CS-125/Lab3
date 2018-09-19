import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WebScraper {
    public static void main(String[] args) {
        System.out.println(urlToString("http://erdani.com/tdpl/hamlet.txt"));
    }

    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    private static int wordCounter(String url) {
        int wordCount = 0;
        boolean isASpace = true;
        String content = urlToString(url);

        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == ' ' || content.charAt(i) == '\n' || content.charAt(i) == '\t') {
                isASpace = true;
            }
            else if (isASpace) {
                isASpace = false;
                wordCount++;
            }
        }

        return wordCount;
    }

    private static int countOneWord(String url, String word) {
        int occurrenceCount = 0;
        String content = urlToString(url);
        String[] wordArray = content.split(" ");

        for (int i = 0; i < wordArray.length; i++) {
            if (word.equals(wordArray[i])) {
                occurrenceCount++;
            }
        }

        return occurrenceCount;
    }

    private static int countUniqueWords(String url, String word) {
        String content = urlToString(url);
        String[] wordArray = content.split(" ");

        Set<String> uniqueWords = new HashSet<>();

        for (String currentWord:wordArray) {
            uniqueWords.add(currentWord);
        }
        return uniqueWords.size();
    }
}
