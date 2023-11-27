package Model;

import java.io.*;
import java.util.*;

import Pattern.AccountPattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Files {
    public static List<String> files = new ArrayList<>();

    public static boolean fileSearch() {

        File file = new File("src/main/resources/input");
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".txt");
            }
        };
        files = List.of(Objects.requireNonNull(file.list(filter)));
        if(files.isEmpty()) {
            System.out.println("Файлов формата .txt нет");
            return false;
        }
        else {
            System.out.println("По вашему запросу найдено следующее количество файлов: " + files.size());
            return true;
        }
    }

}
