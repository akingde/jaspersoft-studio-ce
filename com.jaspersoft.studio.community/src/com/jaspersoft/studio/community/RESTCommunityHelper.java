/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.community;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.community.issues.IssueField;
import com.jaspersoft.studio.community.messages.Messages;
import com.jaspersoft.studio.community.requests.FileUploadRequest;
import com.jaspersoft.studio.community.requests.IssueRequest;
import com.jaspersoft.studio.community.utils.CommunityAPIException;

/**
 * Helper class to manager REST operations towards the community site. 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
@SuppressWarnings("restriction")
public final class RESTCommunityHelper {
	
	private RESTCommunityHelper(){
		// prevent instantiation
	}
	
	/**
	 * Executes the authentication to the Jaspersoft community in order to
	 * retrieve the session cookie to use later for all other operations.
	 * 
	 * @param httpclient
	 *            the http client
	 * 
	 * @param cookieStore
	 *            the Cookie Store instance
	 * @param username
	 *            the community user name (or email)
	 * @param password
	 *            the community user password
	 * @return the authentication cookie if able to retrieve it,
	 *         <code>null</code> otherwise
	 * @throws CommunityAPIException
	 */
	public static Cookie getAuthenticationCookie(
			CloseableHttpClient httpclient, CookieStore cookieStore, String username, String password) throws CommunityAPIException{

		try {
			HttpPost loginPOST = new HttpPost(CommunityConstants.LOGIN_URL);
			EntityBuilder loginEntity = EntityBuilder.create();
			loginEntity.setText("{ \"username\": \""+username+"\", \"password\":\""+password+"\" }");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			loginEntity.setContentType(ContentType.create(CommunityConstants.JSON_CONTENT_TYPE));
			loginEntity.setContentEncoding(CommunityConstants.REQUEST_CHARSET);
			loginPOST.setEntity(loginEntity.build());	

			CloseableHttpResponse resp = httpclient.execute(loginPOST);
			int httpRetCode = resp.getStatusLine().getStatusCode();
			String responseBodyAsString = EntityUtils.toString(resp.getEntity());
			if(HttpStatus.SC_OK == httpRetCode) {
				// Can proceed
				List<Cookie> cookies = cookieStore.getCookies();
				Cookie authCookie = null;
				for(Cookie cookie : cookies){
					if(cookie.getName().startsWith("SESS")){ //$NON-NLS-1$
						authCookie = cookie;
						break;
					}
				}
				return authCookie;
			}
			else if(HttpStatus.SC_UNAUTHORIZED == httpRetCode){
				// Unauthorized... wrong username or password
				CommunityAPIException unauthorizedEx = new CommunityAPIException(Messages.RESTCommunityHelper_WrongUsernamePasswordError);
				unauthorizedEx.setHttpStatusCode(httpRetCode);
				unauthorizedEx.setResponseBodyAsString(responseBodyAsString);
				throw unauthorizedEx;
			}
			else {
				// Some other problem occurred
				CommunityAPIException generalEx = new CommunityAPIException(Messages.RESTCommunityHelper_AuthInfoProblemsError);
				generalEx.setHttpStatusCode(httpRetCode);
				generalEx.setResponseBodyAsString(responseBodyAsString);
				throw generalEx;				
			}
		} catch (UnsupportedEncodingException e) {
			JSSCommunityActivator.getDefault().logError(
					Messages.RESTCommunityHelper_EncodingNotValidError, e);
			throw new CommunityAPIException(Messages.RESTCommunityHelper_AuthenticationError, e);
		} catch (IOException e) {
			JSSCommunityActivator.getDefault().logError(
					Messages.RESTCommunityHelper_PostMethodIOError,e);
			throw new CommunityAPIException(Messages.RESTCommunityHelper_AuthenticationError, e);
		}
	}
	
	
	/**
	 * Uploads the specified file to the community site. The return identifier
	 * can be used later when composing other requests.
	 * 
	 * @param httpclient
	 *            the http client
	 * @param attachment
	 *            the file to attach
	 * @param authCookie
	 *            the session cookie to use for authentication purpose
	 * @return the identifier of the file uploaded, <code>null</code> otherwise
	 * @throws CommunityAPIException
	 */
	public static String uploadFile(CloseableHttpClient httpclient, File attachment, Cookie authCookie) throws CommunityAPIException{
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(attachment);
			byte fileContent[] = new byte[(int)attachment.length()];
			fin.read(fileContent);
			
			byte[] encodedFileContent = Base64.encodeBase64(fileContent);
			FileUploadRequest uploadReq = new FileUploadRequest(attachment.getName(),encodedFileContent);
			
			HttpPost fileuploadPOST = new HttpPost(CommunityConstants.FILE_UPLOAD_URL);
			EntityBuilder fileUploadEntity = EntityBuilder.create();
			fileUploadEntity.setText(uploadReq.getAsJSON());
			fileUploadEntity.setContentType(ContentType.create(CommunityConstants.JSON_CONTENT_TYPE));
			fileUploadEntity.setContentEncoding(CommunityConstants.REQUEST_CHARSET);
			fileuploadPOST.setEntity(fileUploadEntity.build());
			
			CloseableHttpResponse resp = httpclient.execute(fileuploadPOST);
			int httpRetCode = resp.getStatusLine().getStatusCode();
			String responseBodyAsString = EntityUtils.toString(resp.getEntity());

			if(HttpStatus.SC_OK == httpRetCode){
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
				mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
				JsonNode jsonRoot = mapper.readTree(responseBodyAsString);
				String fid = jsonRoot.get("fid").asText(); //$NON-NLS-1$
				return fid;
			}
			else {
				CommunityAPIException ex = new CommunityAPIException(Messages.RESTCommunityHelper_FileUploadError);
				ex.setHttpStatusCode(httpRetCode);
				ex.setResponseBodyAsString(responseBodyAsString);
				throw ex;
			}
			
		} catch (FileNotFoundException e) {
			JSSCommunityActivator.getDefault().logError(
					Messages.RESTCommunityHelper_FileNotFoundError, e);
			throw new CommunityAPIException(Messages.RESTCommunityHelper_FileUploadError,e);
		} catch (UnsupportedEncodingException e) {
			JSSCommunityActivator.getDefault().logError(
					Messages.RESTCommunityHelper_EncodingNotValidError, e);
			throw new CommunityAPIException(Messages.RESTCommunityHelper_FileUploadError,e);
		} catch (IOException e) {
			JSSCommunityActivator.getDefault().logError(
					Messages.RESTCommunityHelper_PostMethodIOError,e);
			throw new CommunityAPIException(Messages.RESTCommunityHelper_FileUploadError,e);
		} finally {
			IOUtils.closeQuietly(fin);
		}
	}
	
	/**
	 * Creates a new issue in the community tracker.
	 * 
	 * @param httpclient
	 *            the http client
	 * @param newIssue
	 *            the new issue to create on the community tracker
	 * @param attachmentsIds
	 *            the list of file identifiers that will be attached to the
	 *            final issue
	 * @param authCookie
	 *            the session cookie to use for authentication purpose
	 * @return the tracker URL of the newly created issue
	 * @throws CommunityAPIException
	 */
	public static String createNewIssue(
			CloseableHttpClient httpclient, IssueRequest newIssue, List<String> attachmentsIds, Cookie authCookie) throws CommunityAPIException{
		try {
			// Add attachments if any
			if (!attachmentsIds.isEmpty()){
				IssueField attachmentsField = new IssueField(){
					@Override
					protected String getValueAttributeName() {
						return "fid"; //$NON-NLS-1$
					}

					@Override
					public boolean isArray() {
						return true;
					}
				};
				attachmentsField.setName("field_bug_attachments"); //$NON-NLS-1$
				attachmentsField.setValues(attachmentsIds);
				newIssue.setAttachments(attachmentsField);
			}
			
			HttpPost issueCreationPOST = new HttpPost(CommunityConstants.ISSUE_CREATION_URL);
			EntityBuilder newIssueEntity = EntityBuilder.create();
			newIssueEntity.setText(newIssue.getAsJSON());
			newIssueEntity.setContentType(ContentType.create(CommunityConstants.JSON_CONTENT_TYPE));
			newIssueEntity.setContentEncoding(CommunityConstants.REQUEST_CHARSET);
			issueCreationPOST.setEntity(newIssueEntity.build());
		    HttpResponse httpResponse = httpclient.execute(issueCreationPOST);
			int httpRetCode = httpResponse.getStatusLine().getStatusCode();
			String responseBodyAsString = EntityUtils.toString(httpResponse.getEntity());
			
			if(HttpStatus.SC_OK != httpRetCode){
				CommunityAPIException ex = new CommunityAPIException(Messages.RESTCommunityHelper_IssueCreationError);
				ex.setHttpStatusCode(httpRetCode);
				ex.setResponseBodyAsString(responseBodyAsString);
				throw ex;
			}
			else {
				// extract the node ID information in order
				// to retrieve the issue URL available on the tracker
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
				mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
				JsonNode jsonRoot = mapper.readTree(responseBodyAsString);
				String nodeID = jsonRoot.get("nid").asText(); //$NON-NLS-1$
				JsonNode jsonNodeContent = retrieveNodeContentAsJSON(httpclient, nodeID, authCookie);
				return jsonNodeContent.get("path").asText(); //$NON-NLS-1$
			}
						
		} catch (UnsupportedEncodingException e) {
			JSSCommunityActivator.getDefault().logError(
					Messages.RESTCommunityHelper_EncodingNotValidError, e);
			throw new CommunityAPIException(Messages.RESTCommunityHelper_IssueCreationError,e);
		} catch (IOException e) {
			JSSCommunityActivator.getDefault().logError(
					Messages.RESTCommunityHelper_PostMethodIOError,e);
			throw new CommunityAPIException(Messages.RESTCommunityHelper_IssueCreationError,e);
		}
	}
	
	/**
	 * Tries to retrieve the content for the specified node ID.
	 * 
	 * @param httpclient
	 *            the http client
	 * @param nodeID
	 *            the node ID
	 * @param authCookie
	 *            the session cookie to use for authentication purpose
	 * @return the node content as JSON
	 * @throws CommunityAPIException
	 */
	public static JsonNode retrieveNodeContentAsJSON(CloseableHttpClient httpclient, String nodeID,Cookie authCookie) throws CommunityAPIException{
		try {
			HttpGet retrieveNodeContentGET = new HttpGet(CommunityConstants.NODE_CONTENT_URL_PREFIX + nodeID + ".json"); //$NON-NLS-1$
			CloseableHttpResponse resp = httpclient.execute(retrieveNodeContentGET);
			int httpRetCode = resp.getStatusLine().getStatusCode();
			String responseBodyAsString = EntityUtils.toString(resp.getEntity());
			
			if(HttpStatus.SC_OK == httpRetCode){
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
				mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
				JsonNode jsonRoot = mapper.readTree(responseBodyAsString);
				return jsonRoot;
			}
			else {
				CommunityAPIException ex = new CommunityAPIException(Messages.RESTCommunityHelper_NodeContentRetrieveError);
				ex.setHttpStatusCode(httpRetCode);
				ex.setResponseBodyAsString(responseBodyAsString);
				throw ex;
			}
		} catch (IOException e) {
			JSSCommunityActivator.getDefault().logError(
					Messages.RESTCommunityHelper_GetMethodIOError,e);
			throw new CommunityAPIException(Messages.RESTCommunityHelper_NodeContentRetrieveError,e);
		}
	}
	
}
