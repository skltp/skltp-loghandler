package se.skltp.loghandler.models;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class TjanstekontraktDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Tjanstekontrakt> getByName(String name) {
        System.out.println("getByTjanstekontrakt");
        TypedQuery<Tjanstekontrakt> query = entityManager.createNamedQuery("TjanstekontraktByName", Tjanstekontrakt.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Cacheable("tjanstekontrakt")
    public Tjanstekontrakt getByNameCreateIfNew(String name) {
        List<Tjanstekontrakt> tjanstekontraktList = getByName(name);
        if(tjanstekontraktList.isEmpty()) {
            Tjanstekontrakt newTjanstekontrakt = new Tjanstekontrakt();
            newTjanstekontrakt.setName(name);
            tjanstekontraktList.add(update(newTjanstekontrakt));
        }
        System.out.println(tjanstekontraktList.get(0).getName());
        return tjanstekontraktList.get(0);
    }

    public Tjanstekontrakt update(Tjanstekontrakt tjanstekontrakt) {
        return entityManager.merge(tjanstekontrakt);
    }

}
