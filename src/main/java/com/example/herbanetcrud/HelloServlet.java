package com.example.herbanetcrud;

import Dao.UserDao;
import model.User;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private static final long serialVersionUID=1L;
    private UserDao userDao;

    public void init() {

        message="Hello...";
        userDao=new UserDao();
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        doGet(request,response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        String action = request.getServletPath();
        switch (action){
            case "/new":
                showNewForm(request,response);
                break;
            case "/insert":
                insertUser(request,response);
                break;
            case "/delete":
                deleteUser(request,response);
                break;
            case "/edit":
                showEditForm(request,response);
                break;
            case "/update":
                updateUser(request,response);
                break;
            default:
                listUser(request,response);
                break;
        }
    }
    private void listUser(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        List <User> listuser= userDao.getAllUser();
        request.setAttribute("listUser",listuser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request,response);
    }
    private void showNewForm(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("user-form.jsp");
        requestDispatcher.forward(request,response);
    }
    private void showEditForm(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        int id=Integer.parseInt(request.getParameter("id"));
        User existinguser=userDao.getUser(id);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("User",existinguser);
        requestDispatcher.forward(request,response);
        //getting data

    }
    private void insertUser(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");
        User newuser=new User(name,email,country);
        userDao.saveUser(newuser);
        response.sendRedirect("list");
    }
    private void updateUser(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");
        User updateuser=new User(id,name,email,country);
        userDao.updateUser(updateuser);
        response.sendRedirect("list");
    }
    public void deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        int id=Integer.parseInt(request.getParameter("id"));
        userDao.deleteUser(id);
        response.sendRedirect("list");
    }


        public void destroy() {
    }

}