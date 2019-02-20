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
package com.jaspersoft.studio.data.random;

import net.sf.jasperreports.data.AbstractDataAdapter;

/**
 * The implementation of the dummy data adapter
 * 
 * @author Veaceslav Chicu
 *
 */
public class RandomDataAdapterImpl extends AbstractDataAdapter implements RandomDataAdapter {

	private int recordNumber = 10;

	@Override
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}

	@Override
	public int getRecordNumber() {
		return recordNumber;
	}

}