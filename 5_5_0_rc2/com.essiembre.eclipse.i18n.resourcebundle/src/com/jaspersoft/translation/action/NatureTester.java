package com.jaspersoft.translation.action;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import com.jaspersoft.translation.resources.TranslationProjectNature;

public class NatureTester extends PropertyTester {

	private boolean evaluateElement(Object receiver){
		if (receiver instanceof IProject){
			IProject project = (IProject)receiver;  
		     try {
				return project.hasNature(TranslationProjectNature.NATURE_ID);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	    return false;
	}
	
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof Collection){
			boolean allRight = true;
			for (Iterator<?> it = ((Collection<?>) receiver).iterator(); it.hasNext() && allRight;) {
				allRight = evaluateElement(it.next());
			}
			return allRight;
		} else {
			return evaluateElement(receiver);
		}

	}

}
