package com.jaspersoft.studio.utils.jobs;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

public class RunWithProgress implements IRunWithProgress {

	public void runJob(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException {
		ProgressMonitorDialog pm = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
		pm.run(true, true, runnable);
	}

}
