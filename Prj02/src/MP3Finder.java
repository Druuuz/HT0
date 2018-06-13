import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.XMPDM;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Class to find dublicates, getting info about mp3 file.
 */
public class MP3Finder {
    private Comparator<MP3FileData> comparator = new ArtistComparator().thenComparing(new AlbumComparator()).thenComparing(new PathComparator());
    private TreeSet<MP3FileData> listOfMP3;
    private HashMap<String, HashSet<MP3FileData>> listOfDublicatesByControlSum;
    public HashMap<String, ArrayList<MP3FileData>> listOfDublicatesByNames;


    public MP3Finder() {
        listOfMP3 = new TreeSet<MP3FileData>(comparator);
        listOfDublicatesByControlSum = new HashMap<>();
        listOfDublicatesByNames = new HashMap<>();

    }

    /**
     * finds dublicates , where control sum equal
     * @param listOfData list of MP3 files data
     * @return hashMap of dublicates
     */
    public HashMap<String, HashSet<MP3FileData>> getDublicatesByControlSum(TreeSet<MP3FileData> listOfData) {

        for (MP3FileData data : listOfData) {

            if (listOfDublicatesByControlSum.containsKey(data.getControlSum())) {
                listOfDublicatesByControlSum.get(data.getControlSum()).add(data);
            }
            else {
                listOfDublicatesByControlSum.put(data.getControlSum(), new HashSet<MP3FileData>());
                listOfDublicatesByControlSum.get(data.getControlSum()).add(data);
            }
        }
        ArrayList<String> keysToDelete = new ArrayList<>();
        for (String key : listOfDublicatesByControlSum.keySet()) {
            if (listOfDublicatesByControlSum.get(key).size() == 1) {
                keysToDelete.add(key);
            }
        }
        for (String key : keysToDelete) {
            listOfDublicatesByControlSum.remove(key);
        }
        return listOfDublicatesByControlSum;

    }

    /**
     * finds dublicates , where artist, album and name equal
     * @param listOfData list of MP3 files data
     * @return hashMap of dublicates
     */
    public HashMap<String, ArrayList<MP3FileData>> getDublicatesByNames(TreeSet<MP3FileData> listOfData) {
        StringBuilder currentKey;
        for (MP3FileData data : listOfData) {
            currentKey = new StringBuilder();
            currentKey.append(data.getAlbum() + data.getArtist() + data.getTitle());
            if (listOfDublicatesByNames.containsKey(currentKey.toString())) {
                listOfDublicatesByNames.get(currentKey.toString()).add(data);
            }
            else {
                listOfDublicatesByNames.put(currentKey.toString(), new ArrayList<>());
                listOfDublicatesByNames.get(currentKey.toString()).add(data);
            }
        }
        ArrayList<String> keysToDelete = new ArrayList<>();
        for (String key : listOfDublicatesByNames.keySet()) {
            if (listOfDublicatesByNames.get(key).size() == 1) {
                keysToDelete.add(key);
            }
        }
        for (String key : keysToDelete) {
            listOfDublicatesByNames.remove(key);
        }
        return listOfDublicatesByNames;

    }

    /**
     * getting info about mp3 files
     * @param listOfFilePaths list of MP3 file paths
     * @return list of MP3 file meta data
     */
    public TreeSet<MP3FileData> getMP3DataList(ArrayList<String> listOfFilePaths) {
        TreeSet<MP3FileData> listOfMP3 = new TreeSet(comparator);
        Metadata metadata;
        for (String path : listOfFilePaths) {
            try {
                InputStream input = new FileInputStream(new File(path));
                ContentHandler handler = new DefaultHandler();
                metadata = new Metadata();
                Parser parser = new Mp3Parser();
                ParseContext parseCtx = new ParseContext();
                parser.parse(input, handler, metadata, parseCtx);
                input.close();

                int duration = 0;
                if (metadata.get("title") == null) {
                    metadata.set("title", "undefTitle");
                }
                if (metadata.get("xmpDM:album") == null) {
                    metadata.set("xmpDM:album", "undefAlbum");
                }
                if (metadata.get("xmpDM:artist") == null) {
                    metadata.set("xmpDM:artist", "undefArtist");
                }

                if (metadata.get(XMPDM.DURATION) == null) {
                    duration = 0;
                }
                else {
                    duration = Math.round(Float.valueOf(metadata.get(XMPDM.DURATION)) / 1000);
                }

                ControlSumCounter controlSumCounter = new ControlSumCounter("SHA-1");
                listOfMP3.add(new MP3FileData(path, metadata.get("xmpDM:album"), metadata.get("xmpDM:artist"), metadata.get("title"), duration, controlSumCounter.Count(path)));
            } catch (IOException e) {
                System.out.println("Error with input file");
            } catch (TikaException e) {
                System.out.println("Error with getting info about file");
            } catch (SAXException e) {
                System.out.println("Error with parse file");
            } catch (NullPointerException e) {
                System.out.println("Error with getting metadata of file");
            }
        }

        return listOfMP3;
    }


}
