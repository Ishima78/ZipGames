import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game = new GameProgress(100, 2, 3, 100);
        GameProgress game1 = new GameProgress(90, 4, 5, 130);
        GameProgress game2 = new GameProgress(50, 5, 10, 200);
        saveGame("D://Games/savegames/save.dat", game);
        saveGame("D:/Games/savegames/save1.dat", game1);
        saveGame("D:/Games/savegames/save2.dat", game2);

        zipFiles("D://Games/savegames/sds.zip", "D://Games/savegames/save.dat", "save.dat");
        zipFiles("D://Games/savegames/sds1.zip", "D://Games/savegames/save1.dat", "save1.dat");
        zipFiles("D://Games/savegames/sds2.zip", "D://Games/savegames/save2.dat", "save2.dat");

        deleteFile("D://Games/savegames/save.dat");
        deleteFile("D://Games/savegames/save1.dat");
        deleteFile("D://Games/savegames/save2.dat");

    }

    public static void saveGame(String pathFile, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(pathFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String pathZipFiles, String pathSaveFile, String nameZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(pathZipFiles));
             FileInputStream fis = new FileInputStream(pathSaveFile)) {
            ZipEntry entry = new ZipEntry(nameZip);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            System.out.println("Удален");
        } else {
            System.out.println("Ошибка удаления");
        }
    }
}