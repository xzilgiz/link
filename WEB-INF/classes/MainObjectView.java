import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MeshJpaServlet
 */

public class MainObjectView extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static javax.persistence.EntityManager entityManager;
  private static java.util.List<MainObject> mainObjectList;
  private static javax.persistence.Query mainObjectQuery;

  private static String action;
  private static String id;
  private static String classID;
  
  public MainObjectView() {
    // TODO Auto-generated constructor stub
  }

  private String Render() {
    String result = "";
    int cur_position = 0;
    int max_position = mainObjectList.size();

    result = "[";
    
    for(MainObject ml : mainObjectList) {
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
    MainObjectControl mainobjectcontrol = new MainObjectControl(entityManager);
    
    if(action.toString().equals("view")) {
      mainObjectList = mainobjectcontrol.findAllMainObject();
      response.setContentType("text/plain");  
      //response.setCharacterEncoding("UTF-8"); 
      response.getWriter().write(Render()); 
    } else {
      id = request.getParameter("id");
      classID = request.getParameter("classID");
    }

    if(action.toString().equals("ins")) {
      //INSERT
      entityManager.getTransaction().begin();
      mainobjectcontrol.createMainObject(Integer.parseInt(id), Integer.parseInt(classID));
      entityManager.getTransaction().commit();
      ActionNote = "Добавление произведено успешно";
    }
   
    if(action.toString().equals("upd")) {
      //UPDATE
      entityManager.getTransaction().begin();
      mainobjectcontrol.changeMainObject(Integer.parseInt(id), Integer.parseInt(classID));
      entityManager.getTransaction().commit();
      ActionNote = "Изменение произведено успешно";
    }
    
    if(action.toString().equals("del")) {
      entityManager.getTransaction().begin();
      mainobjectcontrol.removeMainObject(Integer.parseInt(id));
      entityManager.getTransaction().commit();
      ActionNote = "Удаление произведено успешно";
    }
    
    entityManager.close();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
  }

}