package com.woyo.ms_inventory.service;

import com.woyo.ms_inventory.model.entity.Product;
import com.woyo.ms_inventory.payload.request.CreateProductRequest;
import com.woyo.ms_inventory.payload.response.CreateProductResponse;
import com.woyo.ms_inventory.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.woyo.ms_inventory.utils.CommonUtil.convertToBase64;
import static com.woyo.ms_inventory.utils.CommonUtil.generateSerialNumber;

@Log4j2
@Service
@AllArgsConstructor
public class CreateStockProductService {

    private final ProductRepository productRepository;
    private final CommonService commonService;
    public CreateProductResponse createProduct(CreateProductRequest request) {
        log.info("Received request: [{}]", request);
        commonService.validateMimeType(request.getProductImage());
        Product product = saveProduct(request);
        CreateProductResponse response = buildResponse(product);
        log.info("Response: [{}]", response);
        return response;
    }

    private static CreateProductResponse buildResponse(Product product) {
        return CreateProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .serialNumber(product.getSerialNumber())
                .build();
    }

    private Product saveProduct(CreateProductRequest request) {
        String serialNumber = generateSerialNumber(request.getProductName());
        String content = convertToBase64(request.getProductImage());

        Product product = Product.builder()
                .productName(request.getProductName())
                .stock(request.getStock())
                .serialNumber(serialNumber)
                .additionalInfo(request.getAdditionalInfo())
                .productImage(content)
                .build();

        product.setCreatedBy("SYSTEM");
        return productRepository.save(product);
    }
}
