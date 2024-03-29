package co.com.foodbank.user.sdk.model;

import co.com.foodbank.address.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseUserData general information about User.
 * 
 * @author mauricio.londono@gmail.com co.com.foodbank.user.sdk.model 12/08/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserData {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phones;
    private boolean state;
    private AddressDTO address;

}
