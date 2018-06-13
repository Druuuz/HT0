import java.util.Comparator;

/**
 * Comparator for list of MP3 Files
 * Compare by artist
 */
public class ArtistComparator implements Comparator<MP3FileData> {
    public int compare(MP3FileData one, MP3FileData another) {

        return one.getArtist().compareTo(another.getArtist());
    }
}
