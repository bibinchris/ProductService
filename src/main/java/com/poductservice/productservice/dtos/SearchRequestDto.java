package com.poductservice.productservice.dtos;

import com.poductservice.productservice.models.*;
import com.poductservice.productservice.repositories.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
public class SearchRequestDto {
    private String tittle;
    private int pageNumber;
    private int pageSize;
    private List<SortParam> sortParams;

}
