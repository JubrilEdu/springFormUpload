<%-- 
    Document   : filecontent
    Created on : Dec 5, 2016, 12:28:40 PM
    Author     : Jubril
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .leftdiv{
                margin: 80px;
                padding: 20px;
                align-content: center;
                align-self: center;
                float: left;
                width: 280px;
            }
            .radioButton{
                padding: 10px;
                align-self: center;
                height: 24px;
                width: 24px
            }
            .centerdiv{
                align-self: center;
                align-content: center;
                float: left;
                padding: 30px;
                margin: 50px;
                height: 45px;
                width: 150px;
            }
            .rightdiv{
                margin: 60px;
                padding: 20px;
                align-content: center;
                align-self: center;
                float: left;
                width: 280px;
                
            }
            .radioLabel{
                height: 130px;
                font-family: serif;
            }
            .rightdivRow{
                margin: 20px;
                padding: 15px;
                height: 30px;
                font: bold;
            }
            .buttonstyle{
                 height: 45px;
                 width: 150px;
                 background-color: darkcyan;
                 text-height: 35px;
            }
        </style>
    </head>
    <body style="background-color:buttonface">
        <div class="leftdiv">
        <form action="" >
           
         <p align="left" style="font-weight: bolder; text-height: 30px;">Select Data to work on</p>
            <table border="0">
                
            <tr>
                <td><input type="radio" class="radioButton" name="rbutton" value="Open" /></td> <td><span class="radioLabel">Open</span></td>
            </tr><tr></tr>
            <tr>
            <td><input type="radio" class="radioButton" name="rbutton" value="High" /></td> <td>High</td>
            </tr><tr></tr>
            <tr>
            <td><input type="radio" class="radioButton" name="rbutton" value="Low" /></td> <td>Low</td>
            </tr><tr></tr>
            <tr>
            <td><input type="radio"  class="radioButton" name="rbutton" value="Close" /></td><td>Close</td>
            </tr><tr></tr>
            <tr>
             <td><input type="radio"  class="radioButton" name="rbutton" value="Volume"/></td><td>Volume</td>    
            </tr><tr></tr>
            <tr>
                <td><input type="radio"  class="radioButton" name="rbutton" value="Adj Close"/></td><td>Adj Close</td>   
            </tr><tr></tr>
            </table>
        </form>
            </div>
        <div class="centerdiv">
            <button class="buttonstyle">OPERATE</button>
        </div>
        <div class="rightdiv">
            <table>
                <tr>
                    <td><p class="rightdivRow">MODE :</p></td>
                </tr><tr></tr> 
                <tr>
                    <td><p class="rightdivRow"> MEAN :</p></td>
                </tr><tr></tr> 
                <tr>
                    <td><p class="rightdivRow">MEDIAN :</p></td>
                </tr><tr></tr>
            </table>
        </div>
    </body>
</html>
