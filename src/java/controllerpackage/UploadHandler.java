/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Jubril
 */
@WebServlet(name = "UploadHandler", urlPatterns = {"/UploadHandler"})
@MultipartConfig
public class UploadHandler extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadHandler at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null,response);
     Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
     Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null,filePart.getName());
     String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
//     InputStream fileContent = filePart.getInputStream();
 Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null,fileName);

        try {
            System.out.println("header");
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null,request.getHeader("file"));
            String fileContent = null;
            ServletFileUpload upload = new ServletFileUpload();
            FileItemIterator iterator = upload.getItemIterator(request);
                while (iterator.hasNext()) {
                    System.out.println("and here");
                    FileItemStream item = iterator.next();
                    InputStream in = item.openStream();
                  fileContent = IOUtils.toString(in, "UTF-8");
                  IOUtils.closeQuietly(in);
                    PrintWriter writer = response.getWriter();
                    writer.println(fileContent);
                    writer.flush();
                    System.out.println(fileContent);
              
//                
                IOUtils.closeQuietly(in);
break;
                }   } catch (FileUploadException ex) {
                    Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    
    private String readFile(HttpServletRequest request) throws IOException, FileUploadException {
    String fileContent = null;
    ServletFileUpload upload = new ServletFileUpload();
    FileItemIterator iterator = upload.getItemIterator(request);

    while (iterator.hasNext()) {
      FileItemStream item = iterator.next();

      if (!item.isFormField()) {
        InputStream in = item.openStream();
        fileContent = IOUtils.toString(in, "UTF-8");
        IOUtils.closeQuietly(in);
        break;
      }
    }

    return fileContent;
  }
}
