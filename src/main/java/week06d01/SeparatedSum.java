package week06d01;

public class SeparatedSum {

    public SumResult sum(String s) {
        if(s.isEmpty()){
            throw new NullPointerException("The list is empty");
        }
        double positive = 0;
        double negative = 0;

        String replaced = s.replace(",", ".");
        String[] parts = replaced.split(";");
        for (String part : parts) {
            double value = Double.parseDouble(part);

            if (value >= 0) {
                positive += value;
            } else {
                negative += value;
            }
        }
        return new SumResult(positive, negative);
    }

    public static void main(String[] args) {
        SumResult sum = new SeparatedSum().sum("56,67;6;8;3;-12;-16,7");
        System.out.println(sum);
    }
}
