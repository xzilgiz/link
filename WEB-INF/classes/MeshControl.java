
/**
 * Write a description of class MeshControl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MeshControl {

    private EntityManager em;

    public MeshControl(EntityManager em) {
        this.em = em;
    }

    public Mesh createMesh(int id, String tag, String sense) {
        Mesh mesh = new Mesh(id, tag, sense);
        em.persist(mesh);
        return mesh;
    }

    public void removeMesh(int id) {
        Mesh mesh = em.find(Mesh.class, id);
        if (mesh != null) {
            em.remove(mesh);
        }
    }

    public Mesh changeMesh(int id, String tag, String sense) {
        Mesh mesh = em.find(Mesh.class, id);
        if (mesh != null) {
            mesh.setSense(sense);
            mesh.setTag(tag);
        }
        return mesh;
    }

    public Mesh findMesh(int id) {
        return em.find(Mesh.class, id);
    }

    public List<Mesh> findAllMeshs() {
        TypedQuery<Mesh> query = em.createQuery("SELECT m FROM Mesh m ORDER BY m.id", Mesh.class);
        return query.getResultList();
    }
}