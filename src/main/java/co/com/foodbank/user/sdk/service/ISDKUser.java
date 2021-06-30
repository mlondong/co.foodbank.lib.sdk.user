package co.com.foodbank.user.sdk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import co.com.foodbank.user.dto.ProviderDTO;
import co.com.foodbank.user.sdk.exception.SDKUserServiceException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceIllegalArgumentException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceNotAvailableException;
import co.com.foodbank.user.sdk.model.ResponseProviderData;


/**
 * @author mauricio.londono@gmail.com co.com.foodbank.user.sdk.service
 *         28/06/2021
 */
public interface ISDKUser {



    /**
     * @param id
     * @return {@code ProviderData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceIllegalArgumentException
     */
    ResponseProviderData findUserById(String id)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException;


    /**
     * @param dto
     * @param idProvider
     * @return {@code ProviderData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceIllegalArgumentException
     */
    ResponseProviderData updateVaultInProvider(ProviderDTO dto, String idProvider)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException;



}
