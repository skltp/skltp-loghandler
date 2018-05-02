package se.skltp.loghandler.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import se.skltp.loghandler.models.entity.AnslutningChild;

@Repository
@Transactional
public abstract class AnslutningChildDao<T extends AnslutningChild> {

    @PersistenceContext
    private EntityManager entityManager;

    private List<T> getByName(String name) {
        TypedQuery<T> query = entityManager.createNamedQuery(getNameQueryName(), getEntityClass());
        query.setParameter("name", name);
        return query.getResultList();
    }

    protected synchronized T getByNameCreateIfNew(String name) {
    	if(name == null)
    		name = "";
        List<T> listOfT = getByName(name);
        if(listOfT.isEmpty()) {
            T instanceOfT = getNewObject();
            instanceOfT.setName(name);
            listOfT.add(update(instanceOfT));
        }
        return listOfT.get(0);
    }

    public T update(T child) {
        return entityManager.merge(child);
    }

    protected abstract T getNewObject();

    protected abstract String getNameQueryName();

    protected abstract String getNameQueryId();

    protected abstract Class<T> getEntityClass();

    public String getbyId(long id) {
        TypedQuery<T> query = entityManager.createNamedQuery(getNameQueryId(), getEntityClass());
        query.setParameter("id", id);
        List<T> resultList = query.getResultList();
        return resultList.size() > 0 ? resultList.get(0).getName() : "";
    }
}
