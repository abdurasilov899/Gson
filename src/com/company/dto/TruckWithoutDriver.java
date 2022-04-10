package com.company.dto;

import com.company.enums.State;

public record TruckWithoutDriver(int id, String name, State state ) {
}
