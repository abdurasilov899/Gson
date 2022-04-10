package com.company.daoModel;

import com.company.enums.State;
import com.company.model.Truck;

import java.util.HashMap;
import java.util.Map;

public class DaoTruck {
    private Map<Integer, Truck> mapTruck = new HashMap<>();

    public Map<Integer, Truck> getMapTruck() {
        return mapTruck;
    }

    public void addTruck(Truck truck) {
        mapTruck.put(truck.getId(), truck);
    }

    public void route(int id) {
        for (Map.Entry<Integer, Truck> t : mapTruck.entrySet()) {
            if (t.getKey() == id) {
                if (t.getValue().getState().equals(State.ROUTE)) {
                    System.err.println("The truck is on its way");
                } else {
                    if (t.getValue().getDriver() == null) {
                        System.err.println("NO DRIVER");
                    } else {
                        t.getValue().setState(State.ROUTE);
                        System.out.println("=================================================");
                        System.out.println("You have successfully entered the route");
                    }
                }
            }
        }
    }

    public void repairing(int id) {
        mapTruck.entrySet().stream().filter(x -> x.getKey() == id).forEach(x -> x.getValue().setState(State.REPAIR));
        System.out.println("=================================================");
        System.out.println("you have successfully sent for repair");
        System.out.println();
    }

    public void base(int id) {
        mapTruck.entrySet().stream().filter(x -> x.getKey() == id).forEach(x -> x.getValue().setState(State.BASE));
        System.out.println("=================================================");
        System.out.println("you have successfully sent for base");
        System.out.println();
    }

    public Truck showIdTruck(int id) {
        return mapTruck.get(id);
    }

    public void show() {
        System.out.println("""
                 #     | Bus            | Driver   | State
                -------+----------------+----------+--------------""");
        for (Map.Entry<Integer, Truck> entry : mapTruck.entrySet()) {
            Integer key = entry.getKey();
            Truck value = entry.getValue();
            try {
                System.out.printf("""
                                 -%d-    | %s | %s| %s            """, key, value.getName(),
                        value.getDriver().getName(), value.getState() + "\n");
            } catch (NullPointerException e) {
                System.out.printf("""
                                -%d-    | %s | %s        | %s            """, key, value.getName(),
                        " ", value.getState() + "\n");
            }
        }
    }
}
