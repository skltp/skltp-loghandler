package se.skltp.loghandler.models.dao;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import se.skltp.loghandler.models.entity.Tjanstekontrakt;

@Repository
@Transactional
public class TjanstekontraktDao extends AnslutningChildDao<Tjanstekontrakt>{

    @Cacheable("tjanstekontrakt")
    public Tjanstekontrakt getByNameCreateIfNew(String name) {
        return super.getByNameCreateIfNew(name);
    }

    @Override
    protected Tjanstekontrakt getNewObject() {
        return new Tjanstekontrakt();
    }

    @Override
    protected String getNameQueryName() {
        return "TjanstekontraktByName";
    }

    @Override
    protected String getNameQueryId() {
        return "TjanstekontraktById";
    }

    @Override
    protected Class<Tjanstekontrakt> getEntityClass() {
        return Tjanstekontrakt.class;
    }

}
