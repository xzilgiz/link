/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import javax.persistence.*;

/**
 *
 * @author yla
 */
@Entity
@Table(name = "MainObjectParams", catalog = "", schema = "")
public class MainObjectParam implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "MainObjectID", referencedColumnName = "ID")
    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    private MainObject mainObjectID;

    @Id
    @JoinColumn(name = "EntityParamID", referencedColumnName = "ID")
    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @OrderBy(value="num")
    private EntityParam entityParamID;
    
    @Basic(optional = false)
    @Column(name = "Value")
    private String value;
    
    public MainObjectParam() {
    }

    public MainObjectParam(MainObject mainObjectID) {
        this.mainObjectID = mainObjectID;
    }

    public MainObjectParam(MainObject mainObjectID, EntityParam entityParamID, String value) {
        this.mainObjectID = mainObjectID;
        this.entityParamID = entityParamID;
        this.value = value;
    }

    public MainObject getMainObjectID() {return mainObjectID;}
    public void setMainObjectID(MainObject mainObjectID) {this.mainObjectID = mainObjectID;}
        
    public EntityParam getEntityParamID() {return entityParamID;}
    public void setEntityParamID(EntityParam entityParamID) {this.entityParamID = entityParamID;}

    public String getValue() {return value;}
    public void setValue(String value) {
        String oldValue = this.value;
        this.value = value;
        changeSupport.firePropertyChange("value", oldValue, value);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mainObjectID != null ? mainObjectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MainObjectParam)) {
            return false;
        }
        MainObjectParam other = (MainObjectParam) object;
        if ((this.mainObjectID == null && other.mainObjectID != null) || (this.mainObjectID != null && !this.mainObjectID.equals(other.mainObjectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[\"" + mainObjectID.getID() + "\",\"" + entityParamID.getID() + "\",\"" + value + "\"]";
    }
    
}
