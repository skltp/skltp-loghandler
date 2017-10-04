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
public class KategoriDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Kategori> getByKategori(String kategori) {
        System.out.println("getByKategori");
        TypedQuery<Kategori> query = entityManager.createNamedQuery("KategoriByKategori", Kategori.class);
        query.setParameter("kategori", kategori);
        return query.getResultList();
    }

    @Cacheable("kategori")
    public Kategori getByKategoriCreateIfNew(String kategori) {
        List<Kategori> kategoriList = getByKategori(kategori);
        if(kategoriList.isEmpty()) {
            Kategori newKategori = new Kategori();
            newKategori.setKategori(kategori);
            kategoriList.add(update(newKategori));
        }
        System.out.println(kategoriList.get(0).getKategori());
        return kategoriList.get(0);
    }

    public Kategori update(Kategori kategori) {
        return entityManager.merge(kategori);
    }

}
