import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Class for logging info
 */
public class LogController {

    /**
     * logging string
     * @param logString is string to log
     */
    private void log(String logString) {
        System.setProperty("log4j.configurationFile", "C:\\Users\\user\\IdeaProjects\\hmtsk2\\src\\resources\\log4j2.xml");
        Logger logger = LogManager.getRootLogger();
        logger.info(logString);
    }

    /**
     * parse list of dublicates and log them
     * @param list of files , where artist, album and name are equal;
     */
    public void logDublicatesByNames(HashMap<String, ArrayList<MP3FileData>> list) {
        if (list.size() != 0) {
            log("Dublicates by Names:");
            MP3FileData currentFileData;
            for (String mainKey : list.keySet()) {
                currentFileData = list.get(mainKey).get(0);
                log("Artist " + currentFileData.getArtist() + ", Album " + currentFileData.getAlbum() + ", Title " + currentFileData.getTitle());
                for (MP3FileData data : list.get(mainKey)) {
                    log("+ " + data.getFilePath());
                }
            }
        }

    }

    /**
     * parse list of dublicate and log them
     * @param list of files , where control sum equal;
     */
    public void logDublicatesByControlSum(HashMap<String, HashSet<MP3FileData>> list) {
        if (list.size() != 0) {
            log("Dublicates by ControlSum:");
            int counterOfLists = 0;
            for (String key : list.keySet()) {
                counterOfLists++;
                log("Dublicates " + counterOfLists);
                for (MP3FileData data : list.get(key)) {
                    log("+ " + data.getFilePath());
                }
            }
        }

    }
}
