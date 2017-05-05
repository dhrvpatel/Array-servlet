import java.io.IOException;
import java.io.OutputStreamWriter;
 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Created by Dhruv Patel
 
@WebServlet("/DoubleMeServlet")
public class DoubleMeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DoubleMeServlet() {
        super();
 
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        response.getOutputStream().println("Hurray !! This Servlet Works");
       
        
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String passList="";
        try {
            int length = request.getContentLength();
            byte[] input = new byte[length];
            ServletInputStream sin = request.getInputStream();
            int c, count = 0 ;
            while ((c = sin.read(input, count, input.length-count)) != -1) {
                count +=c;
            }
            sin.close();
            
           
            
            String recievedString = new String(input);
            response.setStatus(HttpServletResponse.SC_OK);
            OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
            
 
            List<Integer> list = new ArrayList<Integer>();
            StringBuilder builder = new StringBuilder();
          	//list.add(55);
          	//list.add(21);
            //list.add(56);
           
            for (int i=0; i< 10 ; i++)
            {
                Random rand = new Random();
                //rand.setSeed(System.currentTimeMillis());
                Integer r = rand.nextInt(100) ;
                list.add(r);
                builder.append(list);
        	    builder.append(":");
        	}
                //System.out.println("list = " + list);
            
            
            
            
            //return arrayRandom;
          	list.add(Integer.parseInt(recievedString));
          	Collections.sort(list);
          	
           	passList = list.toString();
        	System.out.println(passList);
        	
          	int min = list.get(0);
          	int max = list.get(0);
          	
          	for(Integer i: list) {
          	    if(i < min) min = i;
          	    if(i > max) max = i;
          	}

          	//System.out.println("min = " + min);
          	//System.out.println("max = " + max);
            //Integer doubledValue = Integer.parseInt(recievedString) * 2;
          	
          	//Integer doubledValue = max;
          	
          	if(Integer.parseInt(recievedString) >= max){
          		
          	String output = "well done";
 
            writer.write(output);
            writer.flush();
            writer.close();
 
          	}
          	else{
          		String output = "Work hard";
          		writer.write(output);
                //writer.write(passList);
                writer.flush();
                writer.close();
          		
          	}
            
 
 
        } catch (IOException e) {
 
 
            try{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(e.getMessage());
                response.getWriter().close();
            } catch (IOException ioe) {
            }
        }   
        }
 
}