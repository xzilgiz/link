import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MeshJpaServlet
 */

public class ClassTypeView extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static javax.persistence.EntityManager entityManager;
  private static java.util.List<ClassType> classTypeList;
  private static javax.persistence.Query classTypeQuery;
  private static ClassType classtype;
  
  private static String action;
  private static String code;
  private static String sense;

    
  public ClassTypeView() {
    // TODO Auto-generated constructor stub
  }

  private String Render() {
    String result = "[";
    int cur_position = 0;
    int max_position = classTypeList.size();

    for(ClassType ml : classTypeList) {
      cur_position++;
      if(cur_position == max_position)
        result = result + ml;
      else
        result = result + ml + ",";
    }
    
    result += "]";
    return result;
  }

  private String RenderSelect() {
    String result = "";
    for(ClassType ml : classTypeList) {
      result = result + "<option value=\""+ ml.getID() +"\">" + ml.getID() + " - " + ml.getSense() +"</option>";
    }    
    return result;
  }
  
  //����� ������� �������
  private String RenderFind() {
    String result = "";
    boolean noValue = false;
    //������ �������� � ��������� �������
    for(MainObject mo : classtype.getMainObjectList()) {
      //���������� ������ ������ 
      for(ClassParam cp : classtype.getClassParamList()) {
        //�������� ��������
        result = result + "<h4>" + mo.getID() + " | " + cp.getEntityID().getSense() + "</h4>";
        //����� �� ���������� ��������
        for(EntityParam ep : cp.getEntityID().getEntityParamList()) {
          result = result + ep.getSense() + " : ";
          noValue = false;
          //���������� �������� ���������
          for(MainObjectParam mop : mo.getMainObjectParamList()) {
            if(mop.getEntityParamID().getID() == ep.getID()) {
              result = result + mop.getValue();
              noValue = true;
            }
          }
          
          if(!noValue) {result = result + " -";}
          
          result = result + "<br>";
        }
       
      }
        
    }
   
    return result;
  }

  //����� ����� ���������� ������
  private String RenderAdd() {
    String result = "";
    
    result = result + "<form id=\"AddObjectForm\" action=\"ClassTypeView\" method=\"post\">";
    result = result + " <input type=\"hidden\" name=\"" + "classID" + "\" value=\"" + classtype.getID() + "\">";
    //����� �� ���������� ������
    for(ClassParam ml : classtype.getClassParamList()) {
       result = result + "<h4>" + ml.getEntityID().getSense() + "</h4>";
       //����� �� ���������� ���������
       for(EntityParam mp : ml.getEntityID().getEntityParamList()) {
         result = result + 
           "  " + mp.getSense() + 
           " <input type=\"text\" name=\"" + mp.getMark() + 
           "\" value=\"" + mp.getDefaultValue() + "\"><br>";
       }
    }
    
    result = result + "<input type=\"submit\" id=\"add_object\" value=\"Add\"/>";
    result = result + "</form>";
    return result;
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String ActionNote = "";
    action = request.getParameter("action");

    entityManager = javax.persistence.Persistence.createEntityManagerFactory("jdbc:sqlite:newd.dbPU").createEntityManager();
    ClassTypeControl classtypecontrol = new ClassTypeControl(entityManager);
    
    if(action.toString().equals("view")) {
      classTypeList = classtypecontrol.findAllClassTypes();
      response.setContentType("text/plain");  
      //response.setCharacterEncoding("UTF-8"); 
      response.getWriter().write(Render()); 
      
    } else if (action.toString().equals("select")){
      classTypeList = classtypecontrol.findAllClassTypes();
      response.setContentType("text/plain");  
      //response.setCharacterEncoding("UTF-8"); 
      response.getWriter().write(RenderSelect());
      
    } else if (action.toString().equals("find")){
      code = request.getParameter("code");
      classtype = classtypecontrol.findByIDClassType(Integer.parseInt(code));
      response.setContentType("text/plain");  
      //response.setCharacterEncoding("UTF-8"); 
      response.getWriter().write(RenderFind());
      
    } else if (action.toString().equals("add")){
      code = request.getParameter("code");
      classtype = classtypecontrol.findByIDClassType(Integer.parseInt(code));
      response.setContentType("text/plain");  
      //response.setCharacterEncoding("UTF-8"); 
      response.getWriter().write(RenderAdd());  
      
    } else {
      code = request.getParameter("code");
      sense = request.getParameter("sense");
    }

    if(action.toString().equals("ins")) {
      //INSERT
      entityManager.getTransaction().begin();
      classtypecontrol.createClassType(Integer.parseInt(code), sense);
      entityManager.getTransaction().commit();
      ActionNote = "���������� ����������� �������";
    }
   
    if(action.toString().equals("upd")) {
      //UPDATE
      entityManager.getTransaction().begin();
      classtypecontrol.changeClassType(Integer.parseInt(code), sense);
      entityManager.getTransaction().commit();
      ActionNote = "��������� ����������� �������";
    }
    
    if(action.toString().equals("del")) {
      entityManager.getTransaction().begin();
      classtypecontrol.removeClassType(Integer.parseInt(code));
      entityManager.getTransaction().commit();
      ActionNote = "�������� ����������� �������";
    }
    
    entityManager.close();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //��������� ��� ��������
    String action = request.getParameter("action");
    String classID = request.getParameter("classID");
    String objectID = request.getParameter("objectID");
    entityManager = javax.persistence.Persistence.createEntityManagerFactory("jdbc:sqlite:newd.dbPU").createEntityManager();
    
      ClassTypeControl ctc = new ClassTypeControl(entityManager);
      MainObjectControl moc = new MainObjectControl(entityManager);
      
      entityManager.getTransaction().begin();
      //���������� ��� ������
      ClassType ct = ctc.findByIDClassType(Integer.parseInt(classID));
      //������ ������
      MainObject mo = moc.createMainObject(ct.getID());
      //java.util.List<MainObjectParam> mopList;
      //��������� ������ �����������
      //������� ��������� ������, �.�. ��������
      for(ClassParam cplist : ct.getClassParamList()) {
        //������� ��������� ��������
        for(EntityParam eplist : cplist.getEntityID().getEntityParamList()) {
          //��������� ������ �� ��������
          String aparam = request.getParameter(eplist.getMark());
          if(aparam != null && !aparam.equals("")) {
            //������ ����� �������� �������
            MainObjectParam mop = new MainObjectParam(mo, eplist, aparam);
            entityManager.persist(mop);
          }
        }
      }

      entityManager.getTransaction().commit();
          
    entityManager.close();
  }

}