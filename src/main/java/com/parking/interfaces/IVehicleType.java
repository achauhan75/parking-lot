package com.parking.interfaces;

import com.parking.enums.Size;

/**
 * A VehicleType enum value represents the type of vehicle.
 */
public interface IVehicleType {
	/**
	 * Get the size of the vehicle.
	 * @return the size of the vehicle.
	 */
	Size getSize();
}
