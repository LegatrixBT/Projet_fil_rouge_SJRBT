package Boundary;

import java.util.Scanner;

public class Clavier {
    private Scanner scanner = new Scanner(System.in);

    public int entrerClavierInt() {
        return this.scanner.nextInt();
    }

    public String entrerClavierString() {
        return this.scanner.next();
    }

}
