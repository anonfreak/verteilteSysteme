package de.verteiltesysteme;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TimeService extends UnicastRemoteObject implements TimeService_Interface,Runnable {
    private Vector<Event> events = new Vector<Event>();
    private Vector<EventListener> listeners = new Vector<>();
    private Thread t;
    private EntityManager em;
    private EntityManagerFactory emf;


    protected TimeService() throws RemoteException {
        t = new Thread(this, "listenerHelper");
        emf = Persistence.createEntityManagerFactory("timeservice");
        em = emf.createEntityManager();
        t.start();
    }


    @Override
    public Date getDateAndTime() throws RemoteException {
        Date date = new Date();
        return date;
    }

    @Override
    public void addEvent(Event e) throws RemoteException {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(new Event(e.getDate(), e.getDescription()));
        tx.commit();
        t.interrupt();
    }

    @Override
    public List<Event> getAllEvents() throws RemoteException {
    	List<Event> events = new Vector<Event>();
    	events = em.createQuery("SELECT e from event e ORDER BY e.date", Event.class).getResultList();
        return events;
    }

    @Override
    public Event getNextEvent() throws RemoteException {
    	List<Event> eventList = getFutureEvents();
    	if(eventList.size()>0){
    		return eventList.get(0);
    	}
        return null;
    }

    @Override
    public List<Event> getFutureEvents() throws RemoteException {
    	List<Event> eventList = em.createQuery("SELECT e from event e WHERE e.date > CURRENT_TIMESTAMP ORDER BY e.date", Event.class).getResultList();
        return eventList;
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
            	Event nextEvent = this.getNextEvent();
                if(nextEvent != null) {
                    Date date = new Date();
                    Thread.sleep(nextEvent.getDate().getTime() - date.getTime());
                    for (EventListener listener : listeners) {
                        listener.handleEvent(nextEvent);
                    }
                } else {
                	Thread.sleep(10);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

