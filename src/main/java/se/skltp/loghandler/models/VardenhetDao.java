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
public class VardenhetDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Vardenhet> getByHSAId(String hsaid) {
        System.out.println("getByVardenhet");
        TypedQuery<Vardenhet> query = entityManager.createNamedQuery("VardenhetByHSAId", Vardenhet.class);
        query.setParameter("hsaid", hsaid);
        return query.getResultList();
    }

    @Cacheable("vardenhet")
    public Vardenhet getByHSAIdCreateIfNew(String hsaid) {
        List<Vardenhet> vardenhetList = getByHSAId(hsaid);
        if(vardenhetList.isEmpty()) {
            Vardenhet newVardenhet = new Vardenhet();
            newVardenhet.setHsaid(hsaid);
            vardenhetList.add(update(newVardenhet));
        }
        System.out.println(vardenhetList.get(0).getHsaid());
        return vardenhetList.get(0);
    }

    public Vardenhet update(Vardenhet vardenhet) {
        return entityManager.merge(vardenhet);
    }

}
