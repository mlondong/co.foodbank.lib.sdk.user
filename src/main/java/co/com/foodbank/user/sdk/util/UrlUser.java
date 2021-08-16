package co.com.foodbank.user.sdk.util;

import org.springframework.beans.factory.annotation.Value;
import lombok.Data;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.user.sdk.util 12/08/2021
 */
@Data
public final class UrlUser {

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
    private String urlSdlfindVolunteer;

    private String url;


    /**
     * Method to build URL in case to search a User by id.
     * 
     * @param id
     * @return {@code String}
     */
    public String url(String id, int option) {

        String url = null;

        switch (option) {
            case 2:
                url = urlSdlfindUserById.concat(id);
                break;
            case 3:
                url = urlSdlfindUserBySucursal.concat(id);
                break;
            case 4:
                url = urlSdlupdateVaultInProvider.concat(id);
                break;
            case 5:
                url = urlSdlupdateContribution.concat(id);
                break;
            case 6:
                url = urlSdlfindUser;
                break;
            case 7:
                url = urlSdlfindVolunteer;
                break;

            default:
                break;
        }
        return url;
    }


    public String toUser(String name, String email, String phones) {
        return url = url(null, SDKUserParameters.FIND_BY_USER)
                + SDKUserParameters.PARAMETER_NAME + name
                + SDKUserParameters.PARAMETER_EMAIL + email
                + SDKUserParameters.PARAMETER_PHONE + phones;
    }


    public String toVolunteer(String id, String dni) {
        return url = url(null, SDKUserParameters.FIND_BY_VOLUNTEER)
                + SDKUserParameters.PARAMETER_ID + id
                + SDKUserParameters.PARAMETER_AMP
                + SDKUserParameters.PARAMETER_DNI + dni;
    }



}
