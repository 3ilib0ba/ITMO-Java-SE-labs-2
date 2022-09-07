package commands;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import typesfiles.Flat;
import main.MyTreeMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Class for reading *.json files and taking flats from it.
 */
public class ReadCommand {
    /**
     * method for getting a map ith flats
     * @param name - name of file
     * @return map to merge with main MAP
     */
    public static TreeMap<Integer, Flat> readTheCollection(String name) {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(name));
            System.out.println(name);

            try (InputStreamReader isr = reader) {
                int ch;
                StringBuilder allText = new StringBuilder();
                while ((ch = isr.read()) != -1)
                    allText.append((char) ch);

                TreeMap<Integer, Flat> loadingCol;
                Type collectionType = new TypeToken<TreeMap<Integer, Flat>>() {
                }.getType();
                loadingCol = new Gson().fromJson(allText.toString(), collectionType);
                System.out.println("Collection loaded successfully!");
                return loadingCol;
            } catch (NoSuchElementException e) {
                System.out.println("the loaded file is empty!");
            } catch (JsonParseException | NullPointerException exception) {
                System.out.println("Wrong format in the file!");
            } catch (Exception e) {
                System.out.println("Error");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return new TreeMap<>();
    }

    /*public static void allchecking(MyTreeMap map) {

    } */
}
