package com.eci.cosw.project.quicklyshop.service.partner.persistence;

import com.eci.cosw.project.quicklyshop.model.Partner;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PartnerRepository extends MongoRepository<Partner, String> {

    Partner save(Partner partner);

    void removePartnerByIdent(String ident);

    Partner findPartnerByIdent(String ident);

    boolean existsPartnerByIdent(String ident);

}
