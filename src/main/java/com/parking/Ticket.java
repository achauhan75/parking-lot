package com.parking;

import com.parking.enums.Size;
import com.parking.enums.TicketStatus;
import com.parking.enums.WeekDayPrice;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Ticket {

    private final LocalDateTime timeIssued;
    private LocalDateTime exitTime;
    private TicketStatus status;

    Ticket(LocalDateTime timeIssued, LocalDateTime exitTime) {
        this.timeIssued = timeIssued;
        this.exitTime = null;
        this.status = TicketStatus.CANCELLED;
    }

    Ticket() {
        exitTime = null;
        timeIssued = null;
    }


    public void setStatus(TicketStatus statusFinal) {
        status = statusFinal;
    }

    public TicketStatus getTicketStatus(){
        return status;
    }

    public LocalDateTime getTimeIssued(){
        return timeIssued;
    }

    public LocalDateTime getExitTime(){
        return exitTime;
    }

    public double getRate(DayOfWeek day, Size vehicleSize) {
        exitTime = LocalDateTime.now();
        assert timeIssued != null;
        int duration = exitTime.minusHours(timeIssued.getHour()).getHour();
        if (WeekDayPrice.WEEK_DAY.getDays().contains(day)) {
            return 2 * duration * getPricePerHourByVehicleSize(vehicleSize);
        }
        else {
            return duration * getPricePerHourByVehicleSize(vehicleSize);
        }
    }

    public double getRateByHour(DayOfWeek day, int hours, Size vehicleSize){
        if(WeekDayPrice.WEEK_DAY.getDays().contains(day)) {
            return 2 * hours * getPricePerHourByVehicleSize(vehicleSize);
        }
        else {
            return hours * getPricePerHourByVehicleSize(vehicleSize);
        }

    }

    private double getPricePerHourByVehicleSize(Size vehicleSize) {
        if (vehicleSize.equals(Size.LARGE))
            return 100;
        if (vehicleSize.equals(Size.MEDIUM))
            return 50;
        if (vehicleSize.equals(Size.SMALL))
            return 20;
        return 60;
    }


}
