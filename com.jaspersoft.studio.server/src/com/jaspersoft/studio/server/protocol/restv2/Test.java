package com.jaspersoft.studio.server.protocol.restv2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.jasperreports.eclipse.util.FileUtils;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceListWrapper;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ObjectMapper mapper = JacksonHelper.getJSONMapper();

			URL url = new URL("http://localhost:8080/jasperserver-pro/rest_v2");
			HttpHost host = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
			Executor exec = Executor.newInstance().auth(host, "jasperadmin", "jasperadmin");
			// exec.authPreemptive(host);

			Request get = Request.Get("http://localhost:8080/jasperserver-pro/rest_v2/serverInfo");
			setupGet(get);
			InputStream in = exec.execute(get).returnContent().asStream();
			mapper.readValue(in, ServerInfo.class);
			FileUtils.closeStream(in);

			get = Request.Get("http://localhost:8080/jasperserver-pro/rest_v2/resources?folderUri=%2F&recursive=false&sortBy=label&limit=2147483647");
			setupGet(get);

			in = exec.execute(get).returnContent().asStream();
			mapper.readValue(in, ClientResourceListWrapper.class);
			FileUtils.closeStream(in);

			get = Request.Get("http://localhost:8080/jasperserver-pro/rest_v2/resources?folderUri=%2F&recursive=false&sortBy=label&limit=2147483647");
			setupGet(get);

			in = exec.execute(get).returnContent().asStream();
			mapper.readValue(in, ClientResourceListWrapper.class);
			FileUtils.closeStream(in);

			System.out.println("done");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected static void setupGet(Request get) {
		get.connectTimeout(3000);
		get.removeHeaders("Transfer-Encoding");
		get.socketTimeout(3000);
		get.setHeader("Accept", "application/json");
	}

}
