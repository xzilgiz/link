import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MeshJpaServlet
 */

public class MeshJpaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static javax.persistence.EntityManager entityManager;
  private static java.util.List<Mesh> meshList;
  private static javax.persistence.Query meshQuery;

  private static String action;
  private static String id;
  private static String tag;
  private static String sense;

    
  public MeshJpaServlet() {
    // TODO Auto-generated constructor stub
  }

  private String Render() {
    String result = "";
    int cur_position = 0;
    int max_position = meshList.size();

    result = "{ \"aaData\": [";
    
    for(Mesh ml : meshList) {
      cur_position++;
      if(cur_position == max_position)
        result = result + ml;
      else
        result = result + ml + ",";
    }
    
    result += "] }";

    return result;

  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String ActionNote = "";
    action = request.getParameter("action");

    entityManager = javax.persistence.Persistence.createEntityManagerFactory("jdbc:sqlite:newd.dbPU").createEntityManager();
    MeshControl meshcontrol = new MeshControl(entityManager);
    
    if(action.toString().equals("view")) {
      meshList = meshcontrol.findAllMeshs();
      response.setContentType("text/plain");  
      //response.setCharacterEncoding("UTF-8"); 
      response.getWriter().write(Render()); 
    } else {
      id = request.getParameter("id");
      tag = request.getParameter("tag");
      sense = request.getParameter("sense");

    }

    if(action.toString().equals("ins")) {
      //INSERT
      entityManager.getTransaction().begin();
      meshcontrol.createMesh(Integer.parseInt(id), tag, sense);
      entityManager.getTransaction().commit();
      ActionNote = "Добавление произведено успешно";
    }
   
    if(action.toString().equals("upd")) {
      //UPDATE
      entityManager.getTransaction().begin();
      meshcontrol.changeMesh(Integer.parseInt(id), tag, sense);
      entityManager.getTransaction().commit();
      ActionNote = "Изменение произведено успешно";
    }
    
    if(action.toString().equals("del")) {
      entityManager.getTransaction().begin();
      meshcontrol.removeMesh(Integer.parseInt(id));
      entityManager.getTransaction().commit();
      ActionNote = "Удаление произведено успешно";
    }
    
    entityManager.close();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
  }

}
