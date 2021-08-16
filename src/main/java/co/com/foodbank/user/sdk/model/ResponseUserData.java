package co.com.foodbank.user.sdk.model;

import co.com.foodbank.address.dto.AddressDTO;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.user.sdk.model 12/08/2021
 */
public class ResponseUserData {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phones;
    private boolean state = false;
    private AddressDTO address;

    /**
     * Default constructor.
     */
    public ResponseUserData() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }


}
