import java.util.Comparator;

/**
 * Comparator for list of MP3 Files
 * Compare by album
 */
public class AlbumComparator implements Comparator<MP3FileData> {
    public int compare(MP3FileData one, MP3FileData another) {

        return one.getAlbum().compareTo(another.getAlbum());
    }
}
