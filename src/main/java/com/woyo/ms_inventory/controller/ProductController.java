package com.woyo.ms_inventory.controller;

import com.woyo.ms_inventory.payload.DTO.ProductDetailDTO;
import com.woyo.ms_inventory.payload.request.CreateProductRequest;
import com.woyo.ms_inventory.payload.request.GetListStockProductRequest;
import com.woyo.ms_inventory.payload.request.UpdateProductRequest;
import com.woyo.ms_inventory.payload.response.CreateProductResponse;
import com.woyo.ms_inventory.payload.response.EmptyJsonResponse;
import com.woyo.ms_inventory.payload.response.ListStockProductResponse;
import com.woyo.ms_inventory.service.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/inventory/v1/product")
@AllArgsConstructor
public class ProductController {

    private CreateStockProductService createStockProductService;
    private GetListStockProductService getListStockProductService;
    private GetDetailStockProductService getDetailStockProductService;
    private UpdateStockProductService updateStockProductService;
    private DeleteStockProductService deleteStockProductService;

    @PostMapping(value = "/create")
    public CreateProductResponse createStockProduct(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productName") String productName,
            @RequestParam("stock") Integer stock,
            @RequestParam("additionalInfo") String additionalInfo
    ) {
        return createStockProductService.createProduct(CreateProductRequest.builder()
                .productName(productName)
                .stock(stock)
                .additionalInfo(additionalInfo)
                .productImage(file)
                .build()
        );
    }

    @GetMapping("/list")
    public ListStockProductResponse getListStockProduct(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) String category
    ) {
        return getListStockProductService.getListStockProduct(GetListStockProductRequest.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .stock(stock)
                .category(category)
                .build()
        );
    }

    @GetMapping("/{productId}")
    public ProductDetailDTO getListStockProduct(@PathVariable int productId) {
        return getDetailStockProductService.getStockProductDetail(productId);
    }

    @PostMapping(value = "/update")
    public ProductDetailDTO updateProduct(
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) String additionalInfo
    ) {
        return updateStockProductService.updateStockProduct(UpdateProductRequest.builder()
                .productId(productId)
                .productName(productName)
                .stock(stock)
                .additionalInfo(additionalInfo)
                .productImage(file)
                .build()
        );
    }

    @DeleteMapping("/delete/{productId}")
    public EmptyJsonResponse deleteStockProduct(@PathVariable int productId) {
        return deleteStockProductService.deleteStockProduct(productId);
    }
}
