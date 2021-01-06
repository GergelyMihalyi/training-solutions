package exam02.lotto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LottoReader {
    int[] arrayOfLottoNumbers = new int[90];

    public LottoReader(InputStream inputStream) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(";");
                for (int i = 11; i < 16; i++){
                    int number = Integer.parseInt(parts[i-1]);
                    arrayOfLottoNumbers[number]++;
                }
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can not read file", ioe);
        }
    }

    public int getNumber(int number){
        return arrayOfLottoNumbers[number-1];
    }

}
