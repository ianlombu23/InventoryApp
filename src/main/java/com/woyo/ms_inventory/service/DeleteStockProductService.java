package com.woyo.ms_inventory.service;

import com.woyo.ms_inventory.exception.CustomException;
import com.woyo.ms_inventory.model.entity.Product;
import com.woyo.ms_inventory.payload.response.EmptyJsonResponse;
import com.woyo.ms_inventory.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class DeleteStockProductService {

    private final ProductRepository productRepository;

    public EmptyJsonResponse deleteStockProduct(int productId) {
        log.info("Received request: [{}]", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(
                        "6000",
                        "Data product not found",
                        HttpStatus.CONFLICT
                ));

        productRepository.delete(product);
        log.info("Success delete product: ", productId);
        return new EmptyJsonResponse();
    }
}
