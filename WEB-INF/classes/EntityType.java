/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import javax.persistence.*;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author yla
 */
@Entity
@Table(name = "EntityTypes", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "EntityType.findAll", query = "SELECT e FROM EntityType e ORDER BY e.id"),
    @NamedQuery(name = "EntityType.findByID", query = "SELECT e FROM EntityType e WHERE e.id = :id")})
public class EntityType implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "Sense")
    private String sense;
    
    @Basic(optional = false)
    @Column(name = "Required")
    private String required;
        
    @OneToMany(mappedBy = "EntityID", fetch = FetchType.LAZY)
    @OrderColumn(name="num")
    private List<EntityParam> entityParamList;
    
    public EntityType() {
    }

    public EntityType(Integer id) {
        this.id = id;
    }

    public EntityType(Integer id, String sense, String required) {
        this.id = id;
        this.sense = sense;
        this.required = required;
    }

    public Integer getID() {return id;}
    public void setID(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getSense() {return sense;}
    public void setSense(String sense) {
        String oldSense = this.sense;
        this.sense = sense;
        changeSupport.firePropertyChange("sense", oldSense, sense);
    }

    public String getRequired() {return required;}
    public void setRequired(String required) {
        String oldRequired = this.required;
        this.required = required;
        changeSupport.firePropertyChange("required", oldRequired, required);
    }

    //public ClassParam getClassParam() {return classParam;}
    //public void setClassParam(ClassParam classParam) {this.classParam = classParam;}

    public List<EntityParam> getEntityParamList() {return entityParamList;}
    public void setEntityParamList(List<EntityParam> entityParamList) {this.entityParamList = entityParamList;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityType)) {
            return false;
        }
        EntityType other = (EntityType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[\"" + id + "\",\"" + sense + "\",\"" + required + "\"]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
