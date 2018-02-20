package de.dhbw.verteiltesysteme.aufgabe2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

public class TimeService_Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        TimeService_Interface timeService = (TimeService_Interface) Naming.lookup("rmi://127.0.0.1/TimeService");
        System.out.println(timeService.getDateAndTime());

        timeService.addEventListener(new EventHandler());
        Date date = new Date();
        date.setTime(date.getTime()+1000);
        timeService.addEvent(new Event(date, "Test1"));
        date.setTime(date.getTime()+2000);
        timeService.addEvent(new Event(date, "Test2"));
        date.setTime(date.getTime()+3000);
        timeService.addEvent(new Event(date, "Test3"));
    }
}
