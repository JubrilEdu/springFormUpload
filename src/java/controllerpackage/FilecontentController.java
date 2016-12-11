/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerpackage;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jubril
 */
@Controller
public class FilecontentController {
     
    @RequestMapping(value = "/filecontent",method = RequestMethod.GET)
    public ModelAndView onSubmitFile(HttpServletRequest request, 
            @RequestPart("file") MultipartFile file){
             Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null,request.getContentType());
        String rootPath = request.getSession().getServletContext().getContextPath();
        File dir = new File(rootPath + File.separator);
        if (!dir.exists()) {
            dir.mkdirs();
        }
         
         File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
         System.out.println(file.getOriginalFilename());
    //write uploaded image to disk
        try {
            try (InputStream is = file.getInputStream(); BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                int i;
                while ((i = is.read()) != -1) {
                    stream.write(i);
                }
                stream.flush();
            }
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }
        
    return new ModelAndView("/filecontent");
}
    
}
