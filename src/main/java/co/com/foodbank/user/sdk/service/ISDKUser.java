package co.com.foodbank.user.sdk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import co.com.foodbank.contribution.state.ContributionData;
import co.com.foodbank.user.dto.request.RequestVolunterData;
import co.com.foodbank.user.sdk.exception.SDKUserNotFoundException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceIllegalArgumentException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceNotAvailableException;
import co.com.foodbank.user.sdk.model.ResponseBeneficiaryData;
import co.com.foodbank.user.sdk.model.ResponseProviderData;
import co.com.foodbank.user.sdk.model.ResponseUserData;
import co.com.foodbank.user.sdk.model.ResponseVolunteerData;
import co.com.foodbank.vault.dto.VaultDTO;


/**
 * @author mauricio.londono@gmail.com co.com.foodbank.user.sdk.service
 *         28/06/2021
 */
public interface ISDKUser {



    /**
     * Method to find a Beneficiary.
     * 
     * @param id
     * @return {@code ResponseBeneficiaryData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserNotFoundException
     */
    ResponseBeneficiaryData findBeneficiaryById(String id)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException;



    /**
     * Method to find Provider
     * 
     * @param id
     * @return {@code ResponseProviderData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceIllegalArgumentException
     */
    ResponseProviderData findProviderById(String id)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException;



    /**
     * Method to find User in general
     * 
     * @param id
     * @return {@codeResponseUserData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserNotFoundException
     */
    ResponseUserData findUserById(String id)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException;



    /**
     * Method to find Volunteer.
     * 
     * @param data
     * @return {@code ResponseVolunteerData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceIllegalArgumentException
     */
    ResponseVolunteerData findVolunteer(RequestVolunterData data)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException;



    /**
     * Method to find user by parameters.
     * 
     * @param id
     * @return {@code ResponseUserData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceIllegalArgumentException
     */
    ResponseUserData findUser(String name, String email, String phones)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException;



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
     * Method to update vault in provider.
     * 
     * @param dto
     * @param idProvider
     * @return {@code ResponseProviderData}
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
     * Method to find a provider by sucursal id.
     * 
     * @param id
     * @return {@code ResponseProviderData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceIllegalArgumentException
     */
    ResponseProviderData findProviderBySucursal(String idVault)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceException, SDKUserServiceNotAvailableException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException;



}
