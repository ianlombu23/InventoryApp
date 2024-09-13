package com.woyo.ms_inventory.service;

import com.alibaba.fastjson2.JSON;
import com.woyo.ms_inventory.exception.CustomException;
import com.woyo.ms_inventory.model.entity.Product;
import com.woyo.ms_inventory.payload.DTO.ProductDetailDTO;
import com.woyo.ms_inventory.payload.request.UpdateProductRequest;
import com.woyo.ms_inventory.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.woyo.ms_inventory.utils.CommonUtil;

import java.util.Map;

import static com.woyo.ms_inventory.utils.CommonUtil.convertToBase64;

@Service
@AllArgsConstructor
@Log4j2
public class UpdateStockProductService {

    private final ProductRepository productRepository;
    private final CommonService commonService;

    public ProductDetailDTO updateStockProduct(UpdateProductRequest request) {
        log.info("Received request: [{}]", request);
        Product product = findProductById(request);
        updateProductDetails(request, product);
        productRepository.save(product);

        ProductDetailDTO response = commonService.convertToDTO(product);
        log.info("Response: [{}]", response);
        return response;
    }

    private void updateProductDetails(UpdateProductRequest request, Product product) {
        product.setProductName(StringUtils.defaultString(request.getProductName(), product.getProductName()));
        product.setStock(ObjectUtils.defaultIfNull(request.getStock(), product.getStock()));
        product.setSerialNumber(StringUtils.defaultString(CommonUtil.generateSerialNumber(request.getProductName()), product.getSerialNumber()));

        if (ObjectUtils.isNotEmpty(request.getProductImage())) {
            commonService.validateMimeType(request.getProductImage());
            product.setProductImage(convertToBase64(request.getProductImage()));
        }

        updateAdditionalInfo(request, product);
    }

    private void updateAdditionalInfo(UpdateProductRequest request, Product product) {
        if (ObjectUtils.isNotEmpty(request.getAdditionalInfo())) {
            Map<Object, Object> additionalInfoRequest= JSON.parseObject(request.getAdditionalInfo(), Map.class);
            Map<Object, Object> additionalInfoFromDb = JSON.parseObject(product.getAdditionalInfo(), Map.class);

            additionalInfoFromDb.putAll(additionalInfoRequest);
            product.setAdditionalInfo(JSON.toJSONString(additionalInfoFromDb));
        }
    }

    private Product findProductById(UpdateProductRequest request) {
        return productRepository.findById(request.getProductId())
                .orElseThrow(() -> new CustomException(
                        "6500",
                        "Data product not found",
                        HttpStatus.CONFLICT
                ));
    }
}
