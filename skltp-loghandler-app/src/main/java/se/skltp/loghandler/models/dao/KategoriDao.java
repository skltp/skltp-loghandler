package se.skltp.loghandler.models.dao;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import se.skltp.loghandler.models.entity.Kategori;

@Repository
@Transactional
public class KategoriDao extends AnslutningChildDao<Kategori> {

    @Cacheable("kategori")
    public Kategori getByNameCreateIfNew(String name) {
        return super.getByNameCreateIfNew(name);
    }

    @Override
    protected Kategori getNewObject() {
        return new Kategori();
    }

    @Override
    protected String getNameQueryName() {
        return "KategoriByName";
    }

    @Override
    protected String getNameQueryId() {
        return "KategoriById";
    }

    @Override
    protected Class<Kategori> getEntityClass() {
        return Kategori.class;
    }

}
