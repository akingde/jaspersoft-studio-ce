package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.jaspersoft.studio.model.MRoot;

public class Text2Model {

	public static void text2model(MRoot root, XtextDocument doc) {
		System.out.println("convert the model");
		doc.readOnly(new IUnitOfWork<String, XtextResource>() {
			public String exec(XtextResource resource) {
				EObject eobj = resource.getContents().get(0);
				return "";
			}

		});
	}

}
