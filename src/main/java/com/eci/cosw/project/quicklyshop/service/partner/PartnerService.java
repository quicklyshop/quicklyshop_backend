package com.eci.cosw.project.quicklyshop.service.partner;

import com.eci.cosw.project.quicklyshop.model.Partner;
import com.eci.cosw.project.quicklyshop.service.partner.exceptions.PartnerServiceException;

import java.util.List;

public interface PartnerService {

    /**
     * Anade un nuevo socio
      * @param newPartner socio
     * @throws PartnerServiceException el socio ya se encuentra registrado
     */
    void addPartner(Partner newPartner) throws PartnerServiceException;

    /**
     * Actualiza la informacion de un socio
      * @param partner socio
     * @throws PartnerServiceException el socio no esta registrado
     */
    void updatePartner(Partner partner) throws PartnerServiceException;

    /**
     * Elimina a un socio
     * @param id id del socio
     * @throws PartnerServiceException el socio no esta registrado
     */
    void removePartnerByIdent(String id) throws PartnerServiceException;

    /**
     * Obtiene el socio por id
     * @param id id del socio
     * @return socio
     * @throws PartnerServiceException el socio no esta registrado
     */
    Partner getPartnerByIdent(String id) throws PartnerServiceException;

    /**
     * Obtiene todos los socios registrados
     * @return socios
     */
    List<Partner> getAllPartners();

}
