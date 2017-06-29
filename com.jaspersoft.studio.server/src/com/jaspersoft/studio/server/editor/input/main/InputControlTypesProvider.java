/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor.input.main;

import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.editor.preview.input.ext.IInputControlTypeProvider;

public class InputControlTypesProvider implements IInputControlTypeProvider {

	@Override
	public IDataInput[] getInputControlTypes() {
		return new IDataInput[] { new UserInput() };
	}

}
