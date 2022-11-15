package org.example.exeption;

import org.example.utilities.Writer;

import java.io.IOException;

public class NotParsedJson extends RuntimeException{
    public NotParsedJson(String e) throws IOException {
        super(e);
        String str = "{\n" +
                "    \"type\": \"error\",\n" +
                "    \"message\": \"Неверный формат json\"\n" +
                "}\n";
        Writer.writeTest(str);
    }
}
