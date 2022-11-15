package org.example.exeption;

import org.example.utilities.Writer;

import java.io.IOException;

public class InputParameterName extends RuntimeException{
    public InputParameterName(String e) throws IOException {
        super(e);
        String str = "{\n" +
                "    \"type\": \"error\",\n" +
                "    \"message\": \"Некорректное название параметра запроса\"\n" +
                "}\n";
        Writer.writeTest(str);
    }
}
