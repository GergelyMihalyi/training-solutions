package week06d03;

import java.util.Arrays;
import java.util.IllegalFormatPrecisionException;
import java.util.List;

public class Series {

    public Type calculateSeriesType(List<Integer> numbers) {
        checkList(numbers);

        Type type = !isIncrement(numbers, 1) ? Type.INCREASE : Type.DECREASE;

        for (int i = 2; i < numbers.size(); i++) {
            numberCheck(numbers,i);
            if (!isIncrement(numbers, i) && type == Type.INCREASE || isIncrement(numbers, i) && type == Type.DECREASE) {
                type = Type.MIXED;
            }
        }

        return type;
    }

    private void checkList(List<Integer> numbers) {
        if (numbers == null || numbers.size() < 2) {
            throw new IllegalArgumentException("Too short list");
        }
    }

    private void numberCheck(List<Integer> numbers, int i) {
        if (numbers.get(i - 1) == numbers.get(i)) {
            throw new IllegalArgumentException("There can be no repetitions in the list");
        }
    }

    private boolean isIncrement(List<Integer> numbers, int i) {
        return numbers.get(i - 1) > numbers.get(i);
    }

}
