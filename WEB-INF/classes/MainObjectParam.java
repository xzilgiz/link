/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author yla
 */
@Entity
@Table(name = "MainObjectParams", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "MainObjectParam.findAll", query = "SELECT m FROM MainObjectParam m"),
    @NamedQuery(name = "MainObjectParam.findByParamID", query = "SELECT m FROM MainObjectParam m WHERE m.paramID = :paramID"),
    @NamedQuery(name = "MainObjectParam.findByValue", query = "SELECT m FROM MainObjectParam m WHERE m.value = :value")})
public class MainObjectParam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @JoinColumn(name = "MainObjectID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private MainObject mainObjectID;

    @Id
    @JoinColumns({
        @JoinColumn(name = "ClassID", referencedColumnName = "ClassID"),
        @JoinColumn(name = "EntityID", referencedColumnName = "EntityID")})
    @ManyToOne(optional = false)
    private ClassParam classParam;

    @Id
    @JoinColumns({
        @JoinColumn(name = "EntityID", referencedColumnName = "EntityID"),
        @JoinColumn(name = "ParamID", referencedColumnName = "Code", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private EntityParam entityParam;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ParamID")
    private Integer paramID;
        
    @Basic(optional = false)
    @Column(name = "Value")
    private String value;
    
    public MainObjectParam() {
    }

    public MainObjectParam(Integer paramID) {
        this.paramID = paramID;
    }

    public MainObjectParam(Integer paramID, String value) {
        this.paramID = paramID;
        this.value = value;
    }

    public Integer getParamID() {return paramID;}
    public void setParamID(Integer paramID) {this.paramID = paramID;}

    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}

    public MainObject getMainObjectID() {return mainObjectID;}
    public void setMainObjectID(MainObject mainObjectID) {this.mainObjectID = mainObjectID;}

    public ClassParam getClassParam() {return classParam;}
    public void setClassParam(ClassParam classParam) {this.classParam = classParam;}

    public EntityParam getEntityParam() {return entityParam;}
    public void setEntityParam(EntityParam entityParam) {this.entityParam = entityParam;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paramID != null ? paramID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MainObjectParam)) {
            return false;
        }
        MainObjectParam other = (MainObjectParam) object;
        if ((this.paramID == null && other.paramID != null) || (this.paramID != null && !this.paramID.equals(other.paramID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "examples.entites.MainObjectParam[ paramID=" + paramID + " ]";
    }
    
}
