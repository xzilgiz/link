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
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author yla
 */
@Entity
@Table(name = "ClassParams", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "ClassParam.findAll", query = "SELECT c FROM ClassParam c"),
    @NamedQuery(name = "ClassParam.findByCode", query = "SELECT c FROM ClassParam c WHERE c.code = :code")})
public class ClassParam implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private Integer code;

    @Basic(optional = false)
    @Column(name = "Num")
    private int num;
    
    @JoinColumn(name = "ClassID", referencedColumnName = "Code")
    @ManyToOne(optional = false)
    private ClassType classID;
    
    @JoinColumn(name = "EntityID", referencedColumnName = "Code")
    @ManyToOne(optional = false)
    private EntityType entityID;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "classParam")
    //private List<MainObjectParam> mainObjectParamList;

   public ClassParam() {
   }

   public ClassParam(Integer code) {
       this.code = code;
   }

   public ClassParam(Integer code, Integer num, ClassType classID, EntityType entityID) {
       this.code = code;
       this.num = num;
       this.classID = classID;
       this.entityID = entityID;
   }

   public Integer getCode() {return code;}
   public void setCode(Integer code) {
       Integer oldCode = this.code;
       this.code = code;
       changeSupport.firePropertyChange("code", oldCode, code);
   }
    
   public Integer getNum() {return num;}
   public void setNum(Integer num) {
       Integer oldNum = this.num;
       this.num = num;
       changeSupport.firePropertyChange("num", oldNum, num);
   }
    
   public ClassType getClassID() {return classID;}
   public void setClassID(ClassType classID) {this.classID = classID;}

   public EntityType getEntityID() {return entityID;}
   public void setEntityID(EntityType entityID) {this.entityID = entityID;}
    
   //public List<MainObjectParam> getMainObjectParamList() {return mainObjectParamList;}
   //public void setMainObjectParamList(List<MainObjectParam> mainObjectParamList) {this.mainObjectParamList = mainObjectParamList;}

   @Override
   public int hashCode() {
       int hash = 0;
       hash += (entityID != null ? entityID.hashCode() : 0);
       return hash;
   }

   @Override
   public boolean equals(Object object) {
       // TODO: Warning - this method won't work in the case the id fields are not set
       if (!(object instanceof ClassParam)) {
           return false;
       }
       ClassParam other = (ClassParam) object;
       if ((this.entityID == null && other.entityID != null) || (this.entityID != null && !this.entityID.equals(other.entityID))) {
          return false;
       }
       return true;
   }

   @Override
   public String toString() {
        return "[\"" + code + 
          "\",\"" + num + 
          "\",\"" + classID.getCode() + 
          "\",\"" + entityID.getCode() + "\"]";
   }
    
   public void addPropertyChangeListener(PropertyChangeListener listener) {
       changeSupport.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
       changeSupport.removePropertyChangeListener(listener);
   } 
}