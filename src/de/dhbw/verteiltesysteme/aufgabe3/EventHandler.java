package de.verteiltesysteme;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EventHandler extends UnicastRemoteObject implements  EventListener {
    protected EventHandler() throws RemoteException {
    }

    @Override
    public void handleEvent(Event e) throws RemoteException{
        System.out.println(e.getDescription() + "was triggered");
    }
}