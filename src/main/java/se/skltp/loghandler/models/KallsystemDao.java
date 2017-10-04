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
public class KallsystemDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Kallsystem> getByHSAId(String hsaid) {
        System.out.println("getByKallsystem");
        TypedQuery<Kallsystem> query = entityManager.createNamedQuery("KallsystemByHSAId", Kallsystem.class);
        query.setParameter("hsaid", hsaid);
        return query.getResultList();
    }

    @Cacheable("kallsystem")
    public Kallsystem getByHSAIdCreateIfNew(String hsaid) {
        List<Kallsystem> kallsystemList = getByHSAId(hsaid);
        if(kallsystemList.isEmpty()) {
            Kallsystem newKallsystem = new Kallsystem();
            newKallsystem.setHsaid(hsaid);
            kallsystemList.add(update(newKallsystem));
        }
        System.out.println(kallsystemList.get(0).getHsaid());
        return kallsystemList.get(0);
    }

    public Kallsystem update(Kallsystem kallsystem) {
        return entityManager.merge(kallsystem);
    }

}
