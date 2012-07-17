package com.jaspersoft.studio.data.querydesigner.sql;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.designer.QueryDesigner;

/**
 * Simple SQL query designer that simply provides syntax coloring support.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class SimpleSQLQueryDesigner extends QueryDesigner {

	// Line style for SQL code
	private SQLLineStyler sqlLineStyler;
	
	public SimpleSQLQueryDesigner() {
		super();
		sqlLineStyler=getSQLBasedLineStyler();
	}

	@Override
	public Control createControl(Composite parent) {
		super.createControl(parent);
		control.addLineStyleListener(sqlLineStyler);
		control.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				sqlLineStyler.parseBlockComments(control.getText());
			}
		});
		return control;
	}
	
	/**
	 * @return the SQL line styler
	 */
	protected SQLLineStyler getSQLBasedLineStyler(){
		return new SQLLineStyler();
	}
	
}
