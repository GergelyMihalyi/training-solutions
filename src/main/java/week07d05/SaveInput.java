package week07d05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveInput {
    private Scanner scanner;

    //  Dependency injection
    public SaveInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readLines(){
        List<String> lines = new ArrayList<>();
        for(int i = 1; i<4 ;i++){
            System.out.println("Enter the " + i + ". line");
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    private Path readFileName(){
        System.out.println("Enter the file name");
        String fileName = scanner.nextLine();
        Path path = Path.of(fileName);
        return path;
    }

    public void write(Path path, List<String> lines){
        try{
            Files.write(path,lines);
        }catch (IOException ioe){
            throw new IllegalArgumentException("Can not write file",ioe);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SaveInput saveInput = new SaveInput(scanner);
        List<String> lines = saveInput.readLines();
        Path path = saveInput.readFileName();
        saveInput.write(path,lines);
    }
}
