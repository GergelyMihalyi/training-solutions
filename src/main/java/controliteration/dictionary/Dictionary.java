package controliteration.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<DictionaryItem> dictionary = new ArrayList<>();

    public void addItem(String word, List<String> translations) {
        if(findTranslations(word).size() == 0){
            DictionaryItem item = new DictionaryItem(word,translations);
            dictionary.add(item);
        }else{
            for (DictionaryItem item : dictionary) {
                if (item.getWord().equals(word)) {
                    item.addTranslation(translations);
                };
            }
        }


    }

    public List<String> findTranslations(String word) {
        List<String> translations = new ArrayList<>();
        for (DictionaryItem item : dictionary) {
            if (item.getWord().equals(word)) translations = item.getTranslations();
        }
        return translations;
    }
}
