package com.jaspersoft.studio.data;

import org.eclipse.swt.widgets.Composite;

/**
 * A IReportConnectionEditor class provides a complete custom GUI for customizing a target IReportConnection.<br>
 * Each IReportConnectionEditor should inherit from the java.awt.Component class so it can be instantiated inside an AWT
 * dialog or panel.<br>
 * Each IReportConnectionEditor should have a null constructor.<br>
 * 
 * @author gtoffoli
 */
public interface DataAdapterEditor {

	/**
	 * Set the DataAdapter to edit. Actually it is a copy of the original DataAdapter. It can be modifed by
	 * the user interface.<br>
	 * <br>
	 * 
	 * The copy of an DataAdapter is done instancing a new class of the same type and loading the properties stored
	 * by the first object
	 * 
	 * @param dataAdapter
	 *          DataAdapter to edit
	 */
	public void setDataAdapter(DataAdapter dataAdapter);

	/**
	 * This method is called when the user completes to edit the datasource or when a datasource test is required.
	 * 
	 * @return IReportConnection modified. IT can be the same instance get in input with setIReportConnection or a new
	 *         one.
	 */
	public DataAdapter getDataAdapter();
	
	
	/**
	 * This method allows to provide a UI component to edit the data adapter.
	 * 
	 * @return IReportConnection modified. IT can be the same instance get in input with setIReportConnection or a new
	 *         one.
	 */
	public Composite getComposite(Composite parent, int style);

}
