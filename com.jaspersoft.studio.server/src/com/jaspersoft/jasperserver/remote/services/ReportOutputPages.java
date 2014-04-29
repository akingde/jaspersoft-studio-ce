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
package com.jaspersoft.jasperserver.remote.services;

import com.jaspersoft.jasperserver.remote.exception.IllegalParameterValueException;
import com.jaspersoft.jasperserver.remote.exception.xml.ErrorDescriptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ReportOutputPages.java 26599 2012-12-10 13:04:23Z ykovalchyk $
 */
public class ReportOutputPages {
    private Integer page;
    private Integer startPage;
    private Integer endPage;

    public Integer getPage() {
        return page;
    }

    public ReportOutputPages setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public ReportOutputPages setStartPage(Integer startPage) {
        this.startPage = startPage;
        return this;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public ReportOutputPages setEndPage(Integer endPage) {
        this.endPage = endPage;
        return this;
    }

    public ReportOutputPages setPages(String pages) throws IllegalParameterValueException {
        if (Pattern.matches("\\d+", pages)) {
            setPage(Integer.valueOf(pages));
        } else {
            final Matcher matcher = Pattern.compile("^(\\d+)-(\\d+)$").matcher(pages);
            if (matcher.find()) {
                final Integer startPage = Integer.valueOf(matcher.group(1));
                final Integer endPage = Integer.valueOf(matcher.group(2));
                if (startPage.compareTo(endPage) < 0) {
                    setStartPage(startPage);
                    setEndPage(endPage);
                } else {
                    throw new IllegalParameterValueException(new ErrorDescriptor.Builder()
                            .setErrorCode("report.start.page.greater.then.end.page")
                            .setParameters("Start page: " + startPage, "End page: " + endPage).getErrorDescriptor());
                }
            } else {
                throw new IllegalParameterValueException(new ErrorDescriptor.Builder()
                        .setErrorCode("report.invalid.page.range")
                        .setParameters(pages).getErrorDescriptor());
            }
        }
        return this;
    }

    public static ReportOutputPages valueOf(String pages) throws IllegalParameterValueException {
        return pages != null && !pages.isEmpty() ? new ReportOutputPages().setPages(pages) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportOutputPages)) return false;

        ReportOutputPages that = (ReportOutputPages) o;

        if (endPage != null ? !endPage.equals(that.endPage) : that.endPage != null) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;
        if (startPage != null ? !startPage.equals(that.startPage) : that.startPage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = page != null ? page.hashCode() : 0;
        result = 31 * result + (startPage != null ? startPage.hashCode() : 0);
        result = 31 * result + (endPage != null ? endPage.hashCode() : 0);
        return result;
    }

    public String toString(){
        String result = "no pages information";
        if(page != null){
            result = page.toString();
        } else if(startPage != null && endPage != null){
            result = startPage.toString() + "-" + endPage.toString();
        }
        return result;
    }
}
