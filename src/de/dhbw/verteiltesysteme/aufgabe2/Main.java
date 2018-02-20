package de.dhbw.verteiltesysteme.aufgabe2;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        TimeService timeService = new TimeService();
        registry.bind("TimeService", timeService);
        System.out.println("TimeService started");
    }
}
