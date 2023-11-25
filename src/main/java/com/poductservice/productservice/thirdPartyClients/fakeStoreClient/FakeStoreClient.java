package com.poductservice.productservice.thirdPartyClients.fakeStoreClient;

import com.poductservice.productservice.dtos.FakeStoreProductDto;
import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.exceptions.ProductNotFoundException;
import com.poductservice.productservice.thirdPartyClients.ThirdPartyInterface;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient implements ThirdPartyInterface {
    private final RestTemplateBuilder restTemplateBuilder;
    private final String genericProductUrl = "https://fakestoreapi.com/products";
    private final String specificProductUrl = "https://fakestoreapi.com/products/{id}";

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity
                = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class, id);

        if(responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not found for id : "+id);
        }
        return responseEntity.getBody();
    }

    @Override
    public List<FakeStoreProductDto> getAllProducts() throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity
                = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);

        if(responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not found");
        }
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public FakeStoreProductDto deleteProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        if(responseEntity == null || responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not found for id : "+id);
        }
        return responseEntity.getBody();
    }

    @Override
    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(genericProductUrl, genericProductDto, FakeStoreProductDto.class);

        if(responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not found");
        }

        return responseEntity.getBody();
    }

    @Override
    public FakeStoreProductDto updateProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto genericProductDto = getProductById(id);
        genericProductDto.setCategory("xyz");

        RestTemplate restTemplate = this.restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        if(responseEntity == null || responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not updated for id : "+id);
        }
        return responseEntity.getBody();
    }


}
