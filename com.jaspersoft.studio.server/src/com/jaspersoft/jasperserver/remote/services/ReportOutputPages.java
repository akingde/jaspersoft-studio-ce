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

package com.jaspersoft.jasperserver.remote.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jaspersoft.jasperserver.dto.common.ErrorDescriptor;
import com.jaspersoft.jasperserver.remote.exception.IllegalParameterValueException;

/**
 * <p>
 * </p>
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
					throw new IllegalParameterValueException(
							new ErrorDescriptor().setErrorCode("report.start.page.greater.then.end.page")
									.setParameters("Start page: " + startPage, "End page: " + endPage));
				}
			} else {
				throw new IllegalParameterValueException(
						new ErrorDescriptor().setErrorCode("report.invalid.page.range").setParameters(pages));
			}
		}
		return this;
	}

	public static ReportOutputPages valueOf(String pages) throws IllegalParameterValueException {
		return pages != null && !pages.isEmpty() ? new ReportOutputPages().setPages(pages) : null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ReportOutputPages))
			return false;

		ReportOutputPages that = (ReportOutputPages) o;

		if (endPage != null ? !endPage.equals(that.endPage) : that.endPage != null)
			return false;
		if (page != null ? !page.equals(that.page) : that.page != null)
			return false;
		if (startPage != null ? !startPage.equals(that.startPage) : that.startPage != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = page != null ? page.hashCode() : 0;
		result = 31 * result + (startPage != null ? startPage.hashCode() : 0);
		result = 31 * result + (endPage != null ? endPage.hashCode() : 0);
		return result;
	}

	public String toString() {
		String result = "no pages information";
		if (page != null) {
			result = page.toString();
		} else if (startPage != null && endPage != null) {
			result = startPage.toString() + "-" + endPage.toString();
		}
		return result;
	}
}
