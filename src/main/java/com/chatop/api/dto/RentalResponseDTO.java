package com.chatop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponseDTO {
    Long id;
    String name ;
    int surface;
    double price;
    String picture;
    String description;
    Long owner_id;
    LocalDate created_at;
    LocalDate updated_at;
}
