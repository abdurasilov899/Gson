package com.company;

import com.company.enums.State;
import com.company.exceptions.InvalidChangeAttemptException;
import com.company.model.Driver;
import com.company.model.Truck;
import com.company.service.Service;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Truck truck1 = new Truck(1, "BMW           ", State.BASE);
        Truck truck2 = new Truck(2, "CAMRY         ", State.BASE);
        Truck truck3 = new Truck(3, "MERCEDES-BENZ ", State.BASE);

        Driver driver1 = new Driver(1, "Ulanskie ");
        Driver driver2 = new Driver(2, "Altynskie");
        Driver driver3 = new Driver(3, "Kanatskie");

        Service service = new Service();
        service.addDriver(driver1);
        service.addDriver(driver2);
        service.addDriver(driver3);
        service.addTruck(truck1);
        service.addTruck(truck2);
        service.addTruck(truck3);

        service.showTrucks();

        while (true) {
            try {
                System.out.println();
                System.out.print("ENTER ID OF TRUCK TO SEE ALL INFORMATION ABOUT IT: ");
                int truck = scanner.nextInt();
                System.out.println();
                service.showIdTruck(truck);
                System.out.println("Press 1 to change Driver");
                System.out.println("Press 2 to send to the Route");
                System.out.println("Press 3 ot send to the Repairing");
                System.out.println("Press 4 ot send to the Base");
                int state = scanner.nextInt();
                switch (state) {
                    case 1 -> service.changeDriver(truck);
                    case 2 -> service.route(truck);
                    case 3 -> service.repairing(truck);
                    case 4 -> service.base(truck);
                }
            } catch (InvalidChangeAttemptException e) {
                System.err.println("WE COULD NOT FIND THIS NUMBER");
            }
            System.out.println();
            service.showTrucks();
            System.out.println();
            service.showDriver();
            service.writeGson();
        }
    }
}
