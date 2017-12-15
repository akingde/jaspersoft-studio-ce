/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws Exception {
		// TODO code application logic here

		JServer server = new JServer();

		server.setUrl("http://build-master.jaspersoft.com:5980/jrs-pro-trunk/");
		server.setUsername("superuser");
		server.setPassword("superuser");

		WSClient client = new WSClient(server);

		runReportUnit(client, "/public/Samples/Reports/01._Geographic_Results_by_Segment_Report", "");
	}

	/**
	 * Run a report and save the output in PDF and HTML
	 * 
	 * @param client
	 * @param reportUri
	 * @param parameter1
	 * @throws Exception
	 */
	public static void runReportUnit(WSClient client, String reportUri, String parameter1) throws Exception {

		ResourceDescriptor rd = new ResourceDescriptor();
		rd.setUriString(reportUri);

		Map<String, Object> parameters = new HashMap<String, Object>();
		// parameters.put("parameter1", "A");

		List<Argument> arguments = new ArrayList<Argument>();
		arguments.add(new Argument(Argument.RUN_OUTPUT_FORMAT, Argument.RUN_OUTPUT_FORMAT_PDF));

		Map<String, FileContent> files = client.runReport(rd, parameters, arguments);

		FileContent fc = (FileContent) files.get("report");

		FileOutputStream pdfFile = new FileOutputStream("/tmp/myreport.pdf");
		pdfFile.write(fc.getData());
		pdfFile.close();

		System.out.println("PDF file saved to: c:\\myreport.pdf");

		arguments.clear();
		arguments.add(new Argument(Argument.RUN_OUTPUT_FORMAT, Argument.RUN_OUTPUT_FORMAT_HTML));

		files = client.runReport(rd, parameters, arguments);

		Iterator<String> iter = files.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			fc = (FileContent) files.get(key);

			if (key.equals("report")) {
				FileOutputStream htmlFile = new FileOutputStream("/tmp/myreport.html");
				htmlFile.write(fc.getData());
				htmlFile.close();
			} else {
				File f = new File("/tmp/images");
				if (!f.exists())
					f.mkdirs();

				FileOutputStream imageFile = new FileOutputStream("/tmp/images/" + key);
				imageFile.write(fc.getData());
				imageFile.close();
			}
		}

		System.out.println("Html file saved to: c:\\myreport.html");

	}

}
