package com.woyo.ms_inventory.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetListStockProductRequest {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer stock;
    private String category;
}
