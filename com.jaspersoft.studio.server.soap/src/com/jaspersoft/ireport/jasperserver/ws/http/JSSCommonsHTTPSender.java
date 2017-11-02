/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.ireport.jasperserver.ws.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;

import org.apache.axis.AxisFault;
import org.apache.axis.Constants;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.components.logger.LogFactory;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.soap.SOAP12Constants;
import org.apache.axis.soap.SOAPConstants;
import org.apache.axis.transport.http.CommonsHTTPSender;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.axis.utils.Messages;
import org.apache.axis.utils.NetworkUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.StatusLine;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;

import com.jaspersoft.studio.server.protocol.JSSTrustStrategy;
import com.jaspersoft.studio.server.protocol.restv2.CertChainValidator;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.HttpUtils;

public class JSSCommonsHTTPSender extends BasicHandler {
	private static final long serialVersionUID = 8881188152022966420L;

	/** Field log */
	protected static Log log = LogFactory.getLog(CommonsHTTPSender.class.getName());

	private Executor exec;

	private URL targetURL;

	public JSSCommonsHTTPSender() {
		super();
	}

	/**
	 * invoke creates a socket connection, sends the request SOAP message and then
	 * reads the response SOAP message back from the SOAP server
	 *
	 * @param msgContext
	 *            the messsage context
	 *
	 * @throws AxisFault
	 */
	public void invoke(final MessageContext msgContext) throws AxisFault {
		if (log.isDebugEnabled())
			log.debug(Messages.getMessage("enter00", "CommonsHTTPSender::invoke"));
		Request req = null;
		Response response = null;
		try {
			if (exec == null) {
				targetURL = new URL(msgContext.getStrProp(MessageContext.TRANS_URL));
				String userID = msgContext.getUsername();
				String passwd = msgContext.getPassword();
				// if UserID is not part of the context, but is in the URL, use
				// the one in the URL.
				if ((userID == null) && (targetURL.getUserInfo() != null)) {
					String info = targetURL.getUserInfo();
					int sep = info.indexOf(':');

					if ((sep >= 0) && (sep + 1 < info.length())) {
						userID = info.substring(0, sep);
						passwd = info.substring(sep + 1);
					} else
						userID = info;
				}
				Credentials cred = new UsernamePasswordCredentials(userID, passwd);
				if (userID != null) {
					// if the username is in the form "user\domain"
					// then use NTCredentials instead.
					int domainIndex = userID.indexOf("\\");
					if (domainIndex > 0) {
						String domain = userID.substring(0, domainIndex);
						if (userID.length() > domainIndex + 1) {
							String user = userID.substring(domainIndex + 1);
							cred = new NTCredentials(user, passwd, NetworkUtils.getLocalHostname(), domain);
						}
					}
				}

				SSLContextBuilder builder = SSLContexts.custom();

				final KeyStore trustStore = CertChainValidator.getDefaultTrustStore();

				builder.loadTrustMaterial(trustStore, new JSSTrustStrategy(trustStore));
				SSLContext sslContext = builder.build();

				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
						new BrowserCompatHostnameVerifier()) {
					@Override
					protected void prepareSocket(SSLSocket socket) throws IOException {
						super.prepareSocket(socket);
						socket.setEnabledProtocols(new String[] { "TLSv1", "TLSv1.1", "TLSv1.2" });
					}
				};
				HttpClient httpClient = HttpClientBuilder.create().setSSLSocketFactory(sslsf)
						.setRedirectStrategy(new LaxRedirectStrategy() {

							public HttpUriRequest getRedirect(final HttpRequest request, final HttpResponse response,
									final HttpContext context) throws ProtocolException {
								URI uri = getLocationURI(request, response, context);
								String method = request.getRequestLine().getMethod();
								if (method.equalsIgnoreCase(HttpHead.METHOD_NAME))
									return new HttpHead(uri);
								else if (method.equalsIgnoreCase(HttpPost.METHOD_NAME)) {
									HttpPost httpPost = new HttpPost(uri);
									httpPost.addHeader(request.getFirstHeader("Authorization"));
									httpPost.addHeader(request.getFirstHeader("SOAPAction"));
									httpPost.addHeader(request.getFirstHeader("Content-Type"));
									httpPost.addHeader(request.getFirstHeader("User-Agent"));
									httpPost.addHeader(request.getFirstHeader("SOAPAction"));
									if (request instanceof HttpEntityEnclosingRequest)
										httpPost.setEntity(((HttpEntityEnclosingRequest) request).getEntity());
									return httpPost;
								} else if (method.equalsIgnoreCase(HttpGet.METHOD_NAME)) {
									return new HttpGet(uri);
								} else {
									throw new IllegalStateException(
											"Redirect called on un-redirectable http method: " + method);
								}
							}
						}).build();

				exec = Executor.newInstance(httpClient);
				HttpHost host = new HttpHost(targetURL.getHost(), targetURL.getPort(), targetURL.getProtocol());
				exec.auth(host, cred);
				exec.authPreemptive(host);
				HttpUtils.setupProxy(exec, targetURL.toURI());
			}
			boolean posting = true;

			// If we're SOAP 1.2, allow the web method to be set from the
			// MessageContext.
			if (msgContext.getSOAPConstants() == SOAPConstants.SOAP12_CONSTANTS) {
				String webMethod = msgContext.getStrProp(SOAP12Constants.PROP_WEBMETHOD);
				if (webMethod != null)
					posting = webMethod.equals(HTTPConstants.HEADER_POST);
			}
			HttpHost proxy = HttpUtils.getUnauthProxy(exec, targetURL.toURI());
			if (posting) {
				req = Request.Post(targetURL.toString());
				if (proxy != null)
					req.viaProxy(proxy);
				Message reqMessage = msgContext.getRequestMessage();

				addContextInfo(req, msgContext, targetURL);
				Iterator<?> it = reqMessage.getAttachments();
				if (it.hasNext()) {
					ByteArrayOutputStream bos = null;
					try {
						bos = new ByteArrayOutputStream();
						reqMessage.writeTo(bos);
						req.body(new ByteArrayEntity(bos.toByteArray()));
					} finally {
						FileUtils.closeStream(bos);
					}
				} else
					req.body(new StringEntity(reqMessage.getSOAPPartAsString()));

			} else {
				req = Request.Get(targetURL.toString());
				if (proxy != null)
					req.viaProxy(proxy);
				addContextInfo(req, msgContext, targetURL);
			}
			response = exec.execute(req);
			response.handleResponse(new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response) throws IOException {
					HttpEntity en = response.getEntity();
					InputStream in = null;
					try {
						StatusLine statusLine = response.getStatusLine();
						int returnCode = statusLine.getStatusCode();
						String contentType = en.getContentType() != null ? en.getContentType().getValue() : null;

						in = new BufferedHttpEntity(en).getContent();
						// String str = IOUtils.toString(in);
						if (returnCode > 199 && returnCode < 300) {
							// SOAP return is OK - so fall through
						} else if (msgContext.getSOAPConstants() == SOAPConstants.SOAP12_CONSTANTS) {
							// For now, if we're SOAP 1.2, fall
							// through, since the range of
							// valid result codes is much greater
						} else if (contentType != null && !contentType.equals("text/html")
								&& ((returnCode > 499) && (returnCode < 600))) {
							// SOAP Fault should be in here - so
							// fall through
						} else {
							String statusMessage = statusLine.getReasonPhrase();
							AxisFault fault = new AxisFault("HTTP", "(" + returnCode + ")" + statusMessage, null, null);
							fault.setFaultDetailString(
									Messages.getMessage("return01", "" + returnCode, IOUtils.toString(in)));
							fault.addFaultDetail(Constants.QNAME_FAULTDETAIL_HTTPERRORCODE,
									Integer.toString(returnCode));
							throw fault;
						}
						Header contentEncoding = response.getFirstHeader(HTTPConstants.HEADER_CONTENT_ENCODING);
						if (contentEncoding != null) {
							if (contentEncoding.getValue().equalsIgnoreCase(HTTPConstants.COMPRESSION_GZIP))
								in = new GZIPInputStream(in);
							else {
								AxisFault fault = new AxisFault("HTTP",
										"unsupported content-encoding of '" + contentEncoding.getValue() + "' found",
										null, null);
								throw fault;
							}

						}

						// Transfer HTTP headers of HTTP message
						// to MIME headers of SOAP
						// message
						MimeHeaders mh = new MimeHeaders();
						for (Header h : response.getAllHeaders())
							mh.addHeader(h.getName(), h.getValue());
						Message outMsg = new Message(in, false, mh);

						outMsg.setMessageType(Message.RESPONSE);
						msgContext.setResponseMessage(outMsg);
						if (log.isDebugEnabled()) {
							log.debug("\n" + Messages.getMessage("xmlRecd00"));
							log.debug("-----------------------------------------------");
							log.debug(outMsg.getSOAPPartAsString());
						}
					} finally {
						FileUtils.closeStream(in);
					}
					return "";
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
			throw AxisFault.makeFault(e);
		}
		if (log.isDebugEnabled())
			log.debug(Messages.getMessage("exit00", "CommonsHTTPSender::invoke"));
	}

	/**
	 * Extracts info from message context.
	 *
	 * @param method
	 *            Post method
	 * @param httpClient
	 *            The client used for posting
	 * @param msgContext
	 *            the message context
	 * @param tmpURL
	 *            the url to post to.
	 *
	 * @throws Exception
	 */
	private void addContextInfo(Request req, MessageContext msgContext, URL tmpURL) throws Exception {
		String v = msgContext.getStrProp(MessageContext.HTTP_TRANSPORT_VERSION);
		if (v != null && v.equals(HTTPConstants.HEADER_PROTOCOL_V10))
			req.version(HttpVersion.HTTP_1_0);
		// optionally set a timeout for the request
		if (msgContext.getTimeout() != 0) {
			req.connectTimeout(msgContext.getTimeout());
			req.socketTimeout(msgContext.getTimeout());
		}

		// Get SOAPAction, default to ""
		String action = msgContext.useSOAPAction() ? msgContext.getSOAPActionURI() : "";

		if (action == null)
			action = "";

		Message msg = msgContext.getRequestMessage();
		if (msg != null)
			req.addHeader(HTTPConstants.HEADER_CONTENT_TYPE, msg.getContentType(msgContext.getSOAPConstants()));
		req.addHeader(HTTPConstants.HEADER_SOAP_ACTION, "\"" + action + "\"");
		req.addHeader(HTTPConstants.HEADER_USER_AGENT, HttpUtils.USER_AGENT_JASPERSOFT_STUDIO);

		// add compression headers if needed
		if (msgContext.isPropertyTrue(HTTPConstants.MC_ACCEPT_GZIP))
			req.addHeader(HTTPConstants.HEADER_ACCEPT_ENCODING, HTTPConstants.COMPRESSION_GZIP);
		if (msgContext.isPropertyTrue(HTTPConstants.MC_GZIP_REQUEST))
			req.addHeader(HTTPConstants.HEADER_CONTENT_ENCODING, HTTPConstants.COMPRESSION_GZIP);

		// Transfer MIME headers of SOAPMessage to HTTP headers.
		MimeHeaders mimeHeaders = msg.getMimeHeaders();
		if (mimeHeaders != null) {
			for (Iterator<?> i = mimeHeaders.getAllHeaders(); i.hasNext();) {
				MimeHeader mimeHeader = (MimeHeader) i.next();
				// HEADER_CONTENT_TYPE and HEADER_SOAP_ACTION are already set.
				// Let's not duplicate them.
				String headerName = mimeHeader.getName();
				if (headerName.equals(HTTPConstants.HEADER_CONTENT_TYPE)
						|| headerName.equals(HTTPConstants.HEADER_SOAP_ACTION))
					continue;
				req.addHeader(mimeHeader.getName(), mimeHeader.getValue());
			}
		}

		// process user defined headers for information.
		Hashtable<?, ?> userHeaderTable = (Hashtable<?, ?>) msgContext.getProperty(HTTPConstants.REQUEST_HEADERS);
		if (userHeaderTable != null)
			for (Map.Entry<?, ?> me : userHeaderTable.entrySet()) {
				Object keyObj = me.getKey();
				if (null == keyObj)
					continue;
				String key = keyObj.toString().trim();
				String value = me.getValue().toString().trim();

				if (key.equalsIgnoreCase(HTTPConstants.HEADER_EXPECT)
						&& value.equalsIgnoreCase(HTTPConstants.HEADER_EXPECT_100_Continue))
					req.useExpectContinue();
				else if (key.equalsIgnoreCase(HTTPConstants.HEADER_TRANSFER_ENCODING_CHUNKED)) {
					req.removeHeader(new BasicHeader("Transfer-Encoding", "chunked"));
					if (Boolean.parseBoolean(value))
						;// req.setHeader("Transfer-Encoding", "chunked");
				} else
					req.addHeader(key, value);
			}
	}
}
