package de.verteiltesysteme;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EventListener extends Remote {
    public void handleEvent(Event e) throws RemoteException;
}