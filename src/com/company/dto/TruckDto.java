package com.company.dto;

import com.company.enums.State;
import com.google.gson.annotations.SerializedName;

public record TruckDto(int id,
                       String name,
                       @SerializedName(value = "drive")DriverWithoutTruck driverwithoutTruck,
                       State state) {
}
