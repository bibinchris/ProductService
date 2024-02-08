package com.poductservice.productservice.services;

import com.poductservice.productservice.dtos.*;
import com.poductservice.productservice.models.*;
import com.poductservice.productservice.repositories.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<GenericProductDto> searchProduct(String title, int pageNumber, int pageSize, List<SortParam> sortParams){
        Sort sort;
        if("DESC".equalsIgnoreCase(sortParams.get(0).getSortType())){
            sort = Sort.by(sortParams.get(0).getSortParamName()).descending();
        }else {
            sort = Sort.by(sortParams.get(0).getSortParamName()).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return productRepository.findAllByTittleContainingIgnoreCase(title, pageable)
                .stream()
                .map(product -> convertToGenericProductDto(product))
                .collect(Collectors.toList());
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
