
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

    public ClassType createClassType(int id, String sense) {
        ClassType et = new ClassType(id, sense);
        em.persist(et);
        return et;
    }

    public void removeClassType(int id) {
        ClassType et = em.find(ClassType.class, id);
        if (et != null) {
            if (et.getClassParamList().isEmpty()) {
                em.remove(et);
            }
        }
    }

    public ClassType changeClassType(int id, String sense) {
        ClassType et = em.find(ClassType.class, id);
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
    
    public ClassType findByIDClassType(int id) {
        TypedQuery<ClassType> query =
          em.createNamedQuery("ClassType.findByID", ClassType.class);
          query.setParameter("id", id);
        return query.getResultList().get(0);
    }
}