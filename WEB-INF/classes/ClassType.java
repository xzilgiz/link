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
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author yla
 */
@Entity
@Table(name = "ClassTypes", catalog = "", schema = "")
//@NamedQueries({
//    @NamedQuery(name = "ClassType.findAll", query = "SELECT c FROM ClassType c"),
//    @NamedQuery(name = "ClassType.findByCode", query = "SELECT c FROM ClassType c WHERE c.code = :code")})
public class ClassType implements Serializable {
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
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "classID")
    //private List<MainObject> mainObjectList;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "classID")
    //private List<ClassParam> classParamList;

    public ClassType() {
    }

    public ClassType(Integer code) {
        this.code = code;
    }

    public ClassType(Integer code, String sense) {
        this.code = code;
        this.sense = sense;
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

    //public List<MainObject> getMainObjectList() {return mainObjectList;}
    //public void setMainObjectList(List<MainObject> mainObjectList) {this.mainObjectList = mainObjectList;}

    //public List<ClassParam> getClassParamList() {return classParamList;}
    //public void setClassParamList(List<ClassParam> classParamList) {this.classParamList = classParamList;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassType)) {
            return false;
        }
        ClassType other = (ClassType) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[\"" + code + "\",\" " + sense + "\"]";
    }
    
     public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
