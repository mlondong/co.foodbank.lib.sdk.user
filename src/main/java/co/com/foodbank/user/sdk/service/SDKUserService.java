package co.com.foodbank.user.sdk.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.com.foodbank.contribution.dto.ContributionData;
import co.com.foodbank.user.sdk.exception.SDKUserServiceException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceIllegalArgumentException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceNotAvailableException;
import co.com.foodbank.user.sdk.model.ResponseProviderData;
import co.com.foodbank.vault.dto.VaultDTO;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.user.sdk.service
 *         28/06/2021
 */
@Service
@Validated
public class SDKUserService implements ISDKUser {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${urlSdlfindUser}")
    private String urlSdlfindUser;

    @Value("${urlSdlfindUserBySucursal}")
    private String urlSdlfindUserBySucursal;

    @Value("${urlSdlupdateVaultInProvider}")
    private String urlSdlupdateVaultInProvider;

    @Value("${urlSdlupdateContribution}")
    private String urlSdlupdateContribution;



    public static final int FIND_ID_PROVIDER = 2;

    public static final int FIND_SUCURSAL_PROVIDER = 3;

    public static final int UPDATE_VAULT_PROVIDER = 4;

    public static final int UPDATE_CONTRIBUTION_PROVIDER = 5;



    /**
     * Method to update Vault in Provider.
     */
    @Override
    public ResponseProviderData updateVaultInProvider(VaultDTO dto,
            String idProvider)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException {

        try {

            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<VaultDTO> entity =
                    new HttpEntity<VaultDTO>(dto, httpHeaders);


            String response =
                    restTemplate
                            .exchange(
                                    getUrlProvider(idProvider,
                                            UPDATE_VAULT_PROVIDER),
                                    HttpMethod.PUT, entity, String.class)
                            .getBody();

            return objectMapper.readValue(response,
                    new TypeReference<ResponseProviderData>() {});



        } catch (ResourceAccessException e) {
            throw new SDKUserServiceNotAvailableException(e);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new SDKUserServiceIllegalArgumentException(e);
            }
            throw new SDKUserServiceException(e);
        } catch (Exception e) {
            throw new SDKUserServiceException(e);
        }


    }

    /*************************************************************************************************************************/

    /**
     * Method to find an User like Provider, Volunter or Beneficiary.
     */
    @Override
    public ResponseProviderData findUserById(String id)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException {

        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

        String response =
                restTemplate.exchange(getUrlProvider(id, FIND_ID_PROVIDER),
                        HttpMethod.GET, entity, String.class).getBody();


        return objectMapper.readValue(response,
                new TypeReference<ResponseProviderData>() {});
    }


    /**
     * Method to build URL in case to search a User by id.
     * 
     * @param id
     * @return {@code String}
     */
    private String getUrlProvider(String id, int option) {

        String url = null;

        switch (option) {
            case 2:
                url = urlSdlfindUser.concat(id);
                break;
            case 3:
                url = urlSdlfindUserBySucursal.concat(id);
                break;
            case 4:
                url = urlSdlupdateVaultInProvider.concat(id);
                break;
            case 5:
                url = urlSdlupdateContribution.concat(id);

            default:
                break;
        }
        return url;
    }

    /*************************************************************************************************************************/

    /**
     * Method to find an User by Sucursal like Provider.
     */
    @Override
    public ResponseProviderData findProviderBySucursal(String id)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException {

        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

        String response = restTemplate
                .exchange(getUrlProvider(id, FIND_SUCURSAL_PROVIDER),
                        HttpMethod.GET, entity, String.class)
                .getBody();


        return objectMapper.readValue(response,
                new TypeReference<ResponseProviderData>() {});
    }



    /**
     * Method to update contributions in provider.
     */
    @Override
    public ResponseProviderData updateContribution(
            ContributionData contribution, String idVault)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException {

        try {

            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<ContributionData> entity =
                    new HttpEntity<ContributionData>(contribution, httpHeaders);

            String response =
                    restTemplate
                            .exchange(
                                    getUrlProvider(idVault,
                                            UPDATE_CONTRIBUTION_PROVIDER),
                                    HttpMethod.PUT, entity, String.class)
                            .getBody();

            return objectMapper.readValue(response,
                    new TypeReference<ResponseProviderData>() {});

        } catch (ResourceAccessException e) {
            throw new SDKUserServiceNotAvailableException(e);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new SDKUserServiceIllegalArgumentException(e);
            }
            throw new SDKUserServiceException(e);
        } catch (Exception e) {
            throw new SDKUserServiceException(e);
        }
    }



}
