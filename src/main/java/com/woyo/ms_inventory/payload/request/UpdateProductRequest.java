package com.woyo.ms_inventory.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
public class UpdateProductRequest {
    private Integer productId;
    private String productName;
    private Integer stock;
    private String additionalInfo;
    private MultipartFile productImage;
}
