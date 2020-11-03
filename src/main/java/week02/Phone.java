package week02;

import java.util.Scanner;

public class Phone {
    private int mem;
    private String type;

    public Phone(String type, int mem) {
        this.type = type;
        this.mem = mem;
    }

    public int getMem() {
        return mem;
    }

    public String getType() {
        return type;
    }

    public void setMem(int mem) {
        this.mem = mem;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your memory type: ");
        String mtype = scanner.nextLine();

        System.out.println("Please enter your memory size: ");
        int mmem = scanner.nextInt();

        Phone phone = new Phone(mtype, mmem);

        System.out.println(phone.getType());
        System.out.println(phone.getMem());

    }

}
