import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите папку c установленной игрой");
        String installDirectory = scanner.nextLine();
        scanner.close();

        Loader loader = new Loader();
        ArrayList<String> unzipFiles = loader.openZip(installDirectory + "/savegames/zip.zip"
                                                        , installDirectory + "/savegames");
        for (String filePath : unzipFiles) {
            GameProgress progress = loader.loadProgress(filePath);
            System.out.println(progress);
        }
    }
}