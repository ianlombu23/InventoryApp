package com.woyo.ms_inventory.service;

import com.alibaba.fastjson2.JSON;
import com.woyo.ms_inventory.exception.CustomException;
import com.woyo.ms_inventory.model.entity.Product;
import com.woyo.ms_inventory.payload.DTO.AdditionalInfoDTO;
import com.woyo.ms_inventory.payload.DTO.ProductDetailDTO;
import com.woyo.ms_inventory.utils.CommonUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CommonService {

    public CommonService() {
    }

    public ProductDetailDTO convertToDTO(Product product) {
        AdditionalInfoDTO additionalInfoDTO = JSON.parseObject(product.getAdditionalInfo(), AdditionalInfoDTO.class);
        return ProductDetailDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productImage(product.getProductName())
                .stock(product.getStock())
                .serialNumber(product.getSerialNumber())
                .additionalInfo(additionalInfoDTO)
                .productImage(product.getProductImage())
                .build();
    }

    public void validateMimeType(MultipartFile file) {
        if (ObjectUtils.isNotEmpty(file)) {
            boolean isValidMimeType = CommonUtil.isValidMimeType(file);

            if (!isValidMimeType) {
                throw new CustomException(
                        "4500",
                        "Invalid Mime Type, accepted formats: jpg, png",
                        HttpStatus.CONFLICT
                );
            }
        }

    }
}
