package com.woyo.ms_inventory.service;


import com.woyo.ms_inventory.exception.CustomException;
import com.woyo.ms_inventory.model.entity.Product;
import com.woyo.ms_inventory.payload.DTO.ProductDetailDTO;
import com.woyo.ms_inventory.payload.request.GetListStockProductRequest;
import com.woyo.ms_inventory.payload.response.ListStockProductResponse;
import com.woyo.ms_inventory.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class GetListStockProductService {

    private final ProductRepository productRepository;
    private final CommonService commonService;

    public ListStockProductResponse getListStockProduct(GetListStockProductRequest request) {
        Page<Product> products = getProducts(request);

        List<ProductDetailDTO> productList = products.stream()
                .map(commonService::convertToDTO)
                .toList();

        ListStockProductResponse response = ListStockProductResponse.builder()
                .pageNumber(request.getPageNumber())
                .pageSize(request.getPageSize())
                .totalPage(products.getTotalPages())
                .totalData(products.getNumberOfElements())
                .products(productList)
                .build();

        log.info("Response: [{}}", response);
        return response;
    }

    private Page<Product> getProducts(GetListStockProductRequest request) {
        int pageNumber = request.getPageNumber() - 1; // Adjust to zero-based index
        Pageable pageable = PageRequest.of(pageNumber, request.getPageSize());
        Page<Product> products = productRepository.findAllProduct(
                request.getStock(),
                request.getCategory(),
                pageable
        );

        if (products.isEmpty()) {
            throw new CustomException(
                    "5000",
                    "Data product is empty",
                    HttpStatus.CONFLICT
            );
        }
        return products;
    }
}
