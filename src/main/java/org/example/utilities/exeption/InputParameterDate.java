package org.example.utilities.exeption;

import org.example.utilities.Writer;

import java.io.IOException;

public class InputParameterDate extends RuntimeException{
    public InputParameterDate(String e) throws IOException {
        super(e);
        String str = "{\n" +
                "    \"type\": \"error\",\n" +
                "    \"message\": \"Неправильный формат даты\"\n" +
                "}\n";
        Writer.writeTest(str);
    }
}
