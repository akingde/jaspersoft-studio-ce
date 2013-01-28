package com.jaspersoft.studio.doc.handlers;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

/**
 * Abstract class to execute an action asynchronously. This is necessary to avoid to block 
 * the flow of the cheatsheet when a dialog is disposed. Infact when a dialog is opened using an action the 
 * body of the action will not terminate until the dialog is closed. But the cheatsheet must advance even 
 * when the dialog is opened (since it can be a step of a wizard), so it must be opened in a separate thread
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AsyncAction extends Action {

	
	/**
	 * body of the action to execute
	 */
	protected abstract void doAction();
	
	/**
	 * Run the body of the action
	 */
	@Override
	public void run() {
		Display.getCurrent().asyncExec(new Runnable() {
			public void run() {
				doAction();
		    }
		});
	}
	
}
