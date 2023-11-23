package com.poductservice.productservice.services;

import com.poductservice.productservice.dtos.FakeStoreProductDto;
import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.exceptions.ProductNotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private final RestTemplateBuilder restTemplateBuilder;
    private final String genericProductUrl = "https://fakestoreapi.com/products";
    private final String specificProductUrl = "https://fakestoreapi.com/products/{id}";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity
                = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class, id);

        if(responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not found for id : "+id);
        }
        return convertToGenericProductDto(responseEntity.getBody());
    }

    @Override
    public List<GenericProductDto> getAllProducts() throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity
                = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);

        if(responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not found");
        }

        return Arrays.stream(responseEntity.getBody()).map(FakeStoreProductService::convertToGenericProductDto).toList();
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        if(responseEntity == null || responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not found for id : "+id);
        }
        return convertToGenericProductDto(responseEntity.getBody());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(genericProductUrl, genericProductDto, FakeStoreProductDto.class);

        if(responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not found");
        }

        return convertToGenericProductDto(responseEntity.getBody());
    }

    @Override
    public GenericProductDto updateProductById(Long id) throws ProductNotFoundException {
        GenericProductDto genericProductDto = getProductById(id);
        genericProductDto.setCategory("xyz");

        RestTemplate restTemplate = this.restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        if(responseEntity == null || responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not updated for id : "+id);
        }
        return convertToGenericProductDto(responseEntity.getBody());
    }

    private static GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        return GenericProductDto.builder().build()
                .withId(fakeStoreProductDto.getId())
                .withTitle(fakeStoreProductDto.getTitle())
                .withPrice(fakeStoreProductDto.getPrice())
                .withCategory(fakeStoreProductDto.getCategory())
                .withDescription(fakeStoreProductDto.getDescription())
                .withImage(fakeStoreProductDto.getImage());
    }
}
