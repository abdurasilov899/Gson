package com.company.service;

import com.company.enums.State;
import com.company.daoModel.DaoDriver;
import com.company.daoModel.DaoTruck;
import com.company.dto.DriverDto;
import com.company.dto.DriverWithoutTruck;
import com.company.dto.TruckDto;
import com.company.dto.TruckWithoutDriver;
import com.company.model.Driver;
import com.company.model.Truck;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Service {
    public static final GsonBuilder builder = new GsonBuilder();
    public static final Gson GSON = builder.setPrettyPrinting().create();
    public static final Path PATH = Paths.get("./truck.json");
    public static final Path PATH1 = Paths.get("./driver.json");
    Scanner scanner = new Scanner(System.in);
    private DaoDriver daoDriver = new DaoDriver();
    private DaoTruck daoTruck = new DaoTruck();

    public void addDriver(Driver driver) {
        daoDriver.addDriver(driver);
    }

    public void addTruck(Truck truck) {
        daoTruck.addTruck(truck);
    }

    public void showIdTruck(int id) {
        daoTruck.showIdTruck(id);
    }

    public void showTrucks() {
        daoTruck.show();
    }

    public void changeDriver(int truck) {
        for (Map.Entry<Integer, Truck> e : daoTruck.getMapTruck().entrySet()) {
            if (e.getValue().getState().equals(State.BASE)) {
                daoDriver.show();
                System.out.println();
                System.out.print("TO CHANGE DRIVER SELECT DRIVER: ");
                int driver = scanner.nextInt();
                changeDriver1(truck, driver);
                break;
            } else if (e.getValue().getState().equals(State.ROUTE)) {
                System.err.printf("YOU CAN'T CHANGE DRIVER, BECAUSE THE TRUCK[%S] ON THE ROUTE\n",
                        e.getValue().getName());
                break;
            } else {
                System.err.printf("YOU CAN'T CHANGE DRIVER, BECAUSE THE TRUCK[%S] ON THE REPAIR\n",
                        e.getValue().getName());
                break;
            }
        }
    }

    private void changeDriver1(int truck, int driver) {
        Truck truck1 = daoTruck.showIdTruck(truck);
        Driver driver1 = daoDriver.showIdDriver(driver);
        truck1.setDriver(driver1);
        driver1.setTruck(truck1);
    }

    public void route(int truck) {
        daoTruck.route(truck);
    }

    public void repairing(int truck) {
        daoTruck.repairing(truck);
    }

    public void base(int truck) {
        daoTruck.base(truck);
    }

    public void showDriver() {
        daoDriver.show();
    }

    private TruckWithoutDriver convert(Truck truck) {
        return new TruckWithoutDriver(truck.getId(), truck.getName(), truck.getState());
    }

    private DriverWithoutTruck convert(Driver driver) {
        return new DriverWithoutTruck(driver.getId(), driver.getName());
    }

    private Map<Integer, TruckDto> truckDtoMap() {
        Map<Integer, TruckDto> integerTruckDtoMap = new HashMap<>();
        daoTruck.getMapTruck().forEach((key, value) -> {
            integerTruckDtoMap.put(key, new TruckDto(value.getId(),
                    value.getName(),
                    value.hasDriver() ? convert(value.getDriver()) : null,
                    value.getState()));
        });
        return integerTruckDtoMap;
    }

    private Map<Integer, DriverDto> driverDtoMap() {
        Map<Integer, DriverDto> integerDriverDtoMap = new HashMap<>();
        daoDriver.getMapDriver().forEach((key, value) ->
                integerDriverDtoMap.put(key, new DriverDto(value.getId(), value.getName(),
                        value.hasTruck() ? convert(value.getTruck()) : null)));
        return integerDriverDtoMap;
    }

    public void writeGson() {
        String json = GSON.toJson(truckDtoMap());
        writeTruck(json);
        String json1 = GSON.toJson(driverDtoMap());
        writeDrivers(json1);
    }

    public static void writeTruck(String o) {
        try {
            Path path = Paths.get(String.valueOf(PATH));
            Files.writeString(path, o, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDrivers(String o) {
        try {
            Path path = Paths.get(String.valueOf(PATH1));
            Files.writeString(path, o, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
