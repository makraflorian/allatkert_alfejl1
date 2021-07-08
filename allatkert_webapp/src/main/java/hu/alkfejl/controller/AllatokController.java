package hu.alkfejl.controller;

import hu.alkfejl.dao.AllatDAO;
import hu.alkfejl.dao.AllatDAOImpl;
import hu.alkfejl.model.Allat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AllatokController")
public class AllatokController extends HttpServlet {
    private AllatDAO dao = new AllatDAOImpl();

    public AllatokController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Allat> all = dao.nemOrokbefogadottAllat();
        List<Allat> all_no_name = dao.noNameNemOrokbefogadottAllat();
        req.setAttribute("allatList", all);
        req.setAttribute("noNameAllatList", all_no_name);
    }
}

