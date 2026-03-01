package com.example.SharmiSpringBoot.ProductService.service.impl;

import com.example.SharmiSpringBoot.ProductService.dto.ProductDto;
import com.example.SharmiSpringBoot.ProductService.entity.Product;
import com.example.SharmiSpringBoot.ProductService.exception.ProductAlreadyExistException;
import com.example.SharmiSpringBoot.ProductService.exception.ResourceNotFoundException;
import com.example.SharmiSpringBoot.ProductService.mapper.ProductMapper;
import com.example.SharmiSpringBoot.ProductService.repository.ProductRepository;
import com.example.SharmiSpringBoot.ProductService.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto, new Product());
        Optional<Product> optionalProduct = productRepository.findByProductCode(productDto.getProductCode());
        if (optionalProduct.isPresent()) {
            throw new ProductAlreadyExistException("Product already registered with given code "
                    + productDto.getProductCode());
        }
        product.setCreatedAt(LocalDateTime.now());
        product.setCreatedBy("Anonymous");
        product.getUpdatedAt(LocalDateTime.now());
        product.getUpdatedBy(product.getProductName());
        productRepository.save(product);
    }

    @Override
    public ProductDto fetchProduct(String productCode) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ResourceNotFoundException("Product is unavailable"));
        return ProductMapper.mapToProductDto(product, new ProductDto());
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        boolean isUpdated = false;
        if (productDto != null) {
            Optional<Product> optionalProduct = Optional.ofNullable(
                    productRepository.findByProductCode(productDto.getProductCode())
                            .orElseThrow(() -> new ResourceNotFoundException("Product is unavailable"))
            );
            if (optionalProduct.isPresent()) {
                Product existingProduct = optionalProduct.get();
                Product product = ProductMapper.mapToProduct(productDto, existingProduct);
                productRepository.save(product);
                isUpdated = true;
            }
        }
        return isUpdated;
    }

    @Override
    public List<ProductDto> fetchActiveProducts() {
        List<Product> activeProducts = productRepository.findAllByStatus("ACTIVE");
        return activeProducts.stream()
                .map(product -> ProductMapper.mapToProductDto(product, new ProductDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteProduct(String productCode) {
        boolean isDeleted = false;
        if (!productCode.isEmpty()) {
            Optional<Product> product = Optional.ofNullable(
                    productRepository.findByProductCode(productCode)
                            .orElseThrow(() -> new ResourceNotFoundException("Product is unavailable"))
            );
            if (product.isPresent()) {
                productRepository.deleteById(product.get().getProduct_id());
                isDeleted = true;
            }
        }
        return isDeleted;
    }
}
