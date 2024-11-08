package store.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileLoad {
    public static List<String> LoadFile(String path) {
        File file = new File(path);
        List<String> lines = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine();
            while((line = br.readLine())!=null)
                lines.add(line);
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
