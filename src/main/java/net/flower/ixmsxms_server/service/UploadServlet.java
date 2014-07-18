package net.flower.ixmsxms_server.service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.tools.jstat.Literal;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by hyeyoungkang on 2014. 7. 16..
 */
@Service
public class UploadServlet extends HttpServlet{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @SuppressWarnings("rawtypes")
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        Map<String, Object> map = new HashMap<String, Object>();

        //디스크상의 프로젝트 실제 경로얻기
        String contextRootPath = this.getServletContext().getRealPath("/");


        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (!isMultipart) {
            System.out.println("You are not trying to upload<br/>");
            return;
        }

        if (isMultipart) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1 * 1024 * 1024);
            factory.setRepository(new File(contextRootPath + "/WEB-INF/temp")); //임시저장폴더


//            factory.setRepository();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(10 * 1024 * 1024);
            try {
                // Parse the request

                @SuppressWarnings("unchecked")
                List<FileItem> items = ( List<FileItem>)upload.parseRequest(request);
                System.out.println("Number of fields: " + items.size() + "<br/><br/>");
                Iterator<FileItem> iterator = items.iterator();
//
//                FileItemIterator iterator = upload.getItemIterator(request);
                out.println("Number of fields: " + iterator + "<br/><br/>");
System.out.println("violelt");
System.out.println(iterator.hasNext());
                while (iterator.hasNext() ) {
                    System.out.println( "iterator----------------" );
                    FileItem item = (FileItem)iterator;
                    if (!item.isFormField())
                    {
                        String fileName = item.getName();

                        File path = new File(contextRootPath + "/uploads");
                        if (!path.exists())
                        {
                            boolean status = path.mkdirs();
                        }
                        File uploadedFile = new File(path + "/" + fileName);
                        System.out.println( "violet absolutepath" );
                        System.out.println(uploadedFile.getAbsolutePath());
                        map.put("files", uploadedFile.getAbsolutePath());

                        if(fileName!="")
                            item.write(uploadedFile);
                        else
                            out.println("file not found");
                        out.println("<h1>File Uploaded Successfully....:-)</h1>");
                    }
                    else
                    {
                        String abc = item.getString();
                        out.println("<br><br><h1>"+abc+"</h1><br><br>");
                    }
                }
            } catch (FileUploadException e) {
                out.println(e);

            } catch (Exception e) {

                out.println(e);
            }
        }
        else
        {
            out.println("Not Multipart");
        }
    }
}


