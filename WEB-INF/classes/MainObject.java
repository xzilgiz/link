/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 

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

/**
 *
 * @author yla
 */
@Entity
@Table(name = "MainObjects", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "MainObject.findAll", query = "SELECT m FROM MainObject m"),
    @NamedQuery(name = "MainObject.findById", query = "SELECT m FROM MainObject m WHERE m.id = :id"),
    @NamedQuery(name = "MainObject.findByDate", query = "SELECT m FROM MainObject m WHERE m.date = :date")})
public class MainObject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "ClassID", referencedColumnName = "Code")
    @ManyToOne(optional = false)
    private ClassType classID;

    @Basic(optional = false)
    @Column(name = "Date")
    private String date;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mainObjectID")
    private List<MainObjectParam> mainObjectParamList;

    public MainObject() {
    }

    public MainObject(Integer id) {
        this.id = id;
    }

    public MainObject(Integer id, String date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public ClassType getClassID() {return classID;}
    public void setClassID(ClassType classID) {this.classID = classID;}

    public List<MainObjectParam> getMainObjectParamList() {return mainObjectParamList;}
    public void setMainObjectParamList(List<MainObjectParam> mainObjectParamList) {this.mainObjectParamList = mainObjectParamList;}

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
        return "examples.entites.MainObject[ id=" + id + " ]";
    }
    
}
