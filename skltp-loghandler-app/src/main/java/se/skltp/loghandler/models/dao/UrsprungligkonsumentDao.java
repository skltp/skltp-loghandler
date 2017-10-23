package se.skltp.loghandler.models.dao;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import se.skltp.loghandler.models.entity.Ursprungligkonsument;

@Repository
@Transactional
public class UrsprungligkonsumentDao extends AnslutningChildDao<Ursprungligkonsument> {

    @Cacheable("ursprungligkonsument")
    public Ursprungligkonsument getByNameCreateIfNew(String name) {
        return super.getByNameCreateIfNew(name);
    }

    @Override
    protected Ursprungligkonsument getNewObject() {
        return new Ursprungligkonsument();
    }

    @Override
    protected String getNameQueryName() {
        return "UrsprungligkonsumentByName";
    }

    @Override
    protected String getNameQueryId() {
        return "UrsprungligkonsumentById";
    }

    @Override
    protected Class<Ursprungligkonsument> getEntityClass() {
        return Ursprungligkonsument.class;
    }

}
