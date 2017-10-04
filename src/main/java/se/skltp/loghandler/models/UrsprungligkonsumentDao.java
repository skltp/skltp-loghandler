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
public class UrsprungligkonsumentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Ursprungligkonsument> getByHSAId(String hsaid) {
        System.out.println("getByUrsprungligkonsument");
        TypedQuery<Ursprungligkonsument> query = entityManager.createNamedQuery("UrsprungligkonsumentByHSAId", Ursprungligkonsument.class);
        query.setParameter("hsaid", hsaid);
        return query.getResultList();
    }

    @Cacheable("ursprungligkonsument")
    public Ursprungligkonsument getByHSAIdCreateIfNew(String hsaid) {
        List<Ursprungligkonsument> ursprungligkonsumentList = getByHSAId(hsaid);
        if(ursprungligkonsumentList.isEmpty()) {
            Ursprungligkonsument newUrsprungligkonsument = new Ursprungligkonsument();
            newUrsprungligkonsument.setHsaid(hsaid);
            ursprungligkonsumentList.add(update(newUrsprungligkonsument));
        }
        System.out.println(ursprungligkonsumentList.get(0).getHsaid());
        return ursprungligkonsumentList.get(0);
    }

    public Ursprungligkonsument update(Ursprungligkonsument ursprungligkonsument) {
        return entityManager.merge(ursprungligkonsument);
    }

}
