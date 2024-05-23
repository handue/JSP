////package org.example.campusweb;
////
////import javax.servlet.RequestDispatcher;
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////
////
////@WebServlet("/CalcServlet")
////public class CalcServlet extends HttpServlet {
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////
////    }
////
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////
////        int num1 = 0;
////        int num2 = 0;
////        java.lang.String name = "";
//name//
////        request.setCharacterEncoding("utf-8");
////        num1 = Integer.parseInt(request.getParameter("num1"));
////        num2 = Integer.parseInt(request.getParameter("num2"));
////        name = request.getParameter("userName");
////
////        int result = num1 + num2;
////        request.setAttribute("result", result);
////        request.setAttribute("userName", name);
////
////        //1. Dispatcher
////        RequestDispatcher re =
////                request.getRequestDispatcher("/basic/result_dispatcher.jsp");
////        re.forward(request, response);
////
////
////    }
////}