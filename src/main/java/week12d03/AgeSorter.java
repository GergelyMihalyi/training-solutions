package week12d03;

import java.util.Arrays;

public class AgeSorter {
    public int[] sortAges(int[] ages) {
        int support;
        for (int x = 0 ; x < ages.length ; x++) {
            for (int i = 0; i < ages.length - 1; i++) {
                while(ages[i + 1] < ages[i]){
                    support = ages[i];
                    ages[i] = ages[i + 1];
                    ages[i + 1] = support;
                }
            }
        }
        return ages;
    }

    public static void main(String[] args) {
        int[] someArray = {9, 4, 5, 6, 7, 8, 1, 2, 3,33,43,22,11,32,22,0,12,32,43,65,76,132};
        System.out.println(Arrays.toString(new AgeSorter().sortAges(someArray)));
    }
}
