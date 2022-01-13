package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomFileReader {
    public static List<String> readFile(String filePath) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(filePath);
        List<String > Lines = new ArrayList<>();

        Scanner scanner  = new Scanner(inputStream);
       while(scanner.hasNext()){
           Lines.add(scanner.nextLine());
       }
       return Lines;
    }
}
