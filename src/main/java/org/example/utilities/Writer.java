package org.example.utilities;

import org.example.entities.responseEntities.stat.ResponseJsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    static String output = "output.json";

    public static void writeJsonToFile(Object json) throws IOException {
        FileWriter fw = new FileWriter(output);
        fw.write(json.toString());
        fw.close();
    }

    public static void writeTest(String str) throws IOException {
        FileWriter fw = new FileWriter(output);
        fw.write(str);
        fw.close();
    }
}
