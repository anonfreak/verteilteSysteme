package de.verteiltesysteme;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public interface TimeService_Interface extends Remote{
    public abstract Date getDateAndTime() throws RemoteException;
    public abstract void addEvent(Event e) throws RemoteException;
    public abstract List<Event> getAllEvents() throws RemoteException;
    public abstract Event getNextEvent() throws RemoteException;
    public abstract List<Event> getFutureEvents() throws RemoteException;
    public abstract void addEventListener(EventListener listener) throws RemoteException;
    public abstract void removeEventListener(EventListener listener) throws RemoteException;
}
