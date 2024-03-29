
/**
 * Write a description of class ClassParamControl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MainObjectControl {

    private EntityManager em;

    public MainObjectControl(EntityManager em) {
        this.em = em;
    }

    public MainObject createMainObject(int classID) {
        ClassTypeControl classtypecontrol = new ClassTypeControl(em);
        
        MainObject et = new MainObject();
        et.setClassID(classtypecontrol.findByIDClassType(classID));
        
        em.persist(et);
        return et;
    }

    public void removeMainObject(int id) {
        MainObject et = findByIDMainObject(id);
        if (et != null) {
            em.remove(et);
        }
    }

    public MainObject changeMainObject(int id, int classID) {
        MainObject et = findByIDMainObject(id);
        if (et != null) {
            ClassTypeControl classtypecontrol = new ClassTypeControl(em);
            et.setClassID(classtypecontrol.findByIDClassType(classID));
        }
        return et;
    }

    public MainObject findByIDMainObject(int id) {
        TypedQuery<MainObject> query =
          em.createNamedQuery("MainObject.findById", MainObject.class);
          query.setParameter("id", id);
        return query.getResultList().get(0);
    }

    public List<MainObject> findAllMainObject() {
        TypedQuery<MainObject> query =
          em.createNamedQuery("MainObject.findAll", MainObject.class);
        return query.getResultList();
    }
}