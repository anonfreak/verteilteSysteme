package de.dhbw.verteiltesysteme.aufgabe2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

public class TimeService_Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        TimeService_Interface timeService = (TimeService_Interface) Naming.lookup("rmi://127.0.0.1/TimeService");
        Date date = timeService.getDateAndTime();
        System.out.println(date.toString());
    }
}
