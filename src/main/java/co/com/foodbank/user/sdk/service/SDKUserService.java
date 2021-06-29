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
import co.com.foodbank.user.dto.ProviderDTO;
import co.com.foodbank.user.sdk.exception.SDKUserServiceException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceIllegalArgumentException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceNotAvailableException;
import co.com.foodbank.user.sdk.model.ProviderData;

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


    @Value("${urlSdlupdateUser}")
    private String urlSdlupdateUser;


    /**
     * Method to update add Vault in Provider.
     */
    @Override
    public ProviderData updateVaultInProvider(ProviderDTO dto,
            String idProvider)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException {

        try {

            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<ProviderDTO> entity =
                    new HttpEntity<ProviderDTO>(dto, httpHeaders);


            // SE DEBEN CAMBIAR TODOS LOS SDK PARA QUE MANEJEN EL RESPONSEENTITY
            // Y SE ADICIONAN LOS MODELOS PARA EVITAR DEVOLVER STRING.
            String response =
                    restTemplate
                            .exchange(getUrlUpdateProvider(idProvider),
                                    HttpMethod.PUT, entity, String.class)
                            .getBody();

            return objectMapper.readValue(response,
                    new TypeReference<ProviderData>() {});



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



    /**
     * Method to provide URL to Update a Provider.
     * 
     * @param idProvider
     * @return {@code String}
     */
    private String getUrlUpdateProvider(String idProvider) {
        return urlSdlupdateUser.concat(idProvider);
    }



    /**
     * Method to find an User like Provider, Volunter or Beneficiary.
     */
    @Override
    public String findUserById(String id)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException {

        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

        return restTemplate.exchange(getUrlProvider(id), HttpMethod.GET, entity,
                String.class).getBody();

    }



    /**
     * Method to build URL in case to search a User by id.
     * 
     * @param id
     * @return {@code String}
     */
    private String getUrlProvider(String id) {
        return urlSdlfindUser.concat(id);
    }



}
