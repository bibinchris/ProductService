package com.poductservice.productservice.services;

import com.poductservice.productservice.dtos.FakeStoreProductDto;
import com.poductservice.productservice.dtos.GenericProductDto;
import com.poductservice.productservice.exceptions.ProductNotFoundException;
import com.poductservice.productservice.thirdPartyClients.fakeStoreClient.FakeStoreAdaptor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final FakeStoreAdaptor fakeStoreAdaptor;
    public FakeStoreProductService(FakeStoreAdaptor fakeStoreAdaptor) {
        this.fakeStoreAdaptor = fakeStoreAdaptor;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {
        return convertToGenericProductDto(this.fakeStoreAdaptor.getProductById(id));
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
