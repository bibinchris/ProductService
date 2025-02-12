package com.poductservice.productservice.thirdPartyClients.fakeStoreClient;

import com.poductservice.productservice.aop.ExecutionTimeLogger;
import com.poductservice.productservice.dtos.FakeStoreProductDto;
import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.exceptions.ProductNotFoundException;
import com.poductservice.productservice.thirdPartyClients.ThirdPartyInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Slf4j
@Component
public class FakeStoreClient implements ThirdPartyInterface {
    private final RestTemplateBuilder restTemplateBuilder;
    private final String genericProductUrl;
    private final String specificProductUrl;

    public FakeStoreClient(@Value("${fakestore.api.url}") String fakeStoreUrl,
                           @Value("${fakestore.api.path.products}") String pathForProducts,
                           RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.genericProductUrl = fakeStoreUrl + pathForProducts;
        this.specificProductUrl = fakeStoreUrl + pathForProducts + "/{id}";
    }

    @Override
    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
        log.info("Inside getProductById()");
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity
                = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class, id);

        if(responseEntity.getBody()==null){
            throw new ProductNotFoundException("Product not found for id : "+id);
        }
        return responseEntity.getBody();
    }

    @ExecutionTimeLogger
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

    @ExecutionTimeLogger
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

    @ExecutionTimeLogger
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

    @ExecutionTimeLogger
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
