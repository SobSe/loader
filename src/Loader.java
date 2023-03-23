import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Loader {
    public ArrayList<String> openZip(String pathZipFile, String UnzipPath) {
        ArrayList<String> unzipFiles = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(pathZipFile);
             ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry zipEntry;
            String name;
            byte[] buffer = new byte[1024];
            int readBytes;
            while ((zipEntry = zis.getNextEntry()) != null) {
                name = zipEntry.getName();
                try (FileOutputStream fos = new FileOutputStream(UnzipPath + "/" + name)) {
                    while ((readBytes = zis.read(buffer)) != -1) {
                        fos.write(buffer, 0, readBytes);
                    }
                    fos.flush();
                    zis.closeEntry();
                    unzipFiles.add(UnzipPath + "/" + name);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String[] path = pathZipFile.split("/");
        String fileName = path[path.length - 1];
        File zipFile = new File(pathZipFile);
        if (zipFile.delete())
            System.out.println("Удален файл " + pathZipFile);
        return unzipFiles;
    }

    public GameProgress loadProgress(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)){
            return (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
