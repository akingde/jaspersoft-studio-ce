/*
* Copyright (C) 2005 - 2009 Jaspersoft Corporation. All rights  reserved.
* http://www.jaspersoft.com.
*
* Unless you have purchased  a commercial license agreement from Jaspersoft,
* the following license terms  apply:
*
* This program is free software: you can redistribute it and/or  modify
* it under the terms of the GNU Affero General Public License  as
* published by the Free Software Foundation, either version 3 of  the
* License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero  General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public  License
* along with this program.&nbsp; If not, see <http://www.gnu.org/licenses/>.
*/
package com.jaspersoft.jasperserver.dto.resources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientAwsDataSource.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
@XmlRootElement(name = ResourceMediaType.AWS_DATA_SOURCE_CLIENT_TYPE)
public class ClientAwsDataSource extends AbstractClientJdbcDataSource<ClientAwsDataSource> {
    private String accessKey;
    private String secretKey;
    private String roleArn;
    private String region;
    private String dbName;
    private String dbInstanceIdentifier;
    private String dbService;

    public String getAccessKey() {
        return accessKey;
    }

    public ClientAwsDataSource setAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public ClientAwsDataSource setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public ClientAwsDataSource setRoleArn(String roleArn) {
        this.roleArn = roleArn;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public ClientAwsDataSource setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getDbName() {
        return dbName;
    }

    public ClientAwsDataSource setDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public String getDbInstanceIdentifier() {
        return dbInstanceIdentifier;
    }

    public ClientAwsDataSource setDbInstanceIdentifier(String dbInstanceIdentifier) {
        this.dbInstanceIdentifier = dbInstanceIdentifier;
        return this;
    }

    public String getDbService() {
        return dbService;
    }

    public ClientAwsDataSource setDbService(String dbService) {
        this.dbService = dbService;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientAwsDataSource that = (ClientAwsDataSource) o;

        if (accessKey != null ? !accessKey.equals(that.accessKey) : that.accessKey != null) return false;
        if (dbInstanceIdentifier != null ? !dbInstanceIdentifier.equals(that.dbInstanceIdentifier) : that.dbInstanceIdentifier != null)
            return false;
        if (dbName != null ? !dbName.equals(that.dbName) : that.dbName != null) return false;
        if (dbService != null ? !dbService.equals(that.dbService) : that.dbService != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (roleArn != null ? !roleArn.equals(that.roleArn) : that.roleArn != null) return false;
        if (secretKey != null ? !secretKey.equals(that.secretKey) : that.secretKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (accessKey != null ? accessKey.hashCode() : 0);
        result = 31 * result + (secretKey != null ? secretKey.hashCode() : 0);
        result = 31 * result + (roleArn != null ? roleArn.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (dbName != null ? dbName.hashCode() : 0);
        result = 31 * result + (dbInstanceIdentifier != null ? dbInstanceIdentifier.hashCode() : 0);
        result = 31 * result + (dbService != null ? dbService.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientAwsDataSource{" +
                "accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", roleArn='" + roleArn + '\'' +
                ", region='" + region + '\'' +
                ", dbName='" + dbName + '\'' +
                ", dbInstanceIdentifier='" + dbInstanceIdentifier + '\'' +
                ", dbService='" + dbService + '\'' +
                ", version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
