package co.com.foodbank.user.sdk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
public interface ISDKUser {



    /**
     * Method to update contributions in provider
     * 
     * @param idProvider
     * @return {@code ResponseProviderData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceIllegalArgumentException
     */
    ResponseProviderData updateContribution(ContributionData contribution,
            String idVault)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException;;



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
    ResponseProviderData updateVaultInProvider(VaultDTO dto, String idProvider)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException;



    /**
     * Method to find a provider by sucursal.
     * 
     * @param id
     * @return {@code ResponseProviderData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceIllegalArgumentException
     */
    ResponseProviderData findProviderBySucursal(String id)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException;



}
