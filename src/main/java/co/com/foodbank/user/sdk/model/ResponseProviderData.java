package co.com.foodbank.user.sdk.model;

import java.util.Collection;
import co.com.foodbank.address.dto.AddressDTO;
import co.com.foodbank.vault.dto.VaultData;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.user.dto 28/06/2021
 */
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


    public ResponseProviderData() {}


    public void setState(boolean state) {
        this.state = state;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }

    public void setLegalRepresentation(String legalRepresentation) {
        this.legalRepresentation = legalRepresentation;
    }

    public void setSucursal(Collection<VaultData> sucursal) {
        this.sucursal = sucursal;
    }


    public Long getCuil() {
        // TODO Auto-generated method stub
        return cuil;
    }


    public String getLegalRepresentation() {
        // TODO Auto-generated method stub
        return legalRepresentation;
    }


    public Collection<VaultData> getSucursal() {
        // TODO Auto-generated method stub
        return sucursal;
    }


    public String getId() {
        // TODO Auto-generated method stub
        return id;
    }


    public String getEmail() {
        // TODO Auto-generated method stub
        return email;
    }

    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    public String getPhones() {
        // TODO Auto-generated method stub
        return phones;
    }


    public AddressDTO getAddress() {
        // TODO Auto-generated method stub
        return address;
    }


    public boolean isState() {
        return state;
    }

}
