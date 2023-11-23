package com.poductservice.productservice.services;

import com.poductservice.productservice.dtos.FakeStoreProductDto;
import com.poductservice.productservice.dtos.GenericProductDto;
import org.apache.commons.logging.Log;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpLogging;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String genericProductUrl = "https://fakestoreapi.com/products";
    private String specificProductUrl = "https://fakestoreapi.com/products/{id}";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> ob
                = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class, id);

        return convertToGenericProductDto(ob.getBody());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> listResponseEntity
                = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);
        return Arrays.stream(listResponseEntity.getBody()).map(ob -> convertToGenericProductDto(ob)).toList();
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> ob = restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return convertToGenericProductDto(ob.getBody());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(genericProductUrl, genericProductDto, FakeStoreProductDto.class);
        return convertToGenericProductDto(responseEntity.getBody());
    }

    @Override
    public GenericProductDto updateProductById(Long id) {
        GenericProductDto genericProductDto = getProductById(id);
        genericProductDto.setCategory("xyz");

        RestTemplate restTemplate = this.restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> execute = restTemplate.execute(specificProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        return convertToGenericProductDto(execute.getBody());
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
