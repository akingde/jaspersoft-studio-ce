/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package com.jaspersoft.jasperserver.api.engine.scheduling.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb.FtpTypeAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Holder for FTP information
 *
 * @author Ivan Chan (ichan@jaspersoft.com)
 * @version $Id: FTPInfo.java 38348 2013-09-30 04:57:18Z carbiv $
 * @since 1.0
 */
@JasperServerAPI
@XmlRootElement
public class FTPInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    String userName;
    String password;
    String folderPath;
    String serverName;
    private volatile Map<String, String> propertiesMap;
    private static String FTP_TYPE_PROPERTY = "FTP_TYPE_PROPERTY";
    private static String PORT_PROPERTY = "PORT_PROPERTY";
    private static String PROTOCOL_PROPERTY = "PROTOCOL_PROPERTY";
    private static String IS_IMPLICIT_PROPERTY = "IS_IMPLICIT_PROPERTY";
    private static String PHSZ_PROPERTY = "PHSZ_PROPERTY";
    private static String PROT_PROPERTY = "PROT_PROPERTY";

    public static String TYPE_FTP = "TYPE_FTP";
    public static String TYPE_FTPS = "TYPE_FTPS";

    /**
     * Creates an empty FTP information holder.
     */
    public FTPInfo() {
    }

    public FTPInfo(FTPInfo source){
        this.setUserName(source.getUserName());
        this.setPassword(source.getPassword());
        this.setFolderPath(source.getFolderPath());
        this.setServerName(source.getServerName());
        if(source.getPropertiesMap() != null){
            this.setPropertiesMap(new HashMap<String, String>(source.getPropertiesMap()));
        }
    }

    /**
     * Returns the login user name for the FTP server
     *
     * @return the login user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Specifies whether the login user name for the FTP server
     *
     * @param userName the login user name
     */
    public void setUserName(String userName) {
        this.userName = (userName!=null && !userName.isEmpty()) ?  userName : "anonymous";
    }

    /**
     * Returns the login password for the FTP server
     *
     * @return the login password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Specifies whether the login password for the FTP server
     *
     * @param password the login password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the path of the folder under which job output
     * resources would be created.
     *
     * @return the folder path
     */
    public String getFolderPath() {
        return folderPath;
    }

    /**
     * Specifies whether the path of the folder under which job output
     * resources would be created.
     *
     * @param folderPath the folder path
     */
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    /**
     * Returns the server name for the ftp site.
     *
     * @return the server name
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Specifies whether the server name for the ftp site.
     *
     * @param serverName the server name
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }


    /**
     * Returns FTP type: TYPE_FTP / TYPE_FTPS
     *
     * @return the ftp type
     */
    @XmlJavaTypeAdapter(FtpTypeAdapter.class)
    public String getType() {
        String prop = null;
        if (propertiesMap != null) prop = propertiesMap.get(FTP_TYPE_PROPERTY);
        if (prop == null) return TYPE_FTPS;
        else return prop;
    }


    /**
     * Specifies FTP type: TYPE_FTP / TYPE_FTPS
     *
     * @param type the ftp type
     */
    public void setType(String type) {
        setProperty(FTP_TYPE_PROPERTY, type);
    }

    /**
     * Returns the port number of the ftp site
     *
     * @return the port number
     */
    public int getPort() {
        String prop = null;
        if (propertiesMap != null) prop = propertiesMap.get(PORT_PROPERTY);
        if (prop != null) return Integer.parseInt(prop);
        if (getType().equals(TYPE_FTPS)) return 990;
        else return 21;
    }

    /**
     * Specifies the port number of the ftp site
     *
     * @param port the port number
     */
    public void setPort(int port) {
        setProperty(PORT_PROPERTY, port + "");
    }

    /**
     * Returns the protocol of the ftp site
     *
     * @return the protocol
     */
    public String getProtocol() {
        if (propertiesMap == null) return null;
        return propertiesMap.get(PROTOCOL_PROPERTY);
    }

    /**
     * Specifies the protocol of the ftp site
     *
     * @param protocol the protocol
     */
    public void setProtocol(String protocol) {
        setProperty(PROTOCOL_PROPERTY, protocol);
    }

    /**
     * Returns the security mode for FTPS (Implicit/ Explicit)
     * If isImplicit is true, the default port is set to 990
     *
     * @return the protocol
     */
    public boolean isImplicit() {
        if (propertiesMap == null) return true;
        String prop = propertiesMap.get(IS_IMPLICIT_PROPERTY);
        if (prop != null) return Boolean.parseBoolean(prop);
        else return true;
    }

    /**
     * Specifies the security mode for FTPS (Implicit/ Explicit)
     * If isImplicit is true, the default port is set to 990
     *
     * @param implicit
     */
    public void setImplicit(boolean implicit) {
        setProperty(IS_IMPLICIT_PROPERTY, implicit + "");
    }

    /**
     * return pbsz value: 0 to (2^32)-1 decimal integer.
     *
     * @return Protection Buffer Size.
     */
    public long getPbsz() {
        if (propertiesMap == null) return 0;
        String prop = propertiesMap.get(PHSZ_PROPERTY);
        if (prop != null) return Long.parseLong(prop);
        else return 0;
    }

    /**
     * specifies pbsz value: 0 to (2^32)-1 decimal integer.
     *
     * @param pbsz Protection Buffer Size.
     */
    public void setPbsz(long pbsz) {
        setProperty(PHSZ_PROPERTY, pbsz + "");
    }

    /**
     * return PROT command.
     * <ul>
     * <li>C - Clear</li>
     * <li>S - Safe(SSL protocol only)</li>
     * <li>E - Confidential(SSL protocol only)</li>
     * <li>P - Private</li>
     * </ul>
     *
     * @return Data Channel Protection Level
     */
    public String getProt() {
        if (propertiesMap == null) return null;
        return propertiesMap.get(PROT_PROPERTY);
    }

    /**
     * specific PROT command.
     * <ul>
     * <li>C - Clear</li>
     * <li>S - Safe(SSL protocol only)</li>
     * <li>E - Confidential(SSL protocol only)</li>
     * <li>P - Private</li>
     * </ul>
     *
     * @param prot Data Channel Protection Level
     */
    public void setProt(String prot) {
        setProperty(PROT_PROPERTY, prot);
    }

    /**
     * Returns the additional properties for ftp info
     *
     * @return the additional properties for FTP info
     */
    @XmlTransient
    public Map<String, String> getPropertiesMap() {
        return propertiesMap;
    }

    /**
     * Sets the set additional properties for FTP info
     *
     * @param propertiesMap extra properties for FTP info
     */
    public void setPropertiesMap(Map<String, String> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }

    protected void setProperty(String propertyName, String propertyValue) {
        if (propertiesMap == null) {
            synchronized (this) {
                if (propertiesMap == null) {
                    propertiesMap = new HashMap<String, String>();
                }
            }
        }
        if (propertyValue == null) {
            propertiesMap.remove(propertyName);
        } else {
            propertiesMap.put(propertyName, propertyValue);
        }
    }

}
