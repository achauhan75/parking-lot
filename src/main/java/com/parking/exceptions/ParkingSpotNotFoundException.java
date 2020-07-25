package com.parking.exceptions;

/**
 * An ParkingSpotNotFoundException is thrown when there is no parking space available for the vehicle to use.
 */
public class ParkingSpotNotFoundException extends Exception {

	public static final String DEFAULT_MESSAGE = "Parking spot could not be found.";

	/**
	 *
	 * @param message
	 */
	public ParkingSpotNotFoundException(String message) {
		super(message);
	}
}
