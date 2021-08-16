package co.com.foodbank.user.sdk.model;

import java.util.Collection;
import co.com.foodbank.address.dto.AddressDTO;
import co.com.foodbank.vault.dto.VaultData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseProviderData class information about Provider.
 * 
 * @author mauricio.londono@gmail.com co.com.foodbank.user.dto 28/06/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProviderData {

    private String id;
    private String name;
    private String email;
    private AddressDTO address;
    private String password;
    private String phones;
    private boolean state;

    public Long cuil;
    public String legalRepresentation;
    public Collection<VaultData> sucursal;

}
