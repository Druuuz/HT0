import java.io.File;
import java.util.*;

/**
 * Main class, which controll all works with files
 */
public class Controller {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("You must write directory paths");
        }
        else {
            boolean correctParams = true;
            ArrayList<String> listOfDirs = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                File file = new File(args[i]);
                if (!file.isDirectory()) {
                    correctParams = false;
                    break;
                }
                else {
                    listOfDirs.add(args[i]);
                }
            }

            if (correctParams) {
                FileController fc = new FileController();
                ArrayList<String> listOfMP3Paths = new ArrayList<>();
                listOfMP3Paths = fc.getListOfFilePaths(listOfDirs);
                TreeSet<MP3FileData> listOfMP3Data = new TreeSet<>();
                MP3Finder finder = new MP3Finder();
                listOfMP3Data = finder.getMP3DataList(listOfMP3Paths);

                HashMap<String, HashSet<MP3FileData>> listOfDublicatesByControlSum = new HashMap<>();
                HashMap<String, ArrayList<MP3FileData>> listOfDublicatesByNames = new HashMap<>();

                listOfDublicatesByNames = finder.getDublicatesByNames(listOfMP3Data);
                listOfDublicatesByControlSum = finder.getDublicatesByControlSum(listOfMP3Data);

                LogController logController = new LogController();
                logController.logDublicatesByControlSum(listOfDublicatesByControlSum);
                logController.logDublicatesByNames(listOfDublicatesByNames);


                HTMLController htmlController = new HTMLController();
                htmlController.createHTML(listOfMP3Data);
            }
            else {
                System.out.println("Your params are incorrect");
            }
        }
    }
}
