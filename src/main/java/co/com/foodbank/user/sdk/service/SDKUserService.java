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
import co.com.foodbank.contribution.state.ContributionData;
import co.com.foodbank.user.dto.request.RequestUserData;
import co.com.foodbank.user.sdk.exception.SDKUserNotFoundException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceIllegalArgumentException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceNotAvailableException;
import co.com.foodbank.user.sdk.model.ResponseProviderData;
import co.com.foodbank.user.sdk.model.ResponseUserData;
import co.com.foodbank.user.sdk.util.SDKUserParameters;
import co.com.foodbank.user.sdk.util.UrlFindByUser;
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

    @Value("${urlSdlfindUserParameters}")
    private String urlSdlfindUserParameters;



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


            String response = restTemplate.exchange(
                    getUrlProvider(idProvider,
                            SDKUserParameters.UPDATE_VAULT_PROVIDER),
                    HttpMethod.PUT, entity, String.class).getBody();

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
     * Method to find an User by multiples parameters
     * 
     * @throws SDKUserNotFoundException
     */

    @Override
    public ResponseUserData findUser(String name, String email, String phones)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {

        try {

            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

            String response = restTemplate.exchange(
                    getUrlProvider(integredValues(name, email, phones),
                            SDKUserParameters.UPDATE_BY_USER),
                    HttpMethod.GET, entity, String.class).getBody();


            return objectMapper.readValue(response,
                    new TypeReference<ResponseUserData>() {});

        } catch (HttpClientErrorException i) {

            if (i.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new SDKUserServiceIllegalArgumentException(i);
            }
            if (i.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new SDKUserNotFoundException(
                        new RequestUserData(name, email, phones).toString(),
                        i.getResponseBodyAsString());
            }
            throw new SDKUserServiceException(i);
        } catch (ResourceAccessException i) {
            throw new SDKUserServiceNotAvailableException(i);
        } catch (Exception i) {
            throw new SDKUserServiceException(i);
        }

    }



    private String integredValues(String name, String email, String phones) {
        return new UrlFindByUser().build(name, email, phones);
    }


    /*************************************************************************************************************************/

    /**
     * Method to find an User like Provider, Volunter or Beneficiary.
     */
    @Override
    public ResponseProviderData findUserById(String id)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {
        try {
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

            String response =
                    restTemplate
                            .exchange(
                                    getUrlProvider(id,
                                            SDKUserParameters.FIND_ID_PROVIDER),
                                    HttpMethod.GET, entity, String.class)
                            .getBody();


            return objectMapper.readValue(response,
                    new TypeReference<ResponseProviderData>() {});

        } catch (HttpClientErrorException i) {

            if (i.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new SDKUserServiceIllegalArgumentException(i);
            }
            if (i.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new SDKUserNotFoundException(id,
                        i.getResponseBodyAsString());
            }
            throw new SDKUserServiceException(i);
        } catch (ResourceAccessException i) {
            throw new SDKUserServiceNotAvailableException(i);
        } catch (Exception i) {
            throw new SDKUserServiceException(i);
        }
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

            case 6:
                url = urlSdlfindUserParameters.concat(id);


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
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {
        try {
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

            String response = restTemplate.exchange(
                    getUrlProvider(id,
                            SDKUserParameters.FIND_SUCURSAL_PROVIDER),
                    HttpMethod.GET, entity, String.class).getBody();


            return objectMapper.readValue(response,
                    new TypeReference<ResponseProviderData>() {});

        } catch (HttpClientErrorException i) {

            if (i.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new SDKUserServiceIllegalArgumentException(i);
            }
            if (i.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new SDKUserNotFoundException(id,
                        i.getResponseBodyAsString());
            }
            throw new SDKUserServiceException(i);
        } catch (ResourceAccessException i) {
            throw new SDKUserServiceNotAvailableException(i);
        } catch (Exception i) {
            throw new SDKUserServiceException(i);
        }
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

            String response = restTemplate.exchange(
                    getUrlProvider(idVault,
                            SDKUserParameters.UPDATE_CONTRIBUTION_PROVIDER),
                    HttpMethod.PUT, entity, String.class).getBody();

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
