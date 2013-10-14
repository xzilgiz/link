
/**
 * Write a description of class EntityParamControl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EntityParamControl {

    private EntityManager em;

    public EntityParamControl(EntityManager em) {
        this.em = em;
    }

    public EntityParam createEntityParam(int entityID, int code, String sense, String mark, int num, String mask, String required, String enabled, String visible, String defaultValue) {        
        EntityTypeControl entitytypecontrol = new EntityTypeControl(em);
        EntityParam et = new EntityParam(entitytypecontrol.findByCodeEntityType(entityID), code, sense, mark, num, mask, required, enabled, visible, defaultValue);
        em.persist(et);
        return et;
    }

    public void removeEntityParam(int code) {
        EntityParam et = findByCodeEntityParam(code);
        if (et != null) {
            em.remove(et);
        }
    }

    public EntityParam changeEntityParam(int entityID, int code, String sense, String mark, int num, String mask, String required, String enabled, String visible, String defaultValue) {
        EntityParam et = findByCodeEntityParam(code);
        if (et != null) {
            EntityTypeControl entitytypecontrol = new EntityTypeControl(em);
            et.setEntityID(entitytypecontrol.findByCodeEntityType(entityID));
            et.setSense(sense);
            et.setMark(mark);
            et.setRequired(required);
            et.setNum(num); 
            et.setMask(mask);
            et.setRequired(required);
            et.setEnabled(enabled);
            et.setVisible(visible);
            et.setDefaultValue(defaultValue);
        }
        return et;
    }

    public EntityParam findByCodeEntityParam(int code) {
        TypedQuery<EntityParam> query =
          em.createNamedQuery("EntityParam.findByCode", EntityParam.class);
          query.setParameter("code", code);
        return query.getResultList().get(0);
    }

    public List<EntityParam> findAllEntityParams() {
        TypedQuery<EntityParam> query =
          em.createNamedQuery("EntityParam.findAll", EntityParam.class);
        return query.getResultList();
    }
}