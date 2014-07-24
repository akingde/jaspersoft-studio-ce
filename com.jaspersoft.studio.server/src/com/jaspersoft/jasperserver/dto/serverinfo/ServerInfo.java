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

package com.jaspersoft.jasperserver.dto.serverinfo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Volodya Sabadosh (vsabadosh@jaspersoft.com)
 * @version $Id: ServerInfo.java 39313 2013-11-07 10:04:37Z ykovalchyk $
 */
@XmlRootElement
public class ServerInfo {

	public ServerInfo() {
	}

	public ServerInfo(ServerInfo source) {
		dateFormatPattern = source.getDateFormatPattern();
		datetimeFormatPattern = source.getDatetimeFormatPattern();
		timeFormatPattern = source.getTimeFormatPattern();
		version = source.getVersion();
		edition = source.getEdition();
		editionName = source.getEditionName();
		licenseType = source.getLicenseType();
		build = source.getBuild();
		expiration = source.getExpiration();
		features = source.getFeatures();
	}

	private String dateFormatPattern;

	private String datetimeFormatPattern;
	private String timeFormatPattern;

	private String version;

	private ServerEdition edition;

	private String editionName;

	private String licenseType;

	private String build;

	private String expiration;

	private String features;

	public String getTimeFormatPattern() {
		return timeFormatPattern;
	}

	public void setTimeFormatPattern(String timeFormatPattern) {
		this.timeFormatPattern = timeFormatPattern;
	}

	public String getDateFormatPattern() {
		return dateFormatPattern;
	}

	public ServerInfo setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
		return this;
	}

	public String getDatetimeFormatPattern() {
		return datetimeFormatPattern;
	}

	public ServerInfo setDatetimeFormatPattern(String datetimeFormatPattern) {
		this.datetimeFormatPattern = datetimeFormatPattern;
		return this;
	}

	public String getVersion() {
		return version;
	}

	public ServerInfo setVersion(String version) {
		this.version = version;
		return this;
	}

	public ServerInfo setEdition(ServerEdition edition) {
		this.edition = edition;
		return this;
	}

	public ServerEdition getEdition() {
		return edition;
	}

	public String getEditionName() {
		return editionName;
	}

	public ServerInfo setEditionName(String editionName) {
		this.editionName = editionName;
		return this;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public ServerInfo setLicenseType(String licenseType) {
		this.licenseType = licenseType;
		return this;
	}

	public String getBuild() {
		return build;
	}

	public ServerInfo setBuild(String build) {
		this.build = build;
		return this;
	}

	public String getExpiration() {
		return expiration;
	}

	public ServerInfo setExpiration(String expiration) {
		this.expiration = expiration;
		return this;
	}

	public String getFeatures() {
		return features;
	}

	public ServerInfo setFeatures(String features) {
		this.features = features;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ServerInfo that = (ServerInfo) o;

		if (build != null ? !build.equals(that.build) : that.build != null)
			return false;
		if (dateFormatPattern != null ? !dateFormatPattern.equals(that.dateFormatPattern) : that.dateFormatPattern != null)
			return false;
		if (datetimeFormatPattern != null ? !datetimeFormatPattern.equals(that.datetimeFormatPattern) : that.datetimeFormatPattern != null)
			return false;
		if (edition != that.edition)
			return false;
		if (editionName != null ? !editionName.equals(that.editionName) : that.editionName != null)
			return false;
		if (expiration != null ? !expiration.equals(that.expiration) : that.expiration != null)
			return false;
		if (features != null ? !features.equals(that.features) : that.features != null)
			return false;
		if (licenseType != null ? !licenseType.equals(that.licenseType) : that.licenseType != null)
			return false;
		if (version != null ? !version.equals(that.version) : that.version != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = dateFormatPattern != null ? dateFormatPattern.hashCode() : 0;
		result = 31 * result + (datetimeFormatPattern != null ? datetimeFormatPattern.hashCode() : 0);
		result = 31 * result + (version != null ? version.hashCode() : 0);
		result = 31 * result + (edition != null ? edition.hashCode() : 0);
		result = 31 * result + (editionName != null ? editionName.hashCode() : 0);
		result = 31 * result + (licenseType != null ? licenseType.hashCode() : 0);
		result = 31 * result + (build != null ? build.hashCode() : 0);
		result = 31 * result + (expiration != null ? expiration.hashCode() : 0);
		result = 31 * result + (features != null ? features.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ServerInfo{" + "dateFormatPattern='" + dateFormatPattern + '\'' + ", datetimeFormatPattern='" + datetimeFormatPattern + '\'' + ", version='" + version + '\'' + ", edition=" + edition
				+ ", editionName='" + editionName + '\'' + ", licenseType='" + licenseType + '\'' + ", build='" + build + '\'' + ", expiration='" + expiration + '\'' + ", features='" + features + '\'' + "} "
				+ super.toString();
	}

	public static enum ServerEdition {
		CE, PRO
	}

}
