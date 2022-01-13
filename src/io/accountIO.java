package io;

import java.io.FileNotFoundException;
import java.util.List;

public class accountIO {
    public double readAccount(String filePath) throws FileNotFoundException {
        List<String> Lines = CustomFileReader.readFile(filePath);

        return Double.parseDouble(Lines.get(0));

    }
}
