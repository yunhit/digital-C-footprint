package application;

import java.io.*;

public class Storage {
    private static final String SEED_FILE_PATH = "seed.txt";
    private static final String TREE_FILE_PATH = "tree.txt";

    public static int loadSeedCount() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SEED_FILE_PATH))) {
            String line = reader.readLine();
            return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {
            // 파일이 없거나 읽을 수 없는 경우, 또는 숫자로 변환할 수 없는 경우
            // 기본값 300
            return 300;
        }
    }

    public static void saveSeedCount(int seedCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SEED_FILE_PATH))) {
            writer.write(String.valueOf(seedCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static int loadTreeCount() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TREE_FILE_PATH))) {
            String line = reader.readLine();
            return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    public static void saveTreeCount(int treeCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TREE_FILE_PATH))) {
            writer.write(String.valueOf(treeCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
