package controliteration.dictionary;

import java.util.ArrayList;
import java.util.List;

public class DictionaryItem {
    private String word;
    private List<String> translations = new ArrayList<>();

    public DictionaryItem(String word, List<String> translations) {
        this.word = word;
        addTranslation(translations);
    }

    public void addTranslation(List<String> words){
        for(String word : words){
            if(!translations.contains(word)){
                translations.add(word);
            }
        }
    }

    public String getWord() {
        return word;
    }

    public List<String> getTranslations() {
        return translations;
    }
}
