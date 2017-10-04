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
public class VardgivareDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Vardgivare> getByHSAId(String hsaid) {
        System.out.println("getByVardgivare");
        TypedQuery<Vardgivare> query = entityManager.createNamedQuery("VardgivareByHSAId", Vardgivare.class);
        query.setParameter("hsaid", hsaid);
        return query.getResultList();
    }

    @Cacheable("vardgivare")
    public Vardgivare getByHSAIdCreateIfNew(String hsaid) {
        List<Vardgivare> vardgivareList = getByHSAId(hsaid);
        if(vardgivareList.isEmpty()) {
            Vardgivare newVardgivare = new Vardgivare();
            newVardgivare.setHsaid(hsaid);
            vardgivareList.add(update(newVardgivare));
        }
        System.out.println(vardgivareList.get(0).getHsaid());
        return vardgivareList.get(0);
    }

    public Vardgivare update(Vardgivare vardgivare) {
        return entityManager.merge(vardgivare);
    }

}
