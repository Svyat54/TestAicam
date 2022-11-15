package org.example.utilities.exeption;

import org.example.utilities.Writer;
import java.io.IOException;

public class InputOutputName extends RuntimeException{
    public InputOutputName(String e) throws IOException {
        super(e);
        String str = "{\n" +
                "    \"type\": \"error\",\n" +
                "    \"message\": \"Некорректный файл\"\n" +
                "}\n";
        Writer.writeTest(str);
    }
}
