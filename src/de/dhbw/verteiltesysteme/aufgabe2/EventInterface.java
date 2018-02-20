package de.dhbw.verteiltesysteme.aufgabe2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface EventInterface extends Remote {
    Date getDate() throws RemoteException;

    void setDate(Date date) throws RemoteException;

    String getDescription() throws RemoteException;

    void setDescription(String description) throws RemoteException;
}
