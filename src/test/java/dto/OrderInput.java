package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderInput {

    private final String fullname;
    private final String email;
    private final String phone;
    private final String address;
    private final String city;
    private final String zipcode;
    private final String country;
}
