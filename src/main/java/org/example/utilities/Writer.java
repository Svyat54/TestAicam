package org.example.utilities;

import org.example.entities.responseEntities.ResponseJsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    static String output = "output.json";

    public static void writeJsonToFile(ResponseJsonObject json) throws IOException {
        FileWriter fw = new FileWriter(output);
        fw.write(json.toString());
        fw.close();
    }
}
