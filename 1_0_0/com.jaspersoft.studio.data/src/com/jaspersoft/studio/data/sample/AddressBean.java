/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.sample;

public class AddressBean {
	
	private String street;
    
    private String country;
    
    private String state;
    
    /** Creates a new instance of AddressBean */
    public AddressBean() {
    }
    
    /**
     * Getter for property country.
     * @return Value of property country.
     */
    public java.lang.String getCountry() {
        return country;
    }
    
    /**
     * Setter for property country.
     * @param country New value of property country.
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }
    
    /**
     * Getter for property state.
     * @return Value of property state.
     */
    public java.lang.String getState() {
        return state;
    }
    
    /**
     * Setter for property state.
     * @param state New value of property state.
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }
    
    /**
     * Getter for property street.
     * @return Value of property street.
     */
    public java.lang.String getStreet() {
        return street;
    }
    
    /**
     * Setter for property street.
     * @param street New value of property street.
     */
    public void setStreet(java.lang.String street) {
        this.street = street;
    }
}
