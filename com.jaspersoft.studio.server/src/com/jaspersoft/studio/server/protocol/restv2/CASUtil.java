package com.jaspersoft.studio.server.protocol.restv2;

import com.jaspersoft.studio.server.model.server.ServerProfile;

public class CASUtil {
	public static String getToken(ServerProfile sp) {
		String user = null;
		String errorCode = null;
		String errorMessage = null;
		String xmlResponse = null;

		/* instantiate a new ServiceTicketValidator */
		ServiceTicketValidator sv = new ServiceTicketValidator();

		/* set its parameters */
		sv.setCasValidateUrl("https://secure.its.yale.edu/cas/serviceValidate");
		sv.setService(urlOfThisService);
		sv.setServiceTicket(request.getParameter("ticket"));

		/*
		 * If we want to be able to acquire proxy tickets (requires callback servlet
		 * to be set up in web.xml - see below)
		 */

		String urlOfProxyCallbackServlet = "https://portal.yale.edu/CasProxyServlet";

		sv.setProxyCallbackUrl(urlOfProxyCallbackServlet);

		/* contact CAS and validate */
		sv.validate();

		/* if we want to look at the raw response, we can use getResponse() */
		xmlResponse = sv.getResponse();

		/* read the response */

		// Yes, this method is misspelled in this way
		// in the ServiceTicketValidator implementation.
		// Sorry.
		if (sv.isAuthenticationSuccesful()) {
			user = sv.getUser();
		} else {
			errorCode = sv.getErrorCode();
			errorMessage = sv.getErrorMessage();
			/* handle the error */
		}

		/* The user is now authenticated. */

		/* If we did set the proxy callback url, we can get proxy tickets with: */

		String urlOfTargetService = "http://hkg2.its.yale.edu/someApp/portalFeed";

		String proxyTicket = ProxyTicketReceptor.getProxyTicket(sv.getPgtIou(), urlOfTargetService);
		return proxyTicket;
	}
}
