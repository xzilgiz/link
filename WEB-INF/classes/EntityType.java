/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
//import java.util.List;
import javax.persistence.Basic;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author yla
 */
@Entity
@Table(name = "EntityTypes", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "EntityType.findAll", query = "SELECT e FROM EntityType e"),
    @NamedQuery(name = "EntityType.findByCode", query = "SELECT e FROM EntityType e WHERE e.code = :code")})
public class EntityType implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private Integer code;
    
    @Basic(optional = false)
    @Column(name = "Sense")
    private String sense;
    
    @Basic(optional = false)
    @Column(name = "Required")
    private String required;
    
    //@OneToOne(cascade = CascadeType.ALL, mappedBy = "entityType")
    //private ClassParam classParam;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "entityID")
    //private List<EntityParam> entityParamList;

    public EntityType() {
    }

    public EntityType(Integer code) {
        this.code = code;
    }

    public EntityType(Integer code, String sense, String required) {
        this.code = code;
        this.sense = sense;
        this.required = required;
    }

    public Integer getCode() {return code;}
    public void setCode(Integer code) {
        Integer oldCode = this.code;
        this.code = code;
        changeSupport.firePropertyChange("code", oldCode, code);
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

    //public List<EntityParam> getEntityParamList() {return entityParamList;}
    //public void setEntityParamList(List<EntityParam> entityParamList) {this.entityParamList = entityParamList;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityType)) {
            return false;
        }
        EntityType other = (EntityType) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[\"" + code + "\",\"" + sense + "\",\"" + required + "\"]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
