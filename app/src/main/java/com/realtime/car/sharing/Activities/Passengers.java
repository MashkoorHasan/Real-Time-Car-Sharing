package com.realtime.car.sharing.Activities;

public class Passengers {
    public String Start_Location, End_Location, Date, Time, Seat_No, Rider_type;
    public Passengers(){

    }

    public Passengers( String Start_Location, String End_Location, String Date, String Time, String Seat_No, String Rider_type) {
        this.Start_Location = Start_Location;
        this.End_Location = End_Location;
        this.Date = Date;
        this.Time = Time;
        this.Seat_No = Seat_No;
        this.Rider_type = Rider_type;
        //this.No_Of_Seats = No_Of_seats;
    }

    }
