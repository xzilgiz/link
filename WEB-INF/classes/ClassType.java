/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import javax.persistence.*;

/**
 *
 * @author yla
 */
@Entity
@Table(name = "ClassTypes", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "ClassType.findAll", query = "SELECT c FROM ClassType c ORDER BY c.id"),
    @NamedQuery(name = "ClassType.findByID", query = "SELECT c FROM ClassType c WHERE c.id = :id")})
public class ClassType implements Serializable {
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
    
    @OneToMany(mappedBy = "classID", fetch = FetchType.LAZY)
    @OrderColumn(name="num")
    private List<ClassParam> classParamList;
    
    @OneToMany(mappedBy = "classID", fetch = FetchType.LAZY)
    private List<MainObject> mainObjectList;
    
    public ClassType() {
    }

    public ClassType(Integer id) {
        this.id = id;
    }

    public ClassType(Integer id, String sense) {
        this.id = id;
        this.sense = sense;
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

    public List<ClassParam> getClassParamList() {return classParamList;}
    public void setClassParamList(List<ClassParam> classParamList) {this.classParamList = classParamList;}
    
    public List<MainObject> getMainObjectList() {return mainObjectList;}
    public void setMainObjectList(List<MainObject> mainObjectList) {this.mainObjectList = mainObjectList;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassType)) {
            return false;
        }
        ClassType other = (ClassType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[\"" + id + "\",\"" + sense + "\"]";
    }
    
     public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
