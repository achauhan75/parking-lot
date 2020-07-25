package com.parking;

import com.parking.enums.Size;
import com.parking.enums.TicketStatus;
import com.parking.exceptions.DuplicateParkingSpotException;
import com.parking.exceptions.ParkingException;
import com.parking.exceptions.ParkingSpotNotFoundException;
import com.parking.interfaces.IParkingLot;
import com.parking.interfaces.IParkingSpot;
import com.parking.interfaces.IVehicle;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;


public class ParkingLot implements IParkingLot {

	private final Collection<IParkingSpot> parkingSpotsVacant;
	private final Collection<IParkingSpot> parkingSpotsOccupied;
	private static final int INIT_CAPACITY = 500;

	ParkingLot() {
		parkingSpotsVacant = new HashSet<>(INIT_CAPACITY);
		parkingSpotsOccupied = new HashSet<>(INIT_CAPACITY);
	}

	public Collection<IParkingSpot> getParkingSpotsVacant(){
		return parkingSpotsVacant;
	}

	public Collection<IParkingSpot> getParkingSpotsOccupied() {
		return parkingSpotsOccupied;
	}


	@Override
	public void addParkingSpot(IParkingSpot parkingSpot) throws DuplicateParkingSpotException {
		if(parkingSpotsVacant.contains(parkingSpot) || parkingSpotsOccupied.contains(parkingSpot))
			throw new DuplicateParkingSpotException(DuplicateParkingSpotException.DEFAULT_MESSAGE);
		parkingSpotsVacant.add(parkingSpot);
	}

	@Override
	public void removeParkingSpot(int id) throws ParkingSpotNotFoundException {
		IParkingSpot parkingSpot = findParkingSpot(id);
		parkingSpotsOccupied.remove(parkingSpot);
		parkingSpotsVacant.remove(parkingSpot);
	}

	public void displayAllParkingSlots() {
		if(!parkingSpotsOccupied.isEmpty()) {
			System.out.println("Occupied Spots");
			System.out.println("ID::::::::Name");
			for(IParkingSpot spot : parkingSpotsOccupied) {
				System.out.println(spot.getId() + " ---------- " + spot.getSize());
			}
		}
		else
			System.out.println("All spots are vacant");


		if(!parkingSpotsVacant.isEmpty()) {
			System.out.println("Vacant Spots");
			System.out.println("ID:::::::::::Name");
			for(IParkingSpot spot : parkingSpotsVacant) {
				System.out.println(spot.getId() + " ---------- " + spot.getSize());
			}
		}
		else
			System.out.println("All spots are occupied");

	}

	@Override
	public IParkingSpot parkVehicle(IVehicle vehicle) throws ParkingSpotNotFoundException, ParkingException {

		IParkingSpot parkingSpot = vehicle.pickBestParking(parkingSpotsVacant);
		Ticket ticket = new Ticket(LocalDateTime.now(),  null);
		ticket.setStatus(TicketStatus.ACTIVE);
		vehicle.assignTicket(ticket);
		parkingSpot.parkVehicle(vehicle);
		parkingSpotsVacant.remove(parkingSpot);
		parkingSpotsOccupied.add(parkingSpot);
		return parkingSpot;
	}


	@Override
	public void unParkVehicle(IVehicle vehicle) throws ParkingSpotNotFoundException {
		IParkingSpot parkingSpot = findParkingSpot(vehicle);
		parkingSpot.removeCurrentVehicle();
		Ticket ticket = vehicle.showTicket();
		ticket.setStatus(TicketStatus.PAID);
		DayOfWeek day = LocalDateTime.now().getDayOfWeek();
		double rate = ticket.getRate(day, vehicle.getSize());
		System.out.println("Total Aount to be paid for parking is" + rate);
		vehicle.assignTicket(ticket);
		parkingSpotsOccupied.remove(parkingSpot);
		parkingSpotsVacant.add(parkingSpot);
	}

	@Override
	public double getParkingRates(int hours, DayOfWeek day, Size size) {
		Ticket ticket = new Ticket();
		return ticket.getRateByHour(day, hours, size);
	}


	@Override
	public IParkingSpot findParkingSpot(IVehicle vehicle) throws ParkingSpotNotFoundException {
		Predicate<IParkingSpot> predicate = ps-> (ps.getCurrentVehicle() != null)
				&& ps.getCurrentVehicle().equals(vehicle);
		IParkingSpot parkingSpot = findParkingSpot(predicate);

		if(parkingSpot == null)
			throw new ParkingSpotNotFoundException(ParkingSpotNotFoundException.DEFAULT_MESSAGE);
		else return parkingSpot;
	}


	public IParkingSpot findParkingSpot(int id) throws ParkingSpotNotFoundException{
		Predicate<IParkingSpot> predicate = ps -> ps.getId() == id;
		IParkingSpot parkingSpot = findParkingSpot(predicate);

		if(parkingSpot == null)
			throw new ParkingSpotNotFoundException(ParkingSpotNotFoundException.DEFAULT_MESSAGE);
		else return parkingSpot;
	}

	private IParkingSpot findParkingSpot(Predicate<IParkingSpot> predicate) {
		return parkingSpotsOccupied
				.stream()
				.filter(predicate)
				.findFirst()
				.orElseGet(() -> parkingSpotsVacant
						.stream()
						.filter(predicate)
						.findFirst()
						.orElse(null));
	}

}
