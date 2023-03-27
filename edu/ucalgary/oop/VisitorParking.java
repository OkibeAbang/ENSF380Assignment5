package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.*;
public class VisitorParking {
    HashMap<String, TreeSet<LocalDate>> reservations = new HashMap<String, TreeSet<LocalDate>>();
    
    public VisitorParking() {
    }
    public VisitorParking(String licence, LocalDate date) throws IllegalArgumentException {
        String validLicence;
        try{
            validLicence = Parking.standardizeAndValidateLicence(licence);
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
            TreeSet<LocalDate> dates = new TreeSet<LocalDate>();
            dates.add(date);
            reservations.put(validLicence, dates);
        }
    }
    public VisitorParking(String license) throws IllegalArgumentException{
        String validLicence;
        try{
            validLicence = Parking.standardizeAndValidateLicence(license);
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid licence plate");
        }
        TreeSet<LocalDate> dates = new TreeSet<LocalDate>();
        dates.add(LocalDate.now());
        reservations.put(validLicence, dates);
    }
    public void addVisitorReservation(String licence, LocalDate date) throws IllegalArgumentException{
        String validLicence = "";
        int numRegistered = 0;
        try{
            validLicence = Parking.standardizeAndValidateLicence(licence);
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
        if(reservations.get(validLicence) != null){
            Iterator<LocalDate> iterate = reservations.get(validLicence).iterator();
            while(iterate.hasNext()){
                LocalDate checkDate = iterate.next();
                if(date.compareTo(checkDate.plusDays(3)) < 0 && date.compareTo(checkDate) >= 0){
                    throw new IllegalArgumentException("Date cannot be within 3 days of current date.");
                }
            }
        }
        // Counts the number of reservations for the same date
        for(TreeSet<LocalDate> i : reservations.values()){ //Loop through HashMap
            Iterator<LocalDate> iterate = i.iterator(); //Loop through TreeSet
            while(iterate.hasNext()){
                LocalDate checkDate = iterate.next();
                if(date.compareTo(checkDate.plusDays(3)) < 0 && date.compareTo(checkDate) >= 0){
                    numRegistered++;
                }
            }
        }
        if(numRegistered < 2){
            TreeSet<LocalDate> dates = new TreeSet<LocalDate>();
            dates.add(date);
            reservations.put(validLicence, dates);
        }
        else{
            throw new IllegalArgumentException("Max reservation limit reached for this date");
        }

        // if(date.compareTo(this.date.plusDays(3)) <= 0){
        //     throw new IllegalArgumentException("Date cannot be within 3 days of current date.");
        //     }
        
    }
    public void addVisitorReservation(String licence) throws IllegalArgumentException{
        String validLicence = "";
        int numRegistered = 0;
        try{
            validLicence = Parking.standardizeAndValidateLicence(licence);
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid licence plate");
        }
        // Checks if current licence is already registered for the same date
        if(reservations.get(validLicence) != null){
            Iterator<LocalDate> iterate = reservations.get(validLicence).iterator();
            while(iterate.hasNext()){
                LocalDate checkDate = iterate.next();
                if(LocalDate.now().compareTo(checkDate.plusDays(3)) < 0 && LocalDate.now().compareTo(checkDate) >= 0){
                    throw new IllegalArgumentException("Date cannot be within 3 days of current date.");
                }
            }
        }
        for(TreeSet<LocalDate> i : reservations.values()){ //Loop through HashMap
            Iterator<LocalDate> iterate = i.iterator(); //Loop through TreeSet
            while(iterate.hasNext()){
                LocalDate checkDate = iterate.next();
                if(LocalDate.now().compareTo(checkDate.plusDays(3)) < 0){
                    if(LocalDate.now().compareTo(checkDate) >= 0){
                        numRegistered++;
                    }
                }
            }
        }
        // if(numRegistered > 0){
        //     for(String i : reservations.keySet()){
        //         if(LocalDate.now().compareTo(reservations.get(i).plusDays(3)) <= 0 && LocalDate.now().compareTo(reservations.get(i)) >= 0){
        //             if(i.compareTo(validLicence) == 0){
        //                 throw new IllegalArgumentException("License has already been registered for this date");
        //             }
        //         }
        //     }
        // }
        if(numRegistered < 2){
            TreeSet<LocalDate> dates = new TreeSet<LocalDate>();
            dates.add(LocalDate.now());
            reservations.put(validLicence, dates);
        }
        else{
            throw new IllegalArgumentException("Max reservation limit reached for this date");
        }
    }
    
    public boolean licenceIsRegisteredForDate(String licence, LocalDate date){
        boolean isRegistered = false;
        
        // System.out.println(reservations.get(licence));
        if(reservations.get(licence) != null){
            Iterator<LocalDate> iterate = reservations.get(licence).iterator();
            while(iterate.hasNext()){
                LocalDate checkDate = iterate.next();
                if(date.compareTo(checkDate.plusDays(3)) < 0 && date.compareTo(checkDate) >= 0){
                    isRegistered = true;
                }
            }    
        }
        
        return isRegistered;
    }
    public boolean licenceIsRegisteredForDate(String licence){
        boolean isRegistered = false;
        
        Iterator<LocalDate> iterate = reservations.get(licence).iterator();
        while(iterate.hasNext()){
            LocalDate checkDate = iterate.next();
            if(LocalDate.now().compareTo(checkDate.plusDays(3)) < 0 && LocalDate.now().compareTo(checkDate) >= 0){
                isRegistered = true;
            }
        }
        
        return isRegistered;
    }
    
    public ArrayList<String> getLicencesRegisteredForDate(){
        ArrayList<String> licences = new ArrayList<String>();
        for(String i : reservations.keySet()){
            if(licenceIsRegisteredForDate(i)){
                licences.add(i);
            }
        }

        return licences;
    }
    public ArrayList<String> getLicencesRegisteredForDate(LocalDate date){
        ArrayList<String> licences = new ArrayList<String>();
        for(String i : reservations.keySet()){
            if(licenceIsRegisteredForDate(i, date)){
                licences.add(i);
            }
        }
        return licences;
    }
}
