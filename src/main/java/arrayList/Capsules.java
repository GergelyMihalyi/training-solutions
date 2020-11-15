package arrayList;

import java.util.ArrayList;
import java.util.List;

public class Capsules {
    private List<String> capsules = new ArrayList<>();

    public static void main(String[] args) {
        Capsules capsule = new Capsules();
        capsule.addLast("blue");
        capsule.addLast("yellow");
        capsule.addFirst("red");

        System.out.println(capsule.getColors());

        capsule.removeFirst();
        System.out.println(capsule.getColors());

        capsule.removeLast();
        System.out.println(capsule.getColors());

    }

    private void addLast(String lastItem) {
        capsules.add(lastItem);
    }

    private void addFirst(String firstItem) {
        capsules.add(0, firstItem);

    }

    private void removeFirst() {
        capsules.remove(0);

    }

    private void removeLast() {
        capsules.remove(capsules.size() - 1);

    }

    public List<String> getColors() {
        return capsules;
    }

}
