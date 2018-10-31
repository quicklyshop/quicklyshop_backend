package com.eci.cosw.project.quicklyshop.service.partner.impl;

import com.eci.cosw.project.quicklyshop.model.Partner;
import com.eci.cosw.project.quicklyshop.service.partner.PartnerService;
import com.eci.cosw.project.quicklyshop.service.partner.exceptions.PartnerServiceException;
import com.eci.cosw.project.quicklyshop.service.partner.persistence.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    @Override
    public void addPartner(Partner newPartner) throws PartnerServiceException {
        if (partnerRepository.existsPartnerByIdent(newPartner.getIdent())) {
            throw new PartnerServiceException("El socio ya esta registrado");
        }

        partnerRepository.save(newPartner);
    }

    @Override
    public void updatePartner(Partner partner) throws PartnerServiceException {
        if (!partnerRepository.existsPartnerByIdent(partner.getIdent())) {
            throw new PartnerServiceException("El socio no esta registrado");
        }

        partnerRepository.removePartnerByIdent(partner.getIdent());
        partnerRepository.save(partner);
    }

    @Override
    public void removePartnerByIdent(String ident) throws PartnerServiceException {
        if (!partnerRepository.existsPartnerByIdent(ident)) {
            throw new PartnerServiceException("El socio no esta registrado");
        }

        partnerRepository.removePartnerByIdent(ident);
    }

    @Override
    public Partner getPartnerByIdent(String ident) throws PartnerServiceException {
        if (!partnerRepository.existsPartnerByIdent(ident)) {
            throw new PartnerServiceException("El socio no esta registrado");
        }

        return partnerRepository.findPartnerByIdent(ident);
    }

    @Override
    public List<Partner> getAllPartners() {
        return partnerRepository.findAll();
    }
}
