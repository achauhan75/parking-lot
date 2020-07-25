package com.parking.exceptions;

/**
 * A ParkingException is thrown when a vehicle tries to park in a spot which is too small.
 *
 */
public class ParkingException extends Exception {

	public static final String DEFAULT_MESSAGE = "Can not park the car in the parking spot.";

	/**
	 *
	 * @param message
	 */
	public ParkingException(String message) {
		super(message);
	}
}
