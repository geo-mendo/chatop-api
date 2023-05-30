package com.chatop.api.dto;

import com.chatop.api.models.RentalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalsResponseDTO {
    public List<RentalResponseDTO> rentals = new ArrayList<>();
}
