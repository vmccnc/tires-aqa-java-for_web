package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AccountRegistration {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
}