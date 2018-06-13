import java.io.PrintWriter;
import java.util.TreeSet;

/**
 * Class for work with HTML files
 */
public class HTMLController {

    /**
     * Create HTML file with info about MP3 files
     * @param listOfFileData is list of MP3 files data
     */
    public void createHTML(TreeSet<MP3FileData> listOfFileData) {
        if (listOfFileData.size() > 0) {
            StringBuilder html = new StringBuilder();
            String tab = "style = 'padding-left:30px';";
            String doubleTab = "style = 'padding-left:60px';";
            String artistName = listOfFileData.first().getArtist();
            String albumName = listOfFileData.first().getAlbum();
            html.append("<p>" + artistName + "</p>");
            html.append("<p " + tab + ">" + albumName + "</p> ");
            for (MP3FileData data : listOfFileData) {
                if (data.getArtist().equals(artistName)) {
                    if (data.getAlbum().equals(albumName)) {
                        html.append("<p " + doubleTab + ">" + data.getTitle() + " " + data.getDuration() + " " + "<a href='#'>" + data.getFilePath() + "</a>" + "</p>");
                    }
                    else {
                        albumName = data.getAlbum();
                        html.append("<p " + tab + ">" + albumName + "</p>");
                        html.append("<p " + doubleTab + ">" + data.getTitle() + " " + data.getDuration() + " " + "<a href='#'>" + data.getFilePath() + "</a>" + "</p>");
                    }
                }
                else {
                    albumName = data.getAlbum();
                    artistName = data.getArtist();
                    html.append("<p>" + artistName + "</p>");
                    html.append("<p " + tab + ">" + albumName + "</p>");
                    html.append("<p " + doubleTab + ">" + data.getTitle() + " " + data.getDuration() + " " + "<a href='#'>" + data.getFilePath() + "</a>" + "</p>");

                }
            }
            html.append("<style>p{margin:0};</style>");
            saveToFile(html);
        }
        else {
            System.out.println("In your dictionary no mp3 files");
        }

    }

    /**
     * Save into html data
     * @param html is object, which contains html data
     */
    private void saveToFile(StringBuilder html) {
        try {
            PrintWriter pw = new PrintWriter("result.html");
            pw.println(html);
            pw.close();
        } catch (Exception e) {
            System.out.println("Error with save to file");
        }


    }

}
