import java.util.Comparator;

/**
 * Comparator for list of MP3 Files
 * Compare by file path
 */
public class PathComparator implements Comparator<MP3FileData> {
    public int compare(MP3FileData one, MP3FileData another) {
        return one.getFilePath().compareTo(another.getFilePath());
    }
}
