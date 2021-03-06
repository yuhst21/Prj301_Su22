/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DateTimeHandle;
import dal.LectureDBContext;
import dal.SessionDBContext;
import dal.SlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Account;
import model.Lecture;
import model.Session;
import model.Week;

/**
 *
 * @author win
 */
public class ScheduleController extends BaseRequiredAuthenticationController {

    SessionDBContext sessionDB = new SessionDBContext();
    SlotDBContext slotDB = new SlotDBContext();
    DateTimeHandle dateTime = new DateTimeHandle();
    LectureDBContext lectureDB = new LectureDBContext();
    
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
     
        ArrayList<Week> weeks = dateTime.getWeeksOfYear();
        LocalDate currentDate = LocalDate.now();
        Week currentWeek = dateTime.getWeekByDate(weeks, currentDate);
        //get session
        Account user = (Account) request.getSession().getAttribute("account");
        sessionDB = new SessionDBContext();
        Lecture lec = lectureDB.getLectureAccount(user);
        ArrayList<Session> 
         sessions = sessionDB.listSessionByLecture(lec, currentWeek.getStartDate(), currentWeek.getEndDate());
        request.getSession().setAttribute("weeks", weeks);
        //set attributes
        request.getSession().setAttribute("acc", user);
        request.getSession().setAttribute("lecturer", lec.getLid());
        request.setAttribute("slots", slotDB.list());
        request.setAttribute("date", currentDate);
        request.setAttribute("sessions", sessions);
        request.setAttribute("week", currentWeek);
        request.getRequestDispatcher("View/Lecture/schedule.jsp").forward(request, response);
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
         ArrayList<Week> weeks = (ArrayList<Week>)request.getSession().getAttribute("weeks");
        int index = Integer.parseInt(request.getParameter("week_index"));
        Week w = weeks.get(index);
        LocalDate currentDate = w.getStartDate();
        Week currentWeek = dateTime.getWeekByDate(weeks, currentDate);
        //get session
         Account user = (Account) request.getSession().getAttribute("account");
       
        Lecture lec = lectureDB.getLectureAccount(user);
        ArrayList<Session> sessions = sessionDB.listSessionByLecture(lec, currentWeek.getStartDate(), currentWeek.getEndDate());
        //set attributes
        request.setAttribute("sessions", sessions);
        request.setAttribute("slots", slotDB.list());
        request.setAttribute("week", currentWeek);
        request.setAttribute("date", currentDate);
        request.getRequestDispatcher("View/Lecture/schedule.jsp").forward(request, response);
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
