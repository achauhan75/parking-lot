package com.parking;

import com.parking.enums.Size;
import com.parking.enums.VehicleType;
import com.parking.exceptions.ParkingSpotNotFoundException;
import com.parking.interfaces.IParkingSpot;
import com.parking.interfaces.IVehicle;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A Vehicle represents a vehicle that can be parked in a parking spot.
 */
public class Vehicle implements IVehicle {
	private final String vehicleNumber;
	private final VehicleType type;
	private Ticket ticket;
	Vehicle(String vehicleNumber, VehicleType type) {
		this.vehicleNumber = vehicleNumber;
		this.type = type;
	}

	@Override
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	@Override
	public Size getSize() {
		return type.getSize();
	}


	public Ticket showTicket() {
		System.out.println("Entry Time: " + ticket.getTimeIssued() + "Exit time" + ticket.getExitTime() + "Ticket Status" + ticket.getTicketStatus());
		return ticket;
	}

	@Override
	public void assignTicket(Ticket ticketAssigned) {
		ticket = ticketAssigned;
	}
	/**
	 * Pick the best parking spot according to the size of vehicle and spot from all the parking spots in the parking lot.
	 *
	 * @param parkingSpots a set of parking spots.
	 * @return a parking spot from the given parking spots that can be used by the vehicle to park it in.
	 * @throws ParkingSpotNotFoundException when a parking spot is not found.
	 */
	@Override
	public IParkingSpot pickBestParking(Collection<IParkingSpot> parkingSpots) throws ParkingSpotNotFoundException {
		List<IParkingSpot> vacantParkingSpots
				= getVacantParkingSpots(parkingSpots).stream().collect(Collectors.toList());
		vacantParkingSpots.sort(Comparator.comparing(IParkingSpot::getSize));
		return vacantParkingSpots.get(0);
	}

	/**
	 * Get a list of parking spots that are vacant and that can fit this vehicle.
	 *
	 * @param parkingSpots a set of parking spots.
	 * @return a list of parking spots that are vacant and able to fit this vehicle.
	 * @throws ParkingSpotNotFoundException if no such parking spot is available at the moment.
	 */
	private Collection<IParkingSpot> getVacantParkingSpots(Collection<IParkingSpot> parkingSpots)
			throws ParkingSpotNotFoundException {

		Predicate<IParkingSpot> filterPredicateVacant = ps -> ps.isVacant()
				&& (ps.getSize().ordinal() >= type.getSize().ordinal());

		List<IParkingSpot> vacantParkingSpots = parkingSpots.stream()
				.filter(filterPredicateVacant)
				.collect(Collectors.toList());

		if(vacantParkingSpots.isEmpty())
			throw new ParkingSpotNotFoundException(ParkingSpotNotFoundException.DEFAULT_MESSAGE);
		else return vacantParkingSpots;
	}
}
