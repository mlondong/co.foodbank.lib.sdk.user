package co.com.foodbank.user.sdk.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
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
import co.com.foodbank.user.dto.request.RequestVolunterData;
import co.com.foodbank.user.sdk.exception.SDKUserNotFoundException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceIllegalArgumentException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceNotAvailableException;
import co.com.foodbank.user.sdk.model.ResponseProviderData;
import co.com.foodbank.user.sdk.model.ResponseUserData;
import co.com.foodbank.user.sdk.model.ResponseVolunteerData;
import co.com.foodbank.user.sdk.util.UrlUser;
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

    @Autowired
    private UrlUser urlUser;

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

            String response = restTemplate
                    .exchange(urlUser.toUpdateVaultInProvider(idProvider),
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
     * Method to find an User like Provider, Volunteer or Beneficiary.
     */
    @Override
    public ResponseProviderData findUserById(String id)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {
        try {
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

            String response = restTemplate.exchange(urlUser.toUserById(id),
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

            String response =
                    restTemplate
                            .exchange(urlUser.toUserBySucursal(id),
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


    /*************************************************************************************************************************/

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
                            .exchange(urlUser.toUpdateContribution(idVault),
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

    @Override
    public ResponseVolunteerData findVolunteer(RequestVolunterData data)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {

        try {
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);


            String response = restTemplate
                    .exchange(urlUser.toVolunteer(data.getId(), data.getDni()),
                            HttpMethod.GET, entity, String.class)
                    .getBody();

            return objectMapper.readValue(response,
                    new TypeReference<ResponseVolunteerData>() {});

        } catch (HttpClientErrorException i) {

            if (i.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new SDKUserServiceIllegalArgumentException(i);
            }
            if (i.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new SDKUserNotFoundException(data.toString(),
                        i.getResponseBodyAsString());
            }
            throw new SDKUserServiceException(i);
        } catch (ResourceAccessException i) {
            throw new SDKUserServiceNotAvailableException(i);
        } catch (Exception i) {
            throw new SDKUserServiceException(i);
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

            String response =
                    restTemplate
                            .exchange(urlUser.toUser(name, email, phones),
                                    HttpMethod.GET, entity, String.class)
                            .getBody();


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


}
