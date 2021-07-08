package hu.alkfejl.controller;

import hu.alkfejl.dao.*;
import hu.alkfejl.model.Allat;
import hu.alkfejl.model.Orokbefogadas;
import hu.alkfejl.model.Orokbefogado;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;



@WebServlet("/OrokbefogadasController")
public class OrokbefogadasController extends HttpServlet {
    private OrokbefogadasDAO orokbefogadas_dao = new OrokbefogadasDAOImpl();
    private AllatDAO allat_dao = new AllatDAOImpl();
    private OrokbefogadoDAO ember_dao = new OrokbefogadoDAOImpl();
    int allat_id;
    Allat allat;

    public OrokbefogadasController(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        //allat kivalasztasa id alapjan
        allat_id = Integer.parseInt(request.getParameter("allat_id"));
        List<Allat> allat_from_id = allat_dao.getAllatById(allat_id);
        allat = allat_from_id.get(0);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        Orokbefogadas orokbefogadas = new Orokbefogadas();

        try {
            //orokbefogado kivalasztasa email alapjan(email unique -> lehet kulcs)
            String e_mail = req.getParameter("email");
            List<Orokbefogado> orokbefogado_by_email = ember_dao.getOrokbefogadoByEmail(e_mail);

            orokbefogadas.setOrokbefogado_id(orokbefogado_by_email.get(0).getId());
            orokbefogadas.setAllat_id(allat_id);
            orokbefogadas.setMikor(LocalDate.now());
            if (allat.getName() == null){
                orokbefogadas.setN_name(req.getParameter("n_name"));
                allat.setName(req.getParameter("n_name"));
                allat_dao.save(allat);
            }else{
                orokbefogadas.setN_name(allat.getName());
            }
            orokbefogadas.setTipus(req.getParameter("tipus"));
            orokbefogadas.setMennyiseg(Integer.parseInt(req.getParameter("mennyiseg")));
            orokbefogadas.setGyakorisag(req.getParameter("gyakorisag"));

            orokbefogadas_dao.save(orokbefogadas);

            resp.sendRedirect("/allatkert_webapp_war/pages/allatok.jsp?succ=1");
        }
        catch (IndexOutOfBoundsException exc){
            resp.sendRedirect("/allatkert_webapp_war/pages/allatok.jsp?error=4");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
  }
}
