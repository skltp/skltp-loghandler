package se.skltp.loghandler.models.dao;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import se.skltp.loghandler.models.entity.Vardenhet;

@Repository
@Transactional
public class VardenhetDao extends AnslutningChildDao<Vardenhet> {

    @Cacheable("vardenhet")
    public Vardenhet getByNameCreateIfNew(String name) {
        return super.getByNameCreateIfNew(name);
    }

    @Override
    protected Vardenhet getNewObject() {
        return new Vardenhet();
    }

    @Override
    protected String getNameQueryName() {
        return "VardenhetByName";
    }

    @Override
    protected String getNameQueryId() {
        return "VardenhetById";
    }

    @Override
    protected Class<Vardenhet> getEntityClass() {
        return Vardenhet.class;
    }

}
