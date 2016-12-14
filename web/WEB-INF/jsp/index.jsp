<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html"; charset=UTF-8">
        <title>Welcome </title>
         <style>
            .headertext{
                align-self: center;
                margin-left: 500px;
                margin-right: 500px
            } 
            .chooseFile{
                height: 100px;
                width: 170px;
                margin-left: 400px;
                margin-right: 200px;
                align-self: flex-start;
            }
            .submitbuttin{
                height: 150px;
                width:80px; 
                align-content: center;
                align-self: flex-end;
            }
        </style> 
    </head>
    <body style="background-color:buttonface">
        <form method="POST" action="UploadHandler" enctype="multipart/form-data" >
            <p class="headertext">Select File to Upload</p>
            <div class="chooseFile">
        <input type="file" name ="file" >
        <input type="submit" value="upload" class="submitbuttin">
        </div>   
    </form> 
        </body>
</html>
