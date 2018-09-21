/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ValidaSesionesServlet extends HttpServlet {
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

      response.setContentType("text/html");
      HttpSession sesion = request.getSession();
      String titulo = null;
      conexion con = null;
      //Pedimos el atributo, y verificamos si existe
      String claveSesion = (String) sesion.getAttribute("claveSesion");
      String claveSesion2 = (String) sesion.getAttribute("claveSesion2");
       
        try {
            //Conexion a la base de datos, utiliza la clase ConectaDB
            con = new conexion();
            con.conecta();
            ResultSet res = con.query("select * from usuario where nombre='" + claveSesion + "' and apellido='" + claveSesion2 + "';");

            if (res.next()) {
                titulo = "llave correcta continua la sesion";
            }else{
                titulo = "llave incorrecta inicie nuevamente sesion";
            }

            con.cierra();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ValidaSesionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }


        //Mostramos los  valores en el cliente
        PrintWriter out = response.getWriter();
        out.println("¿Continua la Sesion y es valida?: " + titulo);
        out.println("<br>");
        out.println("ID de la sesi&oacute;n JSESSIONID: " + sesion.getId());


    }

}
