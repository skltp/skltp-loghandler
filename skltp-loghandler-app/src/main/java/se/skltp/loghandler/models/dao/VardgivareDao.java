package se.skltp.loghandler.models.dao;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import se.skltp.loghandler.models.entity.Vardgivare;

@Repository
@Transactional
public class VardgivareDao extends AnslutningChildDao<Vardgivare> {

    @Cacheable("vardgivare")
    public Vardgivare getByNameCreateIfNew(String name) {
        return super.getByNameCreateIfNew(name);
    }

    @Override
    protected Vardgivare getNewObject() {
        return new Vardgivare();
    }

    @Override
    protected String getNameQueryName() {
        return "VardgivareByName";
    }

    @Override
    protected String getNameQueryId() {
        return "VardgivareById";
    }

    @Override
    protected Class<Vardgivare> getEntityClass() {
        return Vardgivare.class;
    }

}
