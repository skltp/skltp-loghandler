package se.skltp.loghandler.models.dao;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import se.skltp.loghandler.models.entity.Organisatoriskenhet;

@Repository
@Transactional
public class OrganisatoriskenhetDao extends AnslutningChildDao<Organisatoriskenhet> {

    @Cacheable("organisatoriskenhet")
    public Organisatoriskenhet getByNameCreateIfNew(String name) {
        return super.getByNameCreateIfNew(name);
    }

    @Override
    protected Organisatoriskenhet getNewObject() {
        return new Organisatoriskenhet();
    }

    @Override
    protected String getNameQueryName() {
        return "OrganisatoriskenhetByName";
    }

    @Override
    protected String getNameQueryId() {
        return "OrganisatoriskenhetById";
    }

    @Override
    protected Class<Organisatoriskenhet> getEntityClass() {
        return Organisatoriskenhet.class;
    }

}
