package ru.negative.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingResponseDTO {
    private Integer bookingid;
    private CreateBookingRequestDTO booking;

}
