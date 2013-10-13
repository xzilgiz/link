
/**
 * Write a description of class ClassParamControl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClassParamControl {

    private EntityManager em;

    public ClassParamControl(EntityManager em) {
        this.em = em;
    }

    public ClassParam createClassParam(int code, int num, int classID, int entityID) {
        ClassTypeControl classtypecontrol = new ClassTypeControl(em);
        EntityTypeControl entitytypecontrol = new EntityTypeControl(em);
        ClassParam et = new ClassParam(code, num, classtypecontrol.findByCodeClassType(classID), entitytypecontrol.findByCodeEntityType(entityID));
        em.persist(et);
        return et;
    }

    public void removeClassParam(int code) {
        ClassParam et = findByCodeClassParam(code);
        if (et != null) {
            em.remove(et);
        }
    }

    public ClassParam changeClassParam(int code, int num, int classID, int entityID) {
        ClassParam et = findByCodeClassParam(code);
        if (et != null) {
            ClassTypeControl classtypecontrol = new ClassTypeControl(em);
            EntityTypeControl entitytypecontrol = new EntityTypeControl(em);

            et.setNum(num);
            et.setClassID(classtypecontrol.findByCodeClassType(classID));
            et.setEntityID(entitytypecontrol.findByCodeEntityType(entityID));            
        }
        return et;
    }

    public ClassParam findByCodeClassParam(int code) {
        TypedQuery<ClassParam> query =
          em.createNamedQuery("ClassParam.findByCode", ClassParam.class);
          query.setParameter("code", code);
        return query.getResultList().get(0);
    }

    public List<ClassParam> findAllClassParams() {
        TypedQuery<ClassParam> query =
          em.createNamedQuery("ClassParam.findAll", ClassParam.class);
        return query.getResultList();
    }
}