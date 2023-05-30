package com.chatop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequestDTO {
    Long id;
    String name ;
    int surface;
    double price;
    MultipartFile picture;
    String description;
    Long owner_id;
}
