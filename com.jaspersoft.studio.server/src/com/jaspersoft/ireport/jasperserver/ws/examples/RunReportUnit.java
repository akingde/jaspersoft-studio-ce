/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jaspersoft.ireport.jasperserver.ws.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jaspersoft.ireport.jasperserver.ws.FileContent;
import com.jaspersoft.ireport.jasperserver.ws.JServer;
import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;

/**
 *
 * @author gtoffoli
 */
public class RunReportUnit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        JServer server = new JServer();

        server.setUrl("http://127.0.0.1:8080/jasperserver-pro/services/repository");
        server.setUsername("jasperadmin|organization_1");
        server.setPassword("jasperadmin");

        WSClient client = new WSClient(server);

        runReportUnit(client, "/MY_FOLDER/MY_REPORT", "A");
    }

    /**
     * Run a report and save the output in PDF and HTML
     *
     * @param client
     * @param reportUri
     * @param parameter1
     * @throws Exception
     */
    public static void runReportUnit(WSClient client, String reportUri,  String parameter1) throws Exception
    {

        ResourceDescriptor rd = new ResourceDescriptor();
        rd.setUriString(reportUri);


        Map parameters = new HashMap();
        parameters.put("parameter1", "A");

        List arguments = new ArrayList();
        arguments.add( new Argument( Argument.RUN_OUTPUT_FORMAT , Argument.RUN_OUTPUT_FORMAT_PDF ));

        Map files = client.runReport(rd, parameters, arguments);

        FileContent fc = (FileContent)files.get("report");

        FileOutputStream pdfFile = new FileOutputStream( "c:\\myreport.pdf" );
        pdfFile.write( fc.getData() );
        pdfFile.close();

        System.out.println("PDF file saved to: c:\\myreport.pdf");
        
        arguments.clear();
        arguments.add( new Argument( Argument.RUN_OUTPUT_FORMAT , Argument.RUN_OUTPUT_FORMAT_HTML));

        files = client.runReport(rd, parameters, arguments);

        Iterator iter = files.keySet().iterator();
        while (iter.hasNext())
        {
            String key = (String) iter.next();
            fc = (FileContent)files.get(key);

            if (key.equals("report"))
            {
                FileOutputStream htmlFile = new FileOutputStream( "c:\\myreport.html" );
                htmlFile.write( fc.getData() );
                htmlFile.close();
            }
            else
            {
                File f = new File("c:\\images");
                if (!f.exists()) f.mkdirs();

                FileOutputStream imageFile = new FileOutputStream( "c:\\images\\" + key );
                imageFile.write( fc.getData() );
                imageFile.close();
            }

        }


        System.out.println("Html file saved to: c:\\myreport.html");

       
    }


}
