package co.com.foodbank.user.sdk.model;

import co.com.foodbank.address.dto.AddressDTO;
import co.com.foodbank.vehicule.dto.interfaces.IVehicule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseVolunteerData information about Volunteer.
 * 
 * @author mauricio.londono@gmail.com co.com.foodbank.user.sdk.model 16/08/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVolunteerData {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phones;
    private boolean state = false;
    private AddressDTO address;

    public Long dni;
    public IVehicule vehicule;
}
