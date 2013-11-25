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
package com.jaspersoft.jasperserver.dto.reports.inputcontrols;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: ReportInputControlsListWrapper.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 */
@XmlRootElement(name = "inputControls")
public class ReportInputControlsListWrapper {

    private List<ReportInputControl> inputParameters;

    public ReportInputControlsListWrapper(){}
    public ReportInputControlsListWrapper(List<ReportInputControl> inputParameters){
        this.inputParameters = inputParameters;
    }
    @XmlElement(name = "inputControl")
    public List<ReportInputControl> getInputParameters() {
        return inputParameters;
    }

    public ReportInputControlsListWrapper setInputParameters(List<ReportInputControl> inputParameters) {
        this.inputParameters = inputParameters;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportInputControlsListWrapper)) return false;

        ReportInputControlsListWrapper that = (ReportInputControlsListWrapper) o;

        if (inputParameters != null ? !inputParameters.equals(that.inputParameters) : that.inputParameters != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return inputParameters != null ? inputParameters.hashCode() : 0;
    }
}
