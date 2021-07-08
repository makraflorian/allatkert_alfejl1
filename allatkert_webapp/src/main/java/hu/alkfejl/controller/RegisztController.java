package hu.alkfejl.controller;

import hu.alkfejl.dao.OrokbefogadoDAO;
import hu.alkfejl.dao.OrokbefogadoDAOImpl;
import hu.alkfejl.model.Orokbefogado;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/RegisztController")
public class RegisztController extends HttpServlet {
    private OrokbefogadoDAO dao = new OrokbefogadoDAOImpl();

    public RegisztController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");

        Orokbefogado orokbefogado = new Orokbefogado();

        try {
            orokbefogado.setName(req.getParameter("name"));
            orokbefogado.setEmail(req.getParameter("email"));

            dao.save(orokbefogado);

            resp.sendRedirect("/allatkert_webapp_war/");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
