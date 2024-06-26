package services.shop.Dtos.EntitiesDto.CustomersDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCustomerDto {
    private String fullName;
    private String email;
    private String address;
}
