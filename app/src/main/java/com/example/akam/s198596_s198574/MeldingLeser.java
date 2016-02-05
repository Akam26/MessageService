package com.example.akam.s198596_s198574;

import android.graphics.Path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Akam on 23.10.15.
 */
public class MeldingLeser {

    private static final String FILE_PATH = "./write-to-file.txt";
    private URI fileLocation;

    public void setUp() throws URISyntaxException {
        fileLocation = this.getClass().getClassLoader().getResource(FILE_PATH).toURI();

        /****************
        String file = heapFile.getAbsolutePath();
        FileWriter heapFileWritter = new FileWriter(file, true);*/
    }
    public void skrivTilFil(String mld) throws IOException {
        File file = new File(fileLocation);

        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(mld);
        out.close();

    }
    //*****************************************


    public void convert_file_to_string() throws IOException {

        File file = new File(fileLocation);

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuffer fileContents = new StringBuffer();
        String line = br.readLine();
        while (line != null) {
            fileContents.append(line);
            line = br.readLine();
        }

        br.close();

        //assertEquals("File to string example", fileContents.toString());
    }
}
