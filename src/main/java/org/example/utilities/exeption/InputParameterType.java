package org.example.utilities.exeption;

import org.example.utilities.Writer;
import java.io.IOException;

public class InputParameterType extends RuntimeException{
    public InputParameterType(String e) throws IOException {
        super(e);
        String str = "{\n" +
                "    \"type\": \"error\",\n" +
                "    \"message\": \"Некорректный тип запроса\"\n" +
                "}\n";
        Writer.writeTest(str);
    }
}
