package com.woyo.ms_inventory.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateProductResponse {
    private int productId;
    private String productName;
    private String serialNumber;
}
