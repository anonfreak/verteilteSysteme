package de.dhbw.verteiltesysteme.aufgabe2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class TimeService extends UnicastRemoteObject implements TimeService_Interface {

    protected TimeService() throws RemoteException {
    }


    @Override
    public Date getDateAndTime() throws RemoteException {
        Date date = new Date();
        return date;
    }
}
