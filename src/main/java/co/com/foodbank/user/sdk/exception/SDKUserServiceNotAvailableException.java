package co.com.foodbank.user.sdk.exception;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.sdk.exception
 *         15/06/2021
 */
public class SDKUserServiceNotAvailableException
        extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SDKUserServiceNotAvailableException(Exception e) {
        super(e);
    }

}
