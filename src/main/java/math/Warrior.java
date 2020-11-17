package math;

import java.util.Random;

public class Warrior {
    private String name;
    private int stamina;
    private double skill;
    private Point position;
    private final Random rnd = new Random();

    public Warrior(String name, Point position) {
        this.name = name;
        this.position = position;
        stamina = rnd.nextInt(81) + 20;
        skill = rnd.nextDouble();
    }

    public void move(Warrior warrior) {
        int newX = position.getX();
        int newY = position.getY();
        int possibleX = warrior.getPosition().getX() - position.getX();
        int possibleY = warrior.getPosition().getY() - position.getY();
        if (possibleX != 0) {
            if (possibleX > 0) {
                newX++;
            } else {
                newX--;
            }
        }

        if (possibleY != 0) {
            if (possibleY > 0) {
                newY++;
            } else {
                newY--;
            }
        }

        position = new Point(newX, newY);
    }

    public void attack(Warrior warrior) {
        if (rnd.nextDouble() < skill) {
            warrior.stamina -= rnd.nextInt(3) + 1;
        }
    }

    public double distance(Warrior warrior) {
        return position.distance(warrior.position);

    }

    public boolean isAlive() {
        boolean alive = stamina > 0 ? true : false;
        return alive;
    }

    @Override
    public String toString() {
        return name + ": (" + position.getX() + "," + position.getY() + ") " + stamina;
    }

    public Point getPosition() {
        return position;
    }
}
