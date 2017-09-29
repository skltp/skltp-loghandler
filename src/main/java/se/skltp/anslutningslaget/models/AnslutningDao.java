package se.skltp.anslutningslaget.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

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
  /*public List<Anslutning> getAll() {
    return entityManager.createQuery("from Anslutning").getResultList();
  }*/

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
  
} // class UserDao
