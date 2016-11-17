/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.figure;

import org.eclipse.swt.dnd.ByteArrayTransfer;

/**
 * Define a transfer type, has essentially the goal to separate a drop of a CrossTab Template form 
 * any other drag and drop operations
 * 
 * @author Orlandin Marco
 *
 */
public class CrosstrabRestrictedTransferType extends ByteArrayTransfer{

	/**
	 * The name of the transfer type
	 */
	private static final String TYPE_NAME = "crosstab-style-transfer-format";

	/**
	 * The transfer type id
	 */
	private static final int TYPEID = registerType(TYPE_NAME);
	
	private CrosstrabRestrictedTransferType() {
		super();
	}
	
	/**
	 * an instance of the transfer type
	 */
    private static CrosstrabRestrictedTransferType instance = new CrosstrabRestrictedTransferType();
    
    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance
     */
    public static CrosstrabRestrictedTransferType getInstance() {
        return instance;
    }

    protected int[] getTypeIds() {
        return new int[] { TYPEID };
    }

    protected String[] getTypeNames() {
        return new String[] { TYPE_NAME };
    }

}
