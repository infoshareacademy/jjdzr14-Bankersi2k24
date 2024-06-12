package com.isa.Bankersi2k24.dataAccess;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Connector {
    /**
     * class to provide access to data
     * at this phase, we're writing objects in JSON format to a .txt file
     */

    private static Path PATH_TO_FILE =null;

    public Connector(String fileName) {
        PATH_TO_FILE = Paths.get(System.getProperty("user.dir"), fileName);

        File database = new File(PATH_TO_FILE.toString());
        if (!database.exists()){
            try{
                database.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean saveJson(String json) {
        /**
         * method to store json in a text file
         * @param json
         * is the content to be saved into DB (txt file)
         *
         * @return true if success, false otherwise
         * see debug logs for details
         *
         */
        try {
            FileWriter fw = new FileWriter(PATH_TO_FILE.toFile());
            fw.write(json);
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String read(){
        StringBuilder builder = new StringBuilder();
        try {
            FileReader fr = new FileReader(PATH_TO_FILE.toString());
            BufferedReader buffer = new BufferedReader(fr);
            String str;
            while ((str = buffer.readLine()) != null) {
                builder.append(str);
            }
            fr.close();
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
