package com.parking;

import com.parking.enums.Size;
import com.parking.exceptions.ParkingException;
import com.parking.interfaces.IParkingSpot;
import com.parking.interfaces.IVehicle;

/**
 * A ParkingSpot represents a parking spot in the parking lot.
 *
 */
public class ParkingSpot implements IParkingSpot {

	private final int id;
	private final Size size;
	private IVehicle currentVehicle;

	/**
	 * 
	 * @param id
	 * @param size
	 */
	ParkingSpot(int id, Size size) {
		this.id = id;
		this.size = size;
		this.currentVehicle = null;
	}

	public int getId() {
		return id;
	}


	public Size getSize() {
		return size;
	}


	public IVehicle getCurrentVehicle() {
		return currentVehicle;
	}


	public boolean isVacant() {
		return currentVehicle == null;
	}


	public void parkVehicle(IVehicle vehicle) throws ParkingException {
		if(!canPark(vehicle))
			throw new ParkingException(ParkingException.DEFAULT_MESSAGE);
		currentVehicle = vehicle;
	}


	public void removeCurrentVehicle()  {
		currentVehicle = null;
	}


	private boolean canPark(IVehicle vehicle) {
		return (vehicle.getSize().ordinal() <= size.ordinal());
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof ParkingSpot)
				&& (((ParkingSpot) obj).id == this.id);
	}

	@Override
	public int hashCode() {
		return id;
	}
}
