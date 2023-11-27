package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int operation = 0;

        while (true) {
            System.out.println("Меню: 1)Парсинг файлов 2)Вывод всех переводов из файла отчета 3)Выход");
            try {
                operation = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Введите целое число");
            }
            switch (operation) {
                case 1 -> Files.fileSearch();
                case 2 -> ;
                case 3 -> {return;}
            }
        }


    }
}