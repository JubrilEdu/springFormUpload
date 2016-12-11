/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerpackage;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jubril
 */
public class FileModel {
     MultipartFile multipartFile;
    String name;
    double Size;
    String fileExtension;
    
    public FileModel(){
    
    }
    
   public  FileModel (MultipartFile multipartFile){
       setMultipartFile(multipartFile);
       setFileExtension(multipartFile.getContentType());
       setName(getMultipartFile().getName());
       setSize(getMultipartFile().getSize());
   }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return Size;
    }

    public void setSize(double Size) {
        this.Size = Size;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
   
}
