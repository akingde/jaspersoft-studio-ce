/*
 * iReport - Visual Designer for JasperReports.
 * Copyright (C) 2002 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.ireport.jasperserver.ws;

import com.jaspersoft.ireport.jasperserver.ws.WSClient;

/**
 *
 * @author gtoffoli
 */
public class JServer {
    
    
     private String name;
     private String url;
     private String username;
     private String password;
       
     private WSClient wSClient = null;
     
     private boolean loaded = false;
     private boolean loading = false;
     
     private String locale = null;
     
    /** Creates a new instance of JServer */
    public JServer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setWSClient(null);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        setWSClient(null);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        setWSClient(null);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        setWSClient(null);
    }
    
    public String toString()
    {
        return ""+getName();
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public WSClient getWSClient() throws Exception {
    	if (wSClient == null) {
    		setWSClient(new WSClient(this));
    	}
    	return wSClient;
    }

    public void setWSClient(WSClient wSClient) {
        this.wSClient = wSClient;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
    
    
    
}
