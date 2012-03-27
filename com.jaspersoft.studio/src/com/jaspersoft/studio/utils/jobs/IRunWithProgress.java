package com.jaspersoft.studio.utils.jobs;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.operation.IRunnableWithProgress;

public interface IRunWithProgress {
	public void runJob(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException;
}
