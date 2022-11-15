package org.example.exeption;

import org.example.utilities.Writer;

import java.io.IOException;

public class InputParameterValue extends RuntimeException{
    public InputParameterValue(String e) throws IOException {
        super(e);
        String str = "{\n" +
                "    \"type\": \"error\",\n" +
                "    \"message\": \"Некорректное значение параметра запроса\"\n" +
                "}\n";
        Writer.writeTest(str);
    }
}
