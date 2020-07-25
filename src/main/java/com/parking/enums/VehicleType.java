package com.parking.enums;

import com.parking.interfaces.IVehicleType;

/**
 * A VehicleType enum value represents the type of vehicle.
 *
 */
public enum VehicleType implements IVehicleType{
	BIKE(Size.SMALL),
	CAR(Size.MEDIUM),
	BUS(Size.LARGE);

	private final Size size;

	/**
	 * @param size the size of the vehicle.
	 */
	VehicleType(Size size) {
		this.size = size;
	}

	/**
	 * @return the size of the vehicle.
	 */
	public Size getSize(){
		return size;
	}
}
