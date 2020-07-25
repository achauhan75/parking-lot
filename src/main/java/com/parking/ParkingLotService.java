package com.parking;

import com.parking.enums.Size;
import com.parking.enums.VehicleType;
import com.parking.exceptions.DuplicateParkingSpotException;
import com.parking.exceptions.ParkingException;
import com.parking.exceptions.ParkingSpotNotFoundException;
import com.parking.interfaces.IParkingSpot;
import com.parking.interfaces.IVehicle;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ParkingLotService {

    public static void main(String[] args) throws DuplicateParkingSpotException, ParkingSpotNotFoundException, ParkingException {

        ParkingLot lot = new ParkingLot();
        Scanner scanner = new Scanner(System.in);
        while(true) {
        System.out.println("\n***********\nSelect Option\n 1. Add parking Spot\n 2. Remove Parking Spot\n 3. Display Parking Spots\n 4." +
                " Park Vehicle\n 5. Unpark a Vehicle\n 6. Find Parking Spot\n 7. Display Parking Rates\n 8.exit\n***********");

        int input = scanner.nextInt();
        if(input == 8) break;
        String sizeInput;
        String vehicleInput;
        Size size = null;
        VehicleType vehicleType = null;
        String vehicleNumber;
        switch (input) {
            case 1: //add parking slot
                System.out.println("Add Id of Parking Lot");
                int idInput = scanner.nextInt();
                System.out.println("Add Size of Parking Lot (SMALL, MEDIUM, LARGE)");
                sizeInput = scanner.next();
                if(sizeInput.equalsIgnoreCase("SMALL")) {
                    size = Size.SMALL;
                }
                else if(sizeInput.equalsIgnoreCase("MEDIUM")) {
                    size = Size.MEDIUM;
                }
                else if(sizeInput.equalsIgnoreCase("large")) {
                    size = Size.LARGE;
                }
                else {
                    System.out.println("Select size of parking lot from SMALL, MEDIUM, LARGE only.");
                    break;
                }
                IParkingSpot spot = new ParkingSpot(idInput, size);
                lot.addParkingSpot(spot);
                break;
            case 2: //remove parking slot
                System.out.println("Add id of parking lot to delete");
                int id = scanner.nextInt();
                lot.removeParkingSpot(id);
            case 3: //display parking lot
                lot.displayAllParkingSlots();
                break;
            case 4: //park vehicle
                System.out.println("Add Vehicle CAR, BUS, BIKE");
                vehicleInput =scanner.next();
                if(vehicleInput.equalsIgnoreCase("CAR")) {
                    vehicleType = VehicleType.CAR;
                }
                else if(vehicleInput.equalsIgnoreCase("BUS")) {
                    vehicleType = VehicleType.BUS;
                }
                else if(vehicleInput.equalsIgnoreCase("BIKE")) {
                    vehicleType = VehicleType.BIKE;
                }
                else {
                    System.out.println("Select size of Vehicle from SMALL, MEDIUM, LARGE only.");
                    break;
                }
                System.out.println("Add Vehicle Plate Number");
                vehicleNumber = scanner.next();
                IVehicle vehicle = new Vehicle(vehicleNumber, vehicleType);
                lot.parkVehicle(vehicle);
                break;
            case 5: //unPark vehicle
                System.out.println("Add Details of vehicle to remove");
                vehicleInput =scanner.next();
                if(vehicleInput.equalsIgnoreCase("CAR")) {
                    vehicleType = VehicleType.CAR;
                }
                else if(vehicleInput.equalsIgnoreCase("BUS")) {
                    vehicleType = VehicleType.BUS;
                }
                else if(vehicleInput.equalsIgnoreCase("BIKE")) {
                    vehicleType = VehicleType.BIKE;
                }
                else {
                    System.out.println("Select Vehicle type from CAR, BUS, BIKE only.");
                    break;
                }
                System.out.println("Add Vehicle Number");
                vehicleNumber = scanner.next();
                IVehicle vehicleToUnpark = new Vehicle(vehicleNumber, vehicleType);
                lot.unParkVehicle(vehicleToUnpark);
                break;
            case 6: // Find Parking Spot
                System.out.println("Select Option \n1. Find parking spot by Spot Id\n  2. Find Parking spot by Vehicle details");
                int option = scanner.nextInt();
                switch(option) {
                    case 1: System.out.println("Add Id of Parking Lot");
                        int idOfSpot = scanner.nextInt();
                        IParkingSpot spotFetched = lot.findParkingSpot(idOfSpot);
                        System.out.println("Spot size: " + spotFetched.getSize() + " \nCurrent Vehicle in this Spot: " + spotFetched.getCurrentVehicle().getVehicleNumber());
                        break;
                    case 2:
                        System.out.println("Add Type of vehicle to remove");
                        vehicleInput =scanner.next();
                        if(vehicleInput.equalsIgnoreCase("CAR")) {
                            vehicleType = VehicleType.CAR;
                        }
                        else if(vehicleInput.equalsIgnoreCase("BUS")) {
                            vehicleType = VehicleType.BUS;
                        }
                        else if(vehicleInput.equalsIgnoreCase("BIKE")) {
                            vehicleType = VehicleType.BIKE;
                        }
                        else {
                            System.out.println("Select Vehicle type from CAR, BUS, BIKE only.");
                            break;
                        }
                        System.out.println("Add Vehicle Number");
                        vehicleNumber = scanner.next();
                        IVehicle vehicle1 = new Vehicle(vehicleNumber, vehicleType);
                        lot.findParkingSpot(vehicle1);
                        break;
                }
                break;
            case 7:
                System.out.println("Add Type of vehicle (CAR, BUS, BIKE)");
                vehicleInput =scanner.next();
                if(vehicleInput.equalsIgnoreCase("CAR")) {
                    size = Size.MEDIUM;
                }
                else if(vehicleInput.equalsIgnoreCase("BUS")) {
                    size = Size.LARGE;
                }
                else if(vehicleInput.equalsIgnoreCase("BIKE")) {
                    size = Size.SMALL;
                }
                else {
                    System.out.println("Select Vehicle type from CAR, BUS, BIKE only.");
                    break;
                }
                System.out.println("Enter Number of hours to park vehicle");
                int hours = scanner.nextInt();
                System.out.println("Price is: " + lot.getParkingRates(hours, LocalDateTime.now().getDayOfWeek(), size));
                break;
            default:
                lot.displayAllParkingSlots();
                break;

        }
        }
    }
}
