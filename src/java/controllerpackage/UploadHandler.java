/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerpackage;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jubril
 */
@Controller
@WebServlet(name = "UploadHandler")
@MultipartConfig
@RequestMapping(value = "filecontent")
public class UploadHandler extends HttpServlet {
    private final String DB_USERNAME="root";
    private final String DB_NAME = "csvdb";
    private final String DB_URL = "jdbc:mysql://localhost:3306/";
    private final String DB_PASSWORD ="";
    private final String DB_DRIVER="com.mysql.jdbc.Driver";

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
        try {
              List csvRecords = readFileUsingApacheLib(request);
              setUpConnection(csvRecords);
            } catch (FileUploadException ex) {
                   Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
                   
            }
//        leavePage();
getServletContext().getRequestDispatcher("/filecontent")
    .forward(request, response);                 
        
    }

    private List readFileUsingApacheLib(HttpServletRequest request) throws FileUploadException, IOException {
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader();
        ServletFileUpload upload = new ServletFileUpload();
        FileItemIterator iterator = upload.getItemIterator(request);
        FileItemStream item = iterator.next();
        Reader reader = new InputStreamReader(item.openStream());
        CSVParser csvFileParser = new CSVParser(reader, csvFileFormat);
        List csvRecords = csvFileParser.getRecords();
        return csvRecords;
    }
    
    
    public ModelAndView leavePage(){
         return new ModelAndView("/filecontent");
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

    
    private String readFileUsingIOUtils(HttpServletRequest request) throws IOException, FileUploadException {
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

    private Connection setUpConnection(List records){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = (Connection) DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            if (connection!=null) {
                createDbAndTables(connection);
//                loadDataIntoDb(connection, records);
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  connection;
    }
    
    private void createDbAndTables(Connection connection){
        try {
            Statement createDbAndTableStatement = (Statement) connection.createStatement();
            createDbAndTableStatement.addBatch("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            createDbAndTableStatement.addBatch("USE "+ DB_NAME);
            createDbAndTableStatement.addBatch("CREATE TABLE IF NOT EXISTS "+DB_NAME +
                                       " ( ID INT(64) NOT NULL AUTO_INCREMENT,"+
                                       " DATE STRING(20) NOT NULL," +
                                       " OPEN DOUBLE NOT NULL," +
                                       " HIGH DOUBLE NOT NULL," +
                                       " LOW  DOUBLE NOT NULL," +
                                       " CLOSE DOUBLE NOT NULL," +
                                       " VOLUME INT NOT NULL," +
                                       " ADJCLOSE DOUBLE NOT NULL,"+
                                       " PRIMARY KEY (ID))");
            createDbAndTableStatement.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadDataIntoDb(Connection connection,List records){
        try {
            Statement statement = (Statement) connection.createStatement();
            for (Iterator it = records.iterator(); it.hasNext();) {
                CSVRecord cSVRecord = (CSVRecord) it.next();
                cSVRecord.get("Date");
                String insertQuery = "INSERT INTO "+DB_NAME+" VALUES (0,"+cSVRecord.get("Date")
                        +","+cSVRecord.get("Open")+","+cSVRecord.get("High")+","+cSVRecord.get("Low")
                        +","+cSVRecord.get("Close")+","+cSVRecord.get("Volume")+","+cSVRecord.get("Adj Close")+")";
                statement.execute(insertQuery);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
