package com.woyo.ms_inventory.payload.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetailDTO {
    private int productId;
    private String productName;
    private int stock;
    private String serialNumber;
    private AdditionalInfoDTO additionalInfo;
    private String productImage;
}
