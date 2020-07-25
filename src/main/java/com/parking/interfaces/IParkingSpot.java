package com.parking.interfaces;

import com.parking.enums.Size;
import com.parking.exceptions.ParkingException;

/**
 * An interface for a parking spot that provides space for vehicles to be parked.
 */
public interface IParkingSpot {

	int getId();

	Size getSize();

	IVehicle getCurrentVehicle();

	boolean isVacant();

	void parkVehicle(IVehicle vehicle) throws ParkingException;

	void removeCurrentVehicle();
}
