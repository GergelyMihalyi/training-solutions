package week13d04;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Map;

public class TemplateEngine {

    public void merge(BufferedReader reader, Map<String, Object> values, BufferedWriter writer) {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String merged = merge(line, values);
                writer.write(merged);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }

    private String merge(String line, Map<String, Object> values) {
        int index = 0;
        String result = line;
        while (line.indexOf("{", index) > 0) {
            int indexOfOpen = result.indexOf("{", index);
            int indexOfClose = result.indexOf("}", index);
            String key = result.substring(indexOfOpen + 1, indexOfClose);
            String value = values.get(key).toString();
            result = result.replace("{" + key + "}", value);
            index = indexOfClose;
        }
        return result + "\n";

    }

    public static void main(String[] args) {
        Path template = Path.of("src/main/resources/week13d04/template.txt");
        Map<String, Object> values =
                Map.of("nev", "John Doe",
                        "datum", LocalDate.of(2021, 2, 20),
                        "osszeg", 456.12,
                        "hatarido", LocalDate.of(2021, 3, 23));
        StringWriter stringWriter = new StringWriter();

        try (
                BufferedWriter writer = new BufferedWriter(stringWriter);
                BufferedReader reader = Files.newBufferedReader(template)) {

            new TemplateEngine().merge(reader, values, writer);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
        System.out.println(stringWriter.toString());

    }
}
