
/**
 * Write a description of class ClassTypeControl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClassTypeControl {

    private EntityManager em;

    public ClassTypeControl(EntityManager em) {
        this.em = em;
    }

    public ClassType createClassType(int code, String sense) {
        ClassType et = new ClassType(code, sense);
        em.persist(et);
        return et;
    }

    public void removeClassType(int code) {
        ClassType et = em.find(ClassType.class, code);
        if (et != null) {
            em.remove(et);
        }
    }

    public ClassType changeClassType(int code, String sense) {
        ClassType et = em.find(ClassType.class, code);
        if (et != null) {
            et.setSense(sense);
        }
        return et;
    }

    public List<ClassType> findAllClassTypes() {
        TypedQuery<ClassType> query =
          em.createNamedQuery("ClassType.findAll", ClassType.class);
        return query.getResultList();
    }
    
    public ClassType findByCodeClassType(int code) {
        TypedQuery<ClassType> query =
          em.createNamedQuery("ClassType.findByCode", ClassType.class);
          query.setParameter("code", code);
        return query.getResultList().get(0);
    }
}