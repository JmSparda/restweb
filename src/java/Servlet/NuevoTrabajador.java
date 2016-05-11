/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ControladorTrabajador;
import include.Trabajador;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

/**
 *
 * @author JMLOPEZ
 */
public class NuevoTrabajador extends HttpServlet {

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
        
        FileItemFactory file_factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(file_factory);
        
        ArrayList<String> campos = new ArrayList<>();
        ArrayList<String> imgs = new ArrayList<>();
        
        try {
            List items = sfu.parseRequest(request);
            for (int i = 0; i < items.size(); i++) {
                FileItem item = (FileItem) items.get(i);
                if(!item.isFormField()){
                    File archivo = new File("D:\\Proyectos Netbeans\\restWeb\\restWeb\\web\\img\\trabajadores\\"+item.getName());
                    item.write(archivo);
                    imgs.add("img/trabajadores/"+item.getName());
                }else{
                    campos.add(item.getString());
                }
            }
        } catch (Exception e) {    
        }
        
        Trabajador t = new Trabajador(Integer.parseInt(campos.get(0)), Integer.parseInt(campos.get(12)), Integer.parseInt(campos.get(11)), campos.get(13), Integer.parseInt(campos.get(0)), campos.get(3), campos.get(1), campos.get(2), campos.get(4), campos.get(5), Integer.parseInt(campos.get(6)), Integer.parseInt(campos.get(7)), campos.get(8), campos.get(9), campos.get(10), imgs.get(0));
        ControladorTrabajador ct = new ControladorTrabajador();
        if(ct.crearTrabajador(t)){
            response.getWriter().println("Producto creado Exitosamente");
        }else{
            response.getWriter().println("ERROR AL CREAR PRODUCTO");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
