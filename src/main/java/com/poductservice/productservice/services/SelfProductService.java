package com.poductservice.productservice.services;

import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.exceptions.ProductNotFoundException;
import com.poductservice.productservice.models.Product;
import com.poductservice.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productById = productRepository.findById(UUID.fromString("f19d66ff-7b80-45b0-bf8d-764e94aee42a"));
        if(productById.isPresent()){
            return convertToGenericProductDto(productById.get());
        }
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() throws ProductNotFoundException {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) throws ProductNotFoundException {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    private static GenericProductDto convertToGenericProductDto(Product fakeStoreProductDto) {
        return GenericProductDto.builder().build()
//                .withId(fakeStoreProductDto.getId())
                .withTitle(fakeStoreProductDto.getTittle())
                .withPrice(fakeStoreProductDto.getPrice().getValue())
                .withCategory(fakeStoreProductDto.getCategory().getName())
                .withDescription(fakeStoreProductDto.getDescription())
                .withImage(fakeStoreProductDto.getImage());
    }
}
