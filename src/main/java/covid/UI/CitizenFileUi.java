package covid.UI;

import java.nio.file.Path;

public class CitizenFileUi extends CitizenUserInterface{

    public Path filePath() {
        printOutUi("Please enter file path: ");
        return Path.of(scanner.nextLine());
    }
}
