package week11d04;

import java.util.*;

public class NameLength {

    public static final String FIRST = "J";

    public List<Integer> getLength(List<String> names){
        List<Integer> lengths = new ArrayList<>();
        Set<Integer> lengthOfNames = new HashSet<>();

        for(String name: names){
            if(name.toUpperCase().startsWith("J")){
                if(!lengths.contains(name.length())){
                    lengths.add(name.length());
                }
            }
        }
        return lengths;
    }

}
