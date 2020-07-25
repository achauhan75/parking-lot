package com.parking.interfaces;

import com.parking.exceptions.DuplicateParkingSpotException;
import com.parking.exceptions.ParkingException;
import com.parking.exceptions.ParkingSpotNotFoundException;

import java.util.Collection;

/**
 * An interface for a parking lot that has parking spaces for vehicles.
 */
public interface IParkingLot {

	void addParkingSpot(IParkingSpot parkingSpot) throws DuplicateParkingSpotException;

	void removeParkingSpot(int id) throws ParkingSpotNotFoundException;

	Collection<IParkingSpot> getParkingSpotsVacant();

	Collection<IParkingSpot> getParkingSpotsOccupied();

	IParkingSpot parkVehicle(IVehicle vehicle) throws ParkingSpotNotFoundException, ParkingException;

	void unParkVehicle(IVehicle vehicle) throws ParkingSpotNotFoundException;

	IParkingSpot findParkingSpot(IVehicle vehicle) throws ParkingSpotNotFoundException;

	IParkingSpot findParkingSpot(int id) throws ParkingSpotNotFoundException;
}