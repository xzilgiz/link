
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

    public EntityType createEntityType(int id, String sense, String required) {
        EntityType et = new EntityType(id, sense, required);
        em.persist(et);
        return et;
    }

    public void removeEntityType(int id) {
        EntityType et = em.find(EntityType.class, id);
        if (et != null) {
            if (et.getEntityParamList().isEmpty()) {
                em.remove(et);
            }
        }
    }

    public EntityType changeEntityType(int id, String sense, String required) {
        EntityType et = em.find(EntityType.class, id);
        if (et != null) {
            et.setSense(sense);
            et.setRequired(required);
        }
        return et;
    }

    public List<EntityType> findAllEntityTypes() {
        TypedQuery<EntityType> query =
          em.createNamedQuery("EntityType.findAll", EntityType.class);
        return query.getResultList();
    }
    
    public EntityType findByIDEntityType(int id) {
        TypedQuery<EntityType> query =
          em.createNamedQuery("EntityType.findByID", EntityType.class);
          query.setParameter("id", id);
        return query.getResultList().get(0);
    }

}