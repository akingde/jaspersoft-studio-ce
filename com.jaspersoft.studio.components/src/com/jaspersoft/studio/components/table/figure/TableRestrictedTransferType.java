/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.figure;

import org.eclipse.swt.dnd.ByteArrayTransfer;

/**
 * Define a transfer type, has essentially the goal to separate a drop of a Table Template form 
 * any other drag and drop operations
 * 
 * @author Orlandin Marco
 *
 */
public class TableRestrictedTransferType extends ByteArrayTransfer{

	/**
	 * The name of the transfer type
	 */
	private static final String TYPE_NAME = "table-style-transfer-format";//$NON-NLS-1$

	/**
	 * The transfer type id
	 */
	private static final int TYPEID = registerType(TYPE_NAME);
	
	private TableRestrictedTransferType() {
		super();
	}
	
	/**
	 * an instance of the transfer type
	 */
    private static TableRestrictedTransferType instance = new TableRestrictedTransferType();

    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance
     */
    public static TableRestrictedTransferType getInstance() {
        return instance;
    }

    protected int[] getTypeIds() {
        return new int[] { TYPEID };
    }

    protected String[] getTypeNames() {
        return new String[] { TYPE_NAME };
    }

}
