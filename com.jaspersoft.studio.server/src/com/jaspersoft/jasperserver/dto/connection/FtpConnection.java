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

package com.jaspersoft.jasperserver.dto.connection;

import com.jaspersoft.jasperserver.dto.common.ResourceLocation;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: FtpConnection.java 39215 2013-10-31 07:59:03Z ykovalchyk $
 */
@XmlRootElement(name = "ftp")
public class FtpConnection implements ResourceLocation {
    private String host;
    private String userName;
    private String password;
    private String folderPath;
    private FtpType type;
    private String protocol;
    private Integer port;
    private Boolean implicit;
    private String prot;
    private Long pbsz;

    public FtpConnection(){}
    public FtpConnection(FtpConnection source){
        this.host = source.getHost();
        this.userName = source.getUserName();
        this.password = source.getPassword();
        this.folderPath = source.getFolderPath();
        this.type = source.getType();
        this.protocol = source.getProtocol();
        this.port = source.getPort();
        this.implicit = source.getImplicit();
        this.prot = source.getProt();
        this.pbsz = source.getPbsz();
    }

    public String getFolderPath() {
        return folderPath;
    }

    public FtpConnection setFolderPath(String folderPath) {
        this.folderPath = folderPath;
        return this;
    }

    public FtpType getType() {
        return type;
    }

    public FtpConnection setType(FtpType type) {
        this.type = type;
        return this;
    }

    public String getProtocol() {
        return protocol;
    }

    public FtpConnection setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public FtpConnection setPort(Integer port) {
        this.port = port;
        return this;
    }

    public Boolean getImplicit() {
        return implicit;
    }

    public FtpConnection setImplicit(Boolean implicit) {
        this.implicit = implicit;
        return this;
    }

    public String getProt() {
        return prot;
    }

    public FtpConnection setProt(String prot) {
        this.prot = prot;
        return this;
    }

    public Long getPbsz() {
        return pbsz;
    }

    public FtpConnection setPbsz(Long pbsz) {
        this.pbsz = pbsz;
        return this;
    }

    public String getHost() {
        return host;
    }

    public FtpConnection setHost(String host) {
        this.host = host;
        return this;
    }

    public String getUserName() {
        // Since the set is not being used by the serializer we need to adjust it via the get
        String ret = (userName!=null && !userName.isEmpty()) ?  userName : "anonymous";
        return ret;
    }

    public FtpConnection setUserName(String userName) {
        this.userName = (userName!=null && !userName.isEmpty()) ?  userName : "anonymous";
        return this;
    }

    public String getPassword() {
        return password;
    }

    public FtpConnection setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FtpConnection that = (FtpConnection) o;

        if (folderPath != null ? !folderPath.equals(that.folderPath) : that.folderPath != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (implicit != null ? !implicit.equals(that.implicit) : that.implicit != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (pbsz != null ? !pbsz.equals(that.pbsz) : that.pbsz != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        if (prot != null ? !prot.equals(that.prot) : that.prot != null) return false;
        if (protocol != null ? !protocol.equals(that.protocol) : that.protocol != null) return false;
        if (type != that.type) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (folderPath != null ? folderPath.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (protocol != null ? protocol.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (implicit != null ? implicit.hashCode() : 0);
        result = 31 * result + (prot != null ? prot.hashCode() : 0);
        result = 31 * result + (pbsz != null ? pbsz.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FtpConnection{" +
                "host='" + host + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", folderPath='" + folderPath + '\'' +
                ", type=" + type +
                ", protocol='" + protocol + '\'' +
                ", port=" + port +
                ", implicit=" + implicit +
                ", prot='" + prot + '\'' +
                ", pbsz=" + pbsz +
                "} " + super.toString();
    }

    public enum FtpType{
        ftp, ftps
    }
}
