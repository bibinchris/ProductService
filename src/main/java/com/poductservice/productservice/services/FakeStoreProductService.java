package com.poductservice.productservice.services;

import com.poductservice.productservice.aop.*;
import com.poductservice.productservice.dtos.FakeStoreProductDto;
import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.exceptions.ProductNotFoundException;
import com.poductservice.productservice.thirdPartyClients.fakeStoreClient.FakeStoreClient;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final FakeStoreClient fakeStoreAdaptor;
    private RedisTemplate<String, FakeStoreProductDto> redisTemplate;

    @Autowired
    public FakeStoreProductService(FakeStoreClient fakeStoreAdaptor, RedisTemplate redisTemplate) {
        this.fakeStoreAdaptor = fakeStoreAdaptor;
        this.redisTemplate = redisTemplate;
    }

    @ExecutionTimeLogger
    @Override
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get("Product", id);
        if(fakeStoreProductDto==null){
            fakeStoreProductDto = this.fakeStoreAdaptor.getProductById(id);
            redisTemplate.opsForHash().put("Product", id, fakeStoreProductDto);
        }
        return convertToGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public List<GenericProductDto> getAllProducts() throws ProductNotFoundException {
        return this.fakeStoreAdaptor.getAllProducts().stream().map(FakeStoreProductService::convertToGenericProductDto).toList();
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws ProductNotFoundException {
        return convertToGenericProductDto(this.fakeStoreAdaptor.deleteProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) throws ProductNotFoundException {
        return convertToGenericProductDto(this.fakeStoreAdaptor.createProduct(genericProductDto));
    }

    @Override
    public GenericProductDto updateProductById(Long id) throws ProductNotFoundException {
        return convertToGenericProductDto(this.fakeStoreAdaptor.updateProductById(id));
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
