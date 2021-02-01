package week13d01;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Cities {


    public City findFirst(BufferedReader reader){
        List<City> cities = readCities(reader);
        cities.sort(new NameComparator());
        return cities.get(0);
    }

    private City parseCity(String line){
        String[] parts = line.split(";");
        String part = parts.length>2 ? parts[2] : null;
        City city = new City(parts[0],parts[1],part);
        return city;
    }

    private List<City> readCities(BufferedReader reader){
        List<City> cities = new ArrayList<>();
        String line;
        try {
            skipFirstLine(reader);
            while((line = reader.readLine()) != null){
                City city = parseCity(line);
                cities.add(city);
            }
        }catch (IOException ioe){
            throw new IllegalStateException("Can not read file",ioe);
        }
        return cities;
    }

    public void skipFirstLine(BufferedReader reader) throws IOException{
        reader.readLine();
    }

    public static void main(String[] args) {
        Cities cities = new Cities();
        try(BufferedReader reader = Files.newBufferedReader((Path.of("src/main/resources/week13d01/iranyitoszamok-varosok-2021.csv")))){
            City first = cities.findFirst(reader);
            System.out.println(first);
        }catch(IOException ioe){
            throw new IllegalStateException("Can not read file",ioe);
        }
    }
}
