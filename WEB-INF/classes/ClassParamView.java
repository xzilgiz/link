import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MeshJpaServlet
 */

public class ClassParamView extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static javax.persistence.EntityManager entityManager;
  private static java.util.List<ClassParam> classParamList;
  private static javax.persistence.Query classParamQuery;

  private static String action;
  private static String code;
  private static String num;
  private static String classID;
  private static String entityID;
  
  public ClassParamView() {
    // TODO Auto-generated constructor stub
  }

  private String Render() {
    String result = "";
    int cur_position = 0;
    int max_position = classParamList.size();

    result = "[";
    
    for(ClassParam ml : classParamList) {
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
    ClassParamControl classparamcontrol = new ClassParamControl(entityManager);
    
    if(action.toString().equals("view")) {
      classParamList = classparamcontrol.findAllClassParams();
      response.setContentType("text/plain");  
      //response.setCharacterEncoding("UTF-8"); 
      response.getWriter().write(Render()); 
    } else {
      code = request.getParameter("code");
      num = request.getParameter("num");
      classID = request.getParameter("classID");
      entityID = request.getParameter("entityID");
    }

    if(action.toString().equals("ins")) {
      //INSERT
      entityManager.getTransaction().begin();
      classparamcontrol.createClassParam(Integer.parseInt(code), Integer.parseInt(num), Integer.parseInt(classID), Integer.parseInt(entityID));
      entityManager.getTransaction().commit();
      ActionNote = "Добавление произведено успешно";
    }
   
    if(action.toString().equals("upd")) {
      //UPDATE
      entityManager.getTransaction().begin();
      classparamcontrol.changeClassParam(Integer.parseInt(code), Integer.parseInt(num), Integer.parseInt(classID), Integer.parseInt(entityID));
      entityManager.getTransaction().commit();
      ActionNote = "Изменение произведено успешно";
    }
    
    if(action.toString().equals("del")) {
      entityManager.getTransaction().begin();
      classparamcontrol.removeClassParam(Integer.parseInt(code));
      entityManager.getTransaction().commit();
      ActionNote = "Удаление произведено успешно";
    }
    
    entityManager.close();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
  }

}