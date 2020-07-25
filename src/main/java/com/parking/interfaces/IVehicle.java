package com.parking.interfaces;

import com.parking.Ticket;
import com.parking.enums.Size;
import com.parking.exceptions.ParkingSpotNotFoundException;

import java.util.Collection;

/**
 * A class implementing IVehicle represents a vehicle.
 */
public interface IVehicle {

	String getVehicleNumber();

	Size getSize();

	Ticket showTicket();

	void assignTicket(Ticket ticket);

	/**
	 * Pick the best parking spot from all the parking spots in the parking lot according to the size.
	 *
	 * @param parkingSpots a collection of parking spots.
	 * @return a parking spot from the given parking spots that can be used by the vehicle to park it in.
	 * @throws ParkingSpotNotFoundException when a parking spot is not found.
	 */
	IParkingSpot pickBestParking(Collection<IParkingSpot> parkingSpots) throws ParkingSpotNotFoundException;
}
