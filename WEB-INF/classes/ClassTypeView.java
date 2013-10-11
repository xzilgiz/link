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

  private static String action;
  private static String code;
  private static String sense;

    
  public ClassTypeView() {
    // TODO Auto-generated constructor stub
  }

  private String Render() {
    String result = "";
    int cur_position = 0;
    int max_position = classTypeList.size();

    result = "[";
    
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
    } else {
      code = request.getParameter("code");
      sense = request.getParameter("sense");
    }

    if(action.toString().equals("ins")) {
      //INSERT
      entityManager.getTransaction().begin();
      classtypecontrol.createClassType(Integer.parseInt(code), sense);
      entityManager.getTransaction().commit();
      ActionNote = "Добавление произведено успешно";
    }
   
    if(action.toString().equals("upd")) {
      //UPDATE
      entityManager.getTransaction().begin();
      classtypecontrol.changeClassType(Integer.parseInt(code), sense);
      entityManager.getTransaction().commit();
      ActionNote = "Изменение произведено успешно";
    }
    
    if(action.toString().equals("del")) {
      entityManager.getTransaction().begin();
      classtypecontrol.removeClassType(Integer.parseInt(code));
      entityManager.getTransaction().commit();
      ActionNote = "Удаление произведено успешно";
    }
    
    entityManager.close();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
  }

}