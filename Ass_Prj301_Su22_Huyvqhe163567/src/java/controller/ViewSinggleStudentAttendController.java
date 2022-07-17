/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AttendDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Attendance;
import model.Group;
import model.Session;
import model.Student;

/**
 *
 * @author win
 */
public class ViewSinggleStudentAttendController extends BaseRequiredAuthenticationController {

    StudentDBContext dbStudent = new StudentDBContext();
    AttendDBContext dbAttend = new AttendDBContext();
    SessionDBContext dbSession = new SessionDBContext();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewSinggleStudentAttendController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewSinggleStudentAttendController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int gid = (int) request.getSession().getAttribute("gid");              
        Group group = new Group();
        group.setGid(gid);
        int sid = Integer.parseInt(request.getParameter("sid"));
        Student students1 = new Student();
        students1.setSid(sid);
        ArrayList<Student> students = dbStudent.get(group, students1);
        ArrayList<Attendance> attends = dbAttend.listOneAttend(students1, group);
        for (Student student : students) {
            student.getAttendance().clear();
            for (Attendance attend : attends) {
                if (group.getGid() == attend.getSession().getGroup().getGid()
                        && student.getSid() == attend.getStudent().getSid()) {
                    student.getAttendance().add(attend);
                }
            }
        }
        request.setAttribute("attendance", attends);
        request.setAttribute("students", students);
        request.setAttribute("gid", group.getGid());
        request.getRequestDispatcher("/View/Attendance/viewsinglestudentattend.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
