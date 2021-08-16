package co.com.foodbank.user.sdk.util;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.user.sdk.util 12/08/2021
 */
public final class UrlFindByUser {

    private String url;

    public UrlFindByUser() {}

    public String build(String name, String email, String phones) {
        this.url = SDKUserParameters.PARAMETER_NAME + name
                + SDKUserParameters.PARAMETER_EMAIL + email
                + SDKUserParameters.PARAMETER_PHONE + phones;
        return url;
    }

    public String getUrl() {
        return url;
    }


}
