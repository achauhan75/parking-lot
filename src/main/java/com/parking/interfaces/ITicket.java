package com.parking.interfaces;

import com.parking.enums.Size;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public interface ITicket {

    LocalDateTime getTimeIssued();

    LocalDateTime getExitTime();

    double getRate(DayOfWeek day, int hours, IVehicleType vehicleType);

    double getRate(DayOfWeek day, Size vehicleSize);

}
