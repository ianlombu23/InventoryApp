package com.woyo.ms_inventory.payload.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdditionalInfoDTO {
    private String price;
    private String category;
    private String description;
    private int totalSold;
    private Boolean isOfficial;
    private String location;
    private ProductReviewDTO review;

}
