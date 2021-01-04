package week07d04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShoppingList {

    public static final String SEPARATOR = ";";
    public static final int INDEX_OF_AMOUNT = 1;
    public static final int INDEX_OF_PRICE = 2;

    public long calculateSum(){
            long sum = 0;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    ShoppingList.class.getResourceAsStream("list.txt")))) {
                String line;
                while((line = reader.readLine()) != null) {
                    sum += lineSum(line);
                }
            }
            catch (IOException ioe) {
                throw new IllegalStateException("Can not read file", ioe);
            }
            return sum;
        }

    private long lineSum(String line){
        String[] parts = line.split(SEPARATOR);
        long amount = Long.parseLong(parts[INDEX_OF_AMOUNT]);
        long price = Long.parseLong(parts[INDEX_OF_PRICE]);

        long result = amount * price;
        return result;
    }

}
