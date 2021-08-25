package co.com.foodbank.user.sdk.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.user.sdk.util 12/08/2021
 */
@Component
@Validated
public class UrlUser {

    @Value("${sdk.service.user.scheme}")
    private String urlScheme;

    @Value("${sdk.service.user.url}")
    private String urlBase;

    @Value("${urlSdlfindUserById}")
    private String urlSdlfindUserById;

    @Value("${urlSdlfindUserBySucursal}")
    private String urlSdlfindUserBySucursal;

    @Value("${urlSdlupdateVaultInProvider}")
    private String urlSdlupdateVaultInProvider;

    @Value("${urlSdlupdateContribution}")
    private String urlSdlupdateContribution;

    @Value("${urlSdlfindUser}")
    private String urlSdlfindUser;

    @Value("${urlSdlfindVolunteer}")
    public String urlSdlfindVolunteer;



    /**
     * Method to get URL for find User by id.
     * 
     * @param id
     * @return {@code String}
     */

    public String toUserById(String id) {
        return UriComponentsBuilder.newInstance().scheme(urlScheme)
                .host(urlBase).path(urlSdlfindUserById + id)
                .buildAndExpand(encode(id)).toString();
    }


    /**
     * Method to get URL for find User by sucursal.
     * 
     * @param id
     * @return {@code String}
     */

    public String toUserBySucursal(String id) {
        return UriComponentsBuilder.newInstance().scheme(urlScheme)
                .host(urlBase).path(urlSdlfindUserBySucursal + id)
                .buildAndExpand(encode(id)).toString();
    }


    /**
     * Method to get URL for Update Vault in Provider.
     * 
     * @param id
     * @return {@code String}
     */
    public String toUpdateVaultInProvider(String id) {
        return UriComponentsBuilder.newInstance().scheme(urlScheme)
                .host(urlBase).path(urlSdlupdateVaultInProvider + id)
                .buildAndExpand(encode(id)).toString();
    }



    /**
     * Method to get URL for Update Contribution in Provider.
     * 
     * @param id
     * @return {@code String}
     */
    public String toUpdateContribution(String idVault, String idContribution) {
        return UriComponentsBuilder.newInstance().scheme(urlScheme)
                .host(urlBase)
                .path(getPathContribution(idVault, idContribution)).build()
                .toString();
    }


    /**
     * Method to get URL for User.
     * 
     * @param name
     * @param email
     * @param phones
     * @return {@code String }
     */
    public String toUser(String name, String email, String phones) {
        return UriComponentsBuilder.newInstance().scheme(urlScheme)
                .host(urlBase).path(getPathUser(name, email, phones)).build()
                .toString();
    }

    /**
     * Method to get URL for Volunteer.
     * 
     * @param id
     * @param dni
     * @return {@code}
     */
    public String toVolunteer(String id, String dni) {
        return UriComponentsBuilder.newInstance().scheme(urlScheme)
                .host(urlBase).path(getPathVolunter(id, dni)).build()
                .toString();
    }


    private String getPathVolunter(String id, String dni) {
        return urlSdlfindVolunteer + SDKUserParameters.PARAMETER_ID + encode(id)
                + SDKUserParameters.PARAMETER_AMP
                + SDKUserParameters.PARAMETER_DNI + encode(dni);
    }

    private String getPathUser(String name, String email, String phones) {
        return urlSdlfindUser + SDKUserParameters.PARAMETER_NAME + name
                + SDKUserParameters.PARAMETER_EMAIL + encode(email)
                + SDKUserParameters.PARAMETER_PHONE + phones;
    }


    private String getPathContribution(String idVault, String idContribution) {
        return urlSdlupdateContribution + SDKUserParameters.PARAMETER_VAULT
                + encode(idVault) + SDKUserParameters.PARAMETER_CONTRIBUTION
                + encode(idContribution);
    }


    /**
     * Method for encode
     * 
     * @param value url
     * @return utf8 encoded url
     */
    private String encode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
