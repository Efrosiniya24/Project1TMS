import java.io.*;

import Model.Files;
import Parser.FileParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Scanner sc = new Scanner(System.in);
        int operation = 0;

        while (true) {
            System.out.println("Меню:\n 1)Парсинг файлов\n 2)Вывод всех переводов из файла отчета\n 3)Выход");
            try {
                operation = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Введите целое число");
            }
            switch (operation) {
                case 1 -> {
                    if(Files.fileSearch()) {
                        FileParser.readFiles();
                        FileParser.createFile();
                        FileParser.newFiles();
                    }
                }
                case 2 -> FileParser.readFile();
                case 3 -> {return;}
            }
        }


    }
}