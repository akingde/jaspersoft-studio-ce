/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.internal.mondrian;

import org.olap4j.metadata.NamedList;
import org.olap4j.metadata.Property;

import com.jaspersoft.studio.data.internal.olap.JROlapMember;

import mondrian.olap.Member;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRMondrianMember.java 5879 2013-01-07 20:35:36Z teodord $
 */
public class JRMondrianMember implements JROlapMember
{

	private final Member member;
	private final org.olap4j.metadata.Member olap4jMember;
	private final JRMondrianFactory factory;

	public JRMondrianMember(org.olap4j.metadata.Member olap4jMember, JRMondrianFactory factory)
	{
		this.member = null;
		this.olap4jMember = olap4jMember;
		this.factory = factory;
	}

	public JRMondrianMember(Member member, JRMondrianFactory factory)
	{
		this.member = member;
		this.olap4jMember = null;
		this.factory = factory;
	}
	
	public int getDepth()
	{
		if (member != null) {
			return member.getDepth();
		} else {
			return olap4jMember.getDepth();
		}
	}

	public String getName()
	{
		if (member != null) {
			return member.getName();
		} else {
			return olap4jMember.getName();
		}
	}

	public JROlapMember getParentMember()
	{
		if (member != null) {
			return new JRMondrianMember(member.getParentMember(), factory);
		} else {
			return new JRMondrianMember(olap4jMember.getParentMember(), factory);
		}
	}

	public Object getPropertyValue(String propertyName)
	{
		if (member != null) {
			return member.getPropertyValue(propertyName);
		} else {
			NamedList<Property> properties = olap4jMember.getProperties();
			return properties.get(propertyName);
		}
	}

	public String getUniqueName()
	{
		if (member != null) {
			return member.getUniqueName();
		} else {
			return olap4jMember.getUniqueName();
		}
	}

	public Object getMember()
	{
		if (member != null) {
			return member;
		} else {
			return olap4jMember;
		}
	}

}
