/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
//import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author yla
 */

@Entity
@Table(name = "EntityParams", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "EntityParam.findAll", query = "SELECT e FROM EntityParam e ORDER BY e.id"),
    @NamedQuery(name = "EntityParam.findByID", query = "SELECT e FROM EntityParam e WHERE e.id = :id")
    })
public class EntityParam implements Serializable {
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
    @Column(name = "Mark")
    private String mark;
    
    @Basic(optional = false)
    @Column(name = "Num")
    private Integer num;
    
    @Column(name = "Mask")
    private String mask;
    
    @Basic(optional = false)
    @Column(name = "Required")
    private String required;
    
    @Basic(optional = false)
    @Column(name = "Enabled")    
    private String enabled;

    @Basic(optional = false)
    @Column(name = "Visible")    
    private String visible;
    
    @Basic(optional = false)
    @Column(name = "DefaultValue")    
    private String defaultValue;

    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name = "EntityID")
    private EntityType entityID;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "entityParam")
    //private List<MainObjectParam> mainObjectParamList;

    public EntityParam() {
    }

    public EntityParam(Integer id) {
        this.id = id;
    }

    public EntityParam(EntityType entityID, Integer id, String sense, String mark, Integer num, String mask, String required, String enabled, String visible, String defaultValue) {
        this.entityID = entityID;
        this.id = id;
        this.sense = sense;
        this.mark = mark;
        this.num = num;
        this.mask = mask;
        this.required = required;
        this.enabled = enabled;
        this.visible = visible;
        this.defaultValue = defaultValue;
    }

    public EntityType getEntityID() {return entityID;}
    public void setEntityID(EntityType entityID) {this.entityID = entityID;}

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

    public String getMark() {return mark;}
    public void setMark(String mark) {
        String oldMark = this.mark;
        this.mark = mark;
        changeSupport.firePropertyChange("mark", oldMark, mark);
    }

    public int getNum() {return num;}
    public void setNum(Integer num) {
        Integer oldNum = this.num;
        this.num = num;
        changeSupport.firePropertyChange("num", oldNum, num);
    }

    public String getMask() {return mask;}
    public void setMask(String mask) {
        String oldMask = this.mask;
        this.mask = mask;
        changeSupport.firePropertyChange("mask", oldMask, mask);
    }

    public String getRequired() {return required;}
    public void setRequired(String required) {
        String oldRequired = this.required;
        this.required = required;
        changeSupport.firePropertyChange("required", oldRequired, required);
    }

    public String getEnabled() {return enabled;}
    public void setEnabled(String enabled) {
        String oldEnabled = this.enabled;
        this.enabled = enabled;
        changeSupport.firePropertyChange("enabled", oldEnabled, enabled);
    }

    public String getVisible() {return visible;}
    public void setVisible(String visible) {
        String oldVisible = this.visible;
        this.visible = visible;
        changeSupport.firePropertyChange("visible", oldVisible, visible);
    }

    public String getDefaultValue() {return defaultValue;}
    public void setDefaultValue(String defaultValue) {
        String oldDefaultValue = this.defaultValue;
        this.defaultValue = defaultValue;
        changeSupport.firePropertyChange("defaultValue", oldDefaultValue, defaultValue);
    }

    //public List<MainObjectParam> getMainObjectParamList() {return mainObjectParamList;}
    //public void setMainObjectParamList(List<MainObjectParam> mainObjectParamList) {this.mainObjectParamList = mainObjectParamList;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityParam)) {
            return false;
        }
        EntityParam other = (EntityParam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[\"" + entityID.getID() + 
          "\",\"" + id + 
          "\",\"" + sense + 
          "\",\"" + mark + 
          "\",\"" + num + 
          "\",\"" + mask + 
          "\",\"" + required + 
          "\",\"" + enabled + 
          "\",\"" + visible + 
          "\",\"" + defaultValue + "\"]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
