
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

    public ClassParam createClassParam(int id, int num, int classID, int entityID) {
        ClassTypeControl classtypecontrol = new ClassTypeControl(em);
        EntityTypeControl entitytypecontrol = new EntityTypeControl(em);
        ClassParam et = new ClassParam(id, num, classtypecontrol.findByIDClassType(classID), entitytypecontrol.findByIDEntityType(entityID));
        em.persist(et);
        return et;
    }

    public void removeClassParam(int id) {
        ClassParam et = findByIDClassParam(id);
        if (et != null) {
            em.remove(et);
        }
    }

    public ClassParam changeClassParam(int id, int num, int classID, int entityID) {
        ClassParam et = findByIDClassParam(id);
        if (et != null) {
            ClassTypeControl classtypecontrol = new ClassTypeControl(em);
            EntityTypeControl entitytypecontrol = new EntityTypeControl(em);

            et.setNum(num);
            et.setClassID(classtypecontrol.findByIDClassType(classID));
            et.setEntityID(entitytypecontrol.findByIDEntityType(entityID));            
        }
        return et;
    }

    public ClassParam findByIDClassParam(int id) {
        TypedQuery<ClassParam> query =
          em.createNamedQuery("ClassParam.findByID", ClassParam.class);
          query.setParameter("id", id);
        return query.getResultList().get(0);
    }

    public List<ClassParam> findAllClassParams() {
        TypedQuery<ClassParam> query =
          em.createNamedQuery("ClassParam.findAll", ClassParam.class);
        return query.getResultList();
    }
}