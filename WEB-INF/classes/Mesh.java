/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author yla
 */
@Entity
@Table(name = "mesh", catalog = "", schema = "")
//@NamedQueries({
//    @NamedQuery(name = "Mesh.findAll", query = "SELECT m FROM Mesh m ORDER BY m.id"),
//    @NamedQuery(name = "Mesh.findById", query = "SELECT m FROM Mesh m WHERE m.id = :id ORDER BY m.id"),
//    @NamedQuery(name = "Mesh.findByTag", query = "SELECT m FROM Mesh m WHERE m.tag = :tag ORDER BY m.id"),
//    @NamedQuery(name = "Mesh.findBySense", query = "SELECT m FROM Mesh m WHERE m.sense = :sense ORDER BY m.id")})
public class Mesh implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tag")
    private String tag;
    @Column(name = "sense")
    private String sense;

    public Mesh() {
    }

    public Mesh(Integer id) {
        this.id = id;
    }

    public Mesh(Integer id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Mesh(Integer id, String tag, String sense) {
        this.id = id;
        this.tag = tag;
        this.sense = sense;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        String oldTag = this.tag;
        this.tag = tag;
        changeSupport.firePropertyChange("tag", oldTag, tag);
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        String oldSense = this.sense;
        this.sense = sense;
        changeSupport.firePropertyChange("sense", oldSense, sense);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesh)) {
            return false;
        }
        Mesh other = (Mesh) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[\"" + id + "\",\" " + tag + "\",\" " + sense + "\"]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
