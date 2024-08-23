package com.teta.request;

import com.teta.model.Address;
import com.teta.model.ContactInformation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateStoreRequest {

    private Long id;
    private String name;
    private String description;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;

}
