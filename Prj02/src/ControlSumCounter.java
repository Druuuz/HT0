import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for counting control sum of files
 */
public class ControlSumCounter {

    /**
     * algorithm for getting hash of file
     */
    private String algorithm;

    public ControlSumCounter(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     *
     * @param filename is filepath for count control sum
     * @return control sum of file in String format
     */
    public String Count(String filename) {
        try {
            final MessageDigest md = MessageDigest.getInstance(algorithm);
            final FileInputStream fis = new FileInputStream(filename);
            byte[] dataBytes = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(dataBytes)) > 0) {
                md.update(dataBytes, 0, bytesRead);
            }
            byte[] mdBytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mdBytes.length; i++) {
                sb.append(Integer.toString((mdBytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return sb.toString();
        } catch (FileNotFoundException | NoSuchAlgorithmException ex) {
            System.out.println("Unexpected algoritm or file not found");
        } catch (IOException ex) {
            System.out.println("File not found");
        }
        return "";
    }
}
