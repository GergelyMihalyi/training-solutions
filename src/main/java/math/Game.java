package math;

public class Game {
    public static void main(String[] args) {
        Warrior warrior1 = new Warrior("M'ih", new Point(14, 2));
        Warrior warrior2 = new Warrior("Brugath", new Point(2, 14));
        int round = 1;
        do {
            if (warrior1.distance(warrior2) > 0) {
                warrior1.move(warrior2);
                warrior2.move(warrior1);
            }

            warrior1.attack(warrior2);
            if (warrior2.isAlive()) {
                warrior2.attack(warrior1);
            }
            System.out.println("Round " + round);
            System.out.println(warrior1);
            System.out.println(warrior2);
            round++;
        }
        while (warrior1.isAlive() && warrior2.isAlive());

        System.out.println("Winner: " + (warrior1.isAlive() ? warrior1 : warrior2));
    }
}
