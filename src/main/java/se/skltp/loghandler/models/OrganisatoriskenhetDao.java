package se.skltp.loghandler.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class OrganisatoriskenhetDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Organisatoriskenhet> getByHSAId(String hsaid) {
        System.out.println("getByOrganisatoriskenhet");
        TypedQuery<Organisatoriskenhet> query = entityManager.createNamedQuery("OrganisatoriskenhetByHSAId", Organisatoriskenhet.class);
        query.setParameter("hsaid", hsaid);
        return query.getResultList();
    }

    @Cacheable("organisatoriskenhet")
    public Organisatoriskenhet getByHSAIdCreateIfNew(String hsaid) {
        List<Organisatoriskenhet> organisatoriskenhetList = getByHSAId(hsaid);
        if(organisatoriskenhetList.isEmpty()) {
            Organisatoriskenhet newOrganisatoriskenhet = new Organisatoriskenhet();
            newOrganisatoriskenhet.setHsaid(hsaid);
            organisatoriskenhetList.add(update(newOrganisatoriskenhet));
        }
        System.out.println(organisatoriskenhetList.get(0).getHsaid());
        return organisatoriskenhetList.get(0);
    }

    public Organisatoriskenhet update(Organisatoriskenhet organisatoriskenhet) {
        return entityManager.merge(organisatoriskenhet);
    }

}
