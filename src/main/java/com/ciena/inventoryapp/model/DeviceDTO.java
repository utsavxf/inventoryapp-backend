package com.ciena.inventoryapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeviceDTO {
    private Device device;
    private List<ShelfPosition> shelfPositions;
}
