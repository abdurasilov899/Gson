package com.company.dto;

import com.google.gson.annotations.SerializedName;

public record DriverDto(int id,
                        String name, @SerializedName(value = "Truck")TruckWithoutDriver truckWithoutDriver) {
}
