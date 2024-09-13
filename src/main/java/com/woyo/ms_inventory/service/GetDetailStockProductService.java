package com.woyo.ms_inventory.service;

import com.woyo.ms_inventory.exception.CustomException;
import com.woyo.ms_inventory.model.entity.Product;
import com.woyo.ms_inventory.payload.DTO.ProductDetailDTO;
import com.woyo.ms_inventory.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class GetDetailStockProductService {

    private ProductRepository productRepository;
    private CommonService commonService;

    public ProductDetailDTO getStockProductDetail(int productId) {
        log.info("Received request: [{}]", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(
                        "6000",
                        "Data product not found",
                        HttpStatus.CONFLICT
                ));

        ProductDetailDTO response = commonService.convertToDTO(product);
        log.info("Response: [{}]", response);
        return response;
    }
}
