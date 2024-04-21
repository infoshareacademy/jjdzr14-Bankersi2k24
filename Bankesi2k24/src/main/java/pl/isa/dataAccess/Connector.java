package pl.isa.dataAccess;

import com.fasterxml.jackson.databind.JsonNode;
import pl.isa.model.PlainOldJavaObject;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Connector {
    /**
     * class to provide access to data
     * at this phase, we're writing objects in JSON format to a .txt file
     */

    private static final String fileName = "users.txt";
    private Path pathToFile;
    private File database;

    public Connector() {
        // TODO: check first if file exists, if not create it
        // this should point to <root>/resources/users.txt
        this.pathToFile = Paths.get(System.getProperty("user.dir"), fileName );
        System.out.println(this.pathToFile);
        this.database = new File(this.pathToFile.toString());
        if (!this.database.exists()){
            // file does not exist, create an empty one
            try{
                this.database.createNewFile();

            } catch (IOException e) {
                // TODO: add some sprt opf logging
                throw new RuntimeException(e);
            }
        }

    }

    public boolean save(Object o){
        /**
         * method stub for saving to database
         */
        return saveJson(o.toString());
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
            return (this.pathToFile == Files.writeString(this.pathToFile, json, StandardOpenOption.APPEND));
        } catch (IOException e) {
            //TODO: handle all the exceptions for files.writestring ...
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    public List<Object> read(){
//        //tmp
//        return this.readJsonFile();
//    }

    public String read()  {
        StringBuilder builder = new StringBuilder();

        // try block to check for exceptions where
        // object of BufferedReader class us created
        // to read filepath
        try (BufferedReader buffer = new BufferedReader(
                new FileReader(this.pathToFile.toString()))) {
            String str;

            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }


}
