
/**
 * Write a description of class EntityTypeControl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EntityTypeControl {

    private EntityManager em;

    public EntityTypeControl(EntityManager em) {
        this.em = em;
    }

    public EntityType createEntityType(int code, String sense, String required) {
        EntityType et = new EntityType(code, sense, required);
        em.persist(et);
        return et;
    }

    public void removeEntityType(int code) {
        EntityType et = em.find(EntityType.class, code);
        if (et != null) {
            em.remove(et);
        }
    }

    public EntityType changeEntityType(int code, String sense, String required) {
        EntityType et = em.find(EntityType.class, code);
        if (et != null) {
            et.setSense(sense);
            et.setRequired(required);
        }
        return et;
    }

    //public EntityType findEntityType(int code) {
    //    return em.find(EntityType.class, code);
    //}

    public List<EntityType> findAllEntityTypes() {
        TypedQuery<EntityType> query =
          em.createNamedQuery("EntityType.findAll", EntityType.class);
        return query.getResultList();
    }
    
    public EntityType findByCodeEntityType(int code) {
        TypedQuery<EntityType> query =
          em.createNamedQuery("EntityType.findByCode", EntityType.class);
          query.setParameter("code", code);
        return query.getResultList().get(0);
    }

}