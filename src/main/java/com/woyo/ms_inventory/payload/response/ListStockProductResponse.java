package com.woyo.ms_inventory.payload.response;

import com.woyo.ms_inventory.payload.DTO.ProductDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ListStockProductResponse {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPage;
    private Integer totalData;
    private List<ProductDetailDTO> products;
}
