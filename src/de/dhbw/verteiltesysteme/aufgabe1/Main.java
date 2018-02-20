package de.dhbw.verteiltesysteme.aufgabe1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            TimeService timeService = new TimeService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
