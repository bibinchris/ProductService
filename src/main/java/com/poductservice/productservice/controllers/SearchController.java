package com.poductservice.productservice.controllers;

import com.poductservice.productservice.dtos.*;
import com.poductservice.productservice.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public List<GenericProductDto> searchProduct(@RequestBody SearchRequestDto requestDto){
        return searchService.searchProduct(requestDto.getTittle(), requestDto.getPageNumber(), requestDto.getPageSize(), requestDto.getSortParams());
    }
}
