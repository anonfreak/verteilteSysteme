package de.dhbw.verteiltesysteme.aufgabe2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface TimeService_Interface extends Remote{
    public abstract Date getDateAndTime() throws RemoteException;
}
