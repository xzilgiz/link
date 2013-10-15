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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;

/**
 *
 * @author yla
 */
@Entity
@Table(name = "MainObjects", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "MainObject.findAll", query = "SELECT m FROM MainObject m"),
    @NamedQuery(name = "MainObject.findById", query = "SELECT m FROM MainObject m WHERE m.id = :id")})
public class MainObject implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name = "ClassID")
    private ClassType classID;

    //@Basic(optional = false)
    //@Column(name = "Date")
    //private String date;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "mainObjectID")
    //private List<MainObjectParam> mainObjectParamList;

    public MainObject() {
    }

    public MainObject(Integer id, ClassType classID) {
        this.id = id;
        this.classID = classID;
    }

    //public MainObject(Integer id, String date) {
    //    this.id = id;
    //    this.date = date;
    //}

    public Integer getId() {return id;}
    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    //public String getDate() {return date;}
    //public void setDate(String date) {this.date = date;}

    public ClassType getClassID() {return classID;}
    public void setClassID(ClassType classID) {this.classID = classID;}

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
        if (!(object instanceof MainObject)) {
            return false;
        }
        MainObject other = (MainObject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[\"" + id + "\",\"" + classID.getCode() + "\"]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
