/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Action;

import Database.HandleDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//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
/**
 *
 * @author ahmedmedhat
 */
public class CheckLogin extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        String level = req.getParameter("level");
        String teamID = req.getParameter("teamID");
        String id = req.getParameter("id");
        HttpSession session = req.getSession(true);
        session.setAttribute("isAuth", "true");
        session.setAttribute("level", level);
        session.setAttribute("teamID", teamID);
        session.setAttribute("id", id);

        pw.print("true");
    }
}
