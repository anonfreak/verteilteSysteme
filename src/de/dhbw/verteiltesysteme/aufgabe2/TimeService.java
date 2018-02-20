package de.dhbw.verteiltesysteme.aufgabe2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

public class TimeService extends UnicastRemoteObject implements TimeService_Interface,Runnable {
    private Vector<Event> events = new Vector<Event>();
    private Vector<EventListener> listeners = new Vector<>();
    private Thread t;


    protected TimeService() throws RemoteException {
        t = new Thread(this, "listenerHelper");
        t.start();
    }


    @Override
    public Date getDateAndTime() throws RemoteException {
        Date date = new Date();
        return date;
    }

    @Override
    public void addEvent(Event e) throws RemoteException {
        events.add(e);
        events.sort(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        t.interrupt();
    }

    @Override
    public Vector<Event> getAllEvents() throws RemoteException {

        return events;
    }

    @Override
    public Event getNextEvent() throws RemoteException {
        Date date = new Date();
        for(Event e : events){
            if(e.getDate().after(date)){
                return e;
            }
        }
        return null;
    }

    @Override
    public Vector<Event> getFutureEvents() throws RemoteException {
        Vector<Event> eventsFuture = new Vector<Event>();
        Date date = new Date();
        for(Event e : events){
            if(e.getDate().after(date)){
                eventsFuture.add(e);
            }
        }
        return eventsFuture;
    }

    @Override
    public void addEventListener(EventListener listener) throws RemoteException {
        listeners.add(listener);
    }

    @Override
    public void removeEventListener(EventListener listener) throws RemoteException {
        listeners.remove(listener);
    }

    @Override
    public void run() {
        while(true){
            try {
                if(events.size() >0) {
                    Date date = new Date();
                    Event nextEvent = this.getNextEvent();
                    Thread.sleep(nextEvent.getDate().getTime() - date.getTime());
                    for (EventListener listener : listeners) {
                        listener.handleEvent(nextEvent);
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
