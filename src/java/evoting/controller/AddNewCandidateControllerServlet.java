/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dto.CandidateDTO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author dell	
 */
public class AddNewCandidateControllerServlet extends HttpServlet {

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
       RequestDispatcher rd=null;
       try
       {
           DiskFileItemFactory df=new DiskFileItemFactory();
           ServletFileUpload sfu=new ServletFileUpload(df);
           ServletRequestContext srq=new ServletRequestContext(request);
           List<FileItem> multiList=sfu.parseRequest(srq);
           ArrayList <String>objValues=new ArrayList<>();
           InputStream inp=null;
           for(FileItem fit: multiList)
           {
                if(fit.isFormField())
                {
                    String fname=fit.getFieldName();
                    String value=fit.getString();
                    System.out.println("Inside if");
                    System.out.println(fname+":"+value);
                    objValues.add(value);
                   
                }
                else
                {
                    inp=fit.getInputStream();
                    String key=fit.getFieldName();
                    String fileName=fit.getName();
                    System.out.println("Inside else");
                    System.out.println(key+":"+fileName);
                }
           }
           CandidateDTO candidate=new CandidateDTO(objValues.get(0),objValues.get(3),objValues.get(4),objValues.get(1),inp);
           boolean result=CandidateDAO.addCandidate(candidate);
           if(result)
               rd=request.getRequestDispatcher("success.jsp");
           else
               rd=request.getRequestDispatcher("failure.jsp");
       }
       catch(Exception ex)
       {
           System.out.println("Exception in AddNewCandidateController");
           ex.printStackTrace();
       }
       finally
       {
           if(rd!=null)
               rd.forward(request, response);
       }
        
    }
}

