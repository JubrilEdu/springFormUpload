/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerpackage;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jubril
 */
@Controller
public class FilecontentController {
     @RequestMapping(value = "filecontent",method = RequestMethod.POST)
    public ModelAndView onSubmitFile(HttpServletRequest request
            ) throws IOException{
             Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null,request.getContentType());
      
    return new ModelAndView("/filecontent");
}
    
    
}
