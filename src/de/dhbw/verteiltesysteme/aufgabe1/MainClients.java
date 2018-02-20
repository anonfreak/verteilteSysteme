package de.dhbw.verteiltesysteme.aufgabe1;

import java.io.IOException;

public class MainClients {
    public static void main(String[] args) {
        try {
            System.out.println(TimeServiceClient.dateFromServer("127.0.0.1"));
            System.out.println(TimeServiceClient.timeFromServer("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
