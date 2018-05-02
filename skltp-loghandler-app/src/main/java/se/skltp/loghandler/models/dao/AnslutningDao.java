package se.skltp.loghandler.models.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;
import se.skltp.loghandler.models.entity.Anslutning;

import java.util.List;

/**
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class AnslutningDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the user in the database.
   */
  public void create(Anslutning anslutning) {
    entityManager.persist(anslutning);
    return;
  }
  
  /**
   * Delete the user from the database.
   */
  public void delete(Anslutning anslutning) {
    if (entityManager.contains(anslutning))
      entityManager.remove(anslutning);
    else
      entityManager.remove(entityManager.merge(anslutning));
    return;
  }
  
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<Anslutning> getAll() {
    return entityManager.createQuery("from Anslutning").getResultList();
  }

  /**
   * Return the user having the passed id.
   */
  public Anslutning getById(long id) {
    return entityManager.find(Anslutning.class, id);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(Anslutning anslutning) {
    entityManager.merge(anslutning);
    return;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
//vardgivare,vardenhet,organisatoriskenhet,tjanstekontrakt,kategori,kallsystem,ursprungligkonsument
  public Anslutning getByExample(Anslutning example) {
    TypedQuery<Anslutning> query = entityManager.createQuery("SELECT o FROM Anslutning o where o.vardgivare=:vardgivare AND o.vardenhet=:vardenhet AND o.organisatoriskenhet=:organisatoriskenhet AND o.tjanstekontrakt=:tjanstekontrakt AND o.kategori=:kategori AND o.kallsystem=:kallsystem AND o.ursprungligkonsument=:ursprungligkonsument", Anslutning.class);
    query.setParameter("vardgivare", example.getVardgivare());
    query.setParameter("vardenhet", example.getVardenhet());
    query.setParameter("organisatoriskenhet", example.getOrganisatoriskenhet());
    query.setParameter("tjanstekontrakt", example.getTjanstekontrakt());
    query.setParameter("kategori", example.getKategori());
    query.setParameter("kallsystem", example.getKallsystem());
    query.setParameter("ursprungligkonsument", example.getUrsprungligkonsument());
    List<Anslutning> anslutningList = query.getResultList();
    return anslutningList.isEmpty() ? null : anslutningList.get(0);
  }
}
