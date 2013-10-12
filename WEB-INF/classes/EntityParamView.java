import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MeshJpaServlet
 */

public class EntityParamView extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static javax.persistence.EntityManager entityManager;
  private static java.util.List<EntityParam> entityParamList;
  private static javax.persistence.Query entityParamQuery;

  private static String action;
  private static String entityID;
  private static String code;
  private static String sense;
  private static String mark;
  private static String num;
  private static String mask;
  private static String required;
  private static String enabled;
  private static String visible;
  private static String defaultValue;
  
  public EntityParamView() {
    // TODO Auto-generated constructor stub
  }

  private String Render() {
    String result = "";
    int cur_position = 0;
    int max_position = entityParamList.size();

    result = "[";
    
    for(EntityParam ml : entityParamList) {
      cur_position++;
      if(cur_position == max_position)
        result = result + ml;
      else
        result = result + ml + ",";
    }
    
    result += "]";

    return result;

  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String ActionNote = "";
    action = request.getParameter("action");

    entityManager = javax.persistence.Persistence.createEntityManagerFactory("jdbc:sqlite:newd.dbPU").createEntityManager();
    EntityParamControl entityparamcontrol = new EntityParamControl(entityManager);
    
    if(action.toString().equals("view")) {
      entityParamList = entityparamcontrol.findAllEntityParams();
      response.setContentType("text/plain");  
      //response.setCharacterEncoding("UTF-8"); 
      response.getWriter().write(Render()); 
    } else {
      entityID = request.getParameter("entityID");
      code = request.getParameter("code");
      sense = request.getParameter("sense");
      mark = request.getParameter("mark");
      num = request.getParameter("num");
      mask = request.getParameter("mask");
      required = request.getParameter("required");
      enabled = request.getParameter("enabled");
      visible = request.getParameter("visible");
      defaultValue = request.getParameter("defaultValue");
    }

    if(action.toString().equals("ins")) {
      //INSERT
      entityManager.getTransaction().begin();
      entityparamcontrol.createEntityParam(Integer.parseInt(entityID), Integer.parseInt(code), sense, mark, Integer.parseInt(num), mask, required, enabled, visible, defaultValue);
      entityManager.getTransaction().commit();
      ActionNote = "Добавление произведено успешно";
    }
   
    if(action.toString().equals("upd")) {
      //UPDATE
      entityManager.getTransaction().begin();
      entityparamcontrol.changeEntityParam(Integer.parseInt(entityID), Integer.parseInt(code), sense, mark, Integer.parseInt(num), mask, required, enabled, visible, defaultValue);
      entityManager.getTransaction().commit();
      ActionNote = "Изменение произведено успешно";
    }
    
    if(action.toString().equals("del")) {
      entityManager.getTransaction().begin();
      entityparamcontrol.removeEntityParam(Integer.parseInt(code));
      entityManager.getTransaction().commit();
      ActionNote = "Удаление произведено успешно";
    }
    
    entityManager.close();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
  }

}