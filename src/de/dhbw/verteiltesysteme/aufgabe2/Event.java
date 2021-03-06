package de.dhbw.verteiltesysteme.aufgabe2;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Event implements Serializable{
    private Date date;
    private String description;

    public Event(Date date, String description) {
        super();
        this.date = date;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
