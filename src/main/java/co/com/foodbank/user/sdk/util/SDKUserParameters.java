package co.com.foodbank.user.sdk.util;

public final class SDKUserParameters {


    public static final String _PREFIX = "/user";
    public static final String _SUFIX_FIND_BYID = "/findById/";
    public static final String _SUFIX_FIND_BYSUCURSAL = "/findBySucursal/";
    public static final String _SUFIX_UPDATE_VAULT_PROV =
            "/updateVaultInProvider/";
    public static final String _SUFIX_UPDATE_CONTRIBUTION =
            "/updateContribution/";
    public static final String _SUFIX_FIND_BY_USER = "/findByUser?";
    public static final String _SUFIX_FIND_VOLUNTEER = "/findVolunteer?";


    public static final String PATH_FIND_BYID = _PREFIX + _SUFIX_FIND_BYID;
    public static final String PATH_FIND_BYSUCURSAL =
            _PREFIX + _SUFIX_FIND_BYSUCURSAL;
    public static final String PATH_UPDATE_VAULT_PROV =
            _PREFIX + _SUFIX_UPDATE_VAULT_PROV;
    public static final String PATH_UPDATE_CONTRIBUTION =
            _PREFIX + _SUFIX_UPDATE_CONTRIBUTION;
    public static final String PATH_FIND_BY_USER =
            _PREFIX + _SUFIX_FIND_BY_USER;
    public static final String PATH_FIND_VOLUNTEER =
            _PREFIX + _SUFIX_FIND_VOLUNTEER;



    public static final int FIND_ID_PROVIDER = 2;
    public static final int FIND_SUCURSAL_PROVIDER = 3;
    public static final int FIND_BY_USER = 6;
    public static final int FIND_BY_VOLUNTEER = 7;
    public static final int UPDATE_VAULT_PROVIDER = 4;
    public static final int UPDATE_CONTRIBUTION_PROVIDER = 5;

    public static final String PARAMETER_NAME = "/name/";
    public static final String PARAMETER_EMAIL = "/email/";
    public static final String PARAMETER_PHONE = "/phone/";
    public static final String PARAMETER_DNI = "dni=";
    public static final String PARAMETER_ID = "id=";
    public static final String PARAMETER_AMP = "&";

    public static final String PARAMETER_VAULT = "/vault/";
    public static final String PARAMETER_CONTRIBUTION = "/contribution/";



    public SDKUserParameters() {}
}
