package com.teta.request;

import com.teta.model.Category;
import lombok.Data;

import java.util.List;
@Data
public class CreateBookRequest {

    private String name;

    private String author;

    private String description;

    private Long price;

    private Category category;
    private List<String> images;

    private Long storeId;

    private boolean isAvailable;
    private boolean isNovel;
    private boolean isComic;
}
