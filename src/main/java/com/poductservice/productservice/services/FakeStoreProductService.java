package com.poductservice.productservice.services;

import com.poductservice.productservice.dtos.FakeStoreProductDto;
import com.poductservice.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String getAllProductUrl = "https://fakestoreapi.com/products";
    private String getProductUrl = "https://fakestoreapi.com/products/{id}";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> ob
                = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class, id);

        return convertToGenericProductDto(ob.getBody());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> listResponseEntity
                = restTemplate.getForEntity(getAllProductUrl, FakeStoreProductDto[].class);
        return Arrays.stream(listResponseEntity.getBody()).map(ob -> convertToGenericProductDto(ob)).toList();
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void updateProductById(Long id) {

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
