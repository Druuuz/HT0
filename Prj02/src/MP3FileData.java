import org.jetbrains.annotations.NotNull;

public class MP3FileData {
    private int duration;
    private String filePath;
    private String album;
    private String artist;
    private String title;
    private String controlSum;

    public MP3FileData(String filePath, String album, String artist, String title, int duration, String controlSum) {
        this.album = album;
        this.artist = artist;
        this.filePath = filePath;
        this.duration = duration;
        this.title = title;
        this.controlSum = controlSum;
    }

    public String getControlSum() {
        return this.controlSum;
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getAlbum() {
        return this.album;
    }

    public int getDuration() {
        return this.duration;
    }


}
