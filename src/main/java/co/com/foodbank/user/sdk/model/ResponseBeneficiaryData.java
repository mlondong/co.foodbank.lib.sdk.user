package co.com.foodbank.user.sdk.model;

import co.com.foodbank.address.dto.AddressDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseBeneficiaryData {
    private String id;
    private String name;
    private String email;
    private AddressDTO address;
    private String password;
    private String phones;
    private boolean state;

    public String socialReason;
    public String category;
    public int size;
}
