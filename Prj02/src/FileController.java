import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for work with files
 */
public class FileController {
    /**
     * List of file paths
     */
    private ArrayList<String> listOfFilePaths;
    /**
     * List of files with mp3 extension
     */
    private static ArrayList<File> files = new ArrayList<>();

    /**
     *
     * @param listOfDirPath list of directories to find files in it
     * @return list of mp3 file paths
     */
    public ArrayList<String> getListOfFilePaths(ArrayList<String> listOfDirPath) {
        listOfFilePaths = new ArrayList<>();
        for (String dirPath : listOfDirPath) {
            File directory = new File(dirPath);
            try {
                files = list(directory);
            } catch (IOException e) {
            }

            for (int i = 0; i < files.size(); i++) {
                listOfFilePaths.add(files.get(i).getPath());
            }
        }
        return listOfFilePaths;
    }

    public ArrayList<File> list(File dir) throws IOException {
        File[] currentSeekingFolderContent = dir.listFiles();
        for (File file : currentSeekingFolderContent) {
            if (file.isFile()) {
                if (getFileExtension(file).equals("mp3")) {
                    files.add(file);
                }
            }
            if (file.isDirectory()) {
                list(file);
            }
        }
        return files;
    }

    /**
     *
     * @param file is file when checking extension
     * @return extension of file in String format
     */
    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

}
