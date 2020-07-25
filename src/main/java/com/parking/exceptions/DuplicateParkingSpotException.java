package com.parking.exceptions;

/**
 * A DuplicateParkingSpotException is thrown when
 * a parking spot with the same id already exists in the parking lot.
 */
public class DuplicateParkingSpotException extends Exception {

	public static final String DEFAULT_MESSAGE = "A parking spot with the same id already exists.";

	/**
	 * 
	 * @param message
	 */
	public DuplicateParkingSpotException(String message) {
		super(message);
	}
}
