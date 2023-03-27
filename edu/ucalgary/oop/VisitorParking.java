package edu.ucalgary.oop;

import java.time.LocalDate;

public class VisitorParking {
    private String licence;
    private LocalDate date;

    public VisitorParking() {
        // this.licence = "";
        // this.date = LocalDate.now();
    }
    public VisitorParking(String licence, LocalDate date) throws IllegalArgumentException {
        try{
            this.licence = Parking.standardizeAndValidateLicence(licence);
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid licence plate");
        }
        if(date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if(date.compareTo(LocalDate.now()) < 0){
            throw new IllegalArgumentException("Date cannot be in the past");
        }
        else{
            this.date = date;
        }
    }
    public VisitorParking(String license) throws IllegalArgumentException{
        try{
            this.licence = Parking.standardizeAndValidateLicence(license);
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid licence plate");
        }
        this.date = LocalDate.now();
    }
    public void addVisitorReservation(String licence, LocalDate date) throws IllegalArgumentException{
        try{
            this.licence = Parking.standardizeAndValidateLicence(licence);
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid licence plate");
        }
        if(date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if(date.compareTo(LocalDate.now()) < 0){
            throw new IllegalArgumentException("Date cannot be in the past");
        }
        if(this.date != null){
            if(date.compareTo(this.date.plusDays(3)) <= 0){
                throw new IllegalArgumentException("Date cannot be within 3 days of current date.");
            }
        }
        else{
            this.date = date;
        }
    }
    public void addVisitorReservation(String licence) throws IllegalArgumentException{
        try{
            this.licence = Parking.standardizeAndValidateLicence(licence);
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid licence plate");
        }
        // Throws Error if today's date is within 3 days of the currently registered date.
        if(LocalDate.now().compareTo(this.date) < 0 || LocalDate.now().compareTo(this.date.plusDays(3)) > 0){
            this.date = LocalDate.now();
        }
        else{
            throw new IllegalArgumentException("Date cannot be within 3 days of current date.");
        }
    }
}
