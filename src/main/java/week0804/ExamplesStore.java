package week0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExamplesStore {

    public static final String SEPARATOR = "# ";
    public static final int INDEX_OF_TITLE = 1;

    public List<String> getTitlesOfExamples() {
        List<String> titles = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                ExamplesStore.class.getResourceAsStream("examples.md")))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(SEPARATOR)) {
                    String[] parts = line.split(SEPARATOR);
                    titles.add(parts[INDEX_OF_TITLE]);
                }
            }

        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can not read file", ioe);
        }
        return titles;
    }

}
