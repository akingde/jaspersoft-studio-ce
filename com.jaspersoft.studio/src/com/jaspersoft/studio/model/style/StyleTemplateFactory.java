/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.studio.model.style;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplateReference;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.SimpleFileResolver;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.SelectionHelper;

public class StyleTemplateFactory {

	public static ANode createNode(ANode parent, Object obj, int index, IFile file, JRSimpleTemplate jrst) {
		if (obj instanceof JRDesignStyle) {
			index += jrst.getIncludedTemplatesList().size();
			return new MStyle(parent, (JRDesignStyle) obj, index);
		}
		if (obj instanceof JRTemplateReference) {
			ANode n = new MStyleTemplateReference(parent, (JRTemplateReference) obj, index);
			createTemplateReference(n, ((JRTemplateReference) obj).getLocation(), -1, new HashSet<String>(), false, file);
		}
		return null;
	}

	public static ANode createTemplate(ANode parent, JRDesignReportTemplate jrObject, int newIndex, IFile file) {
		MStyleTemplate mStyleTemplate = new MStyleTemplate(parent, (JRDesignReportTemplate) jrObject, newIndex);
		String str = ExpressionUtil.eval(jrObject.getSourceExpression(), parent.getJasperDesign());
		if (str != null) {
			Set<String> set = new HashSet<String>();
			if (file == null) {
				IEditorPart ep = SelectionHelper.getActiveJRXMLEditor();
				if (ep != null)
					file = ((IFileEditorInput) ep.getEditorInput()).getFile();
			}
			createTemplateReference(mStyleTemplate, str, -1, set, false, file);

			return mStyleTemplate;
		}
		return null;
	}

	public static void createTemplateReference(ANode parent, String location, int newIndex, Set<String> set,
			boolean editable, IFile file) {
		if (file == null)
			return;
		SimpleFileResolver fileResolver = SelectionHelper.getFileResolver(file);

		File fileToBeOpened = fileResolver.resolveFile(location);
		if (fileToBeOpened != null && fileToBeOpened.exists() && fileToBeOpened.isFile()) {
			JRSimpleTemplate jrst = (JRSimpleTemplate) JRXmlTemplateLoader.load(fileToBeOpened);
			createTemplate(parent, set, editable, file, fileToBeOpened, jrst);
		}
	}

	public static void createTemplate(ANode parent, Set<String> set, boolean editable, IFile file, File fileToBeOpened,
			JRSimpleTemplate jrst) {
		for (JRTemplateReference s : jrst.getIncludedTemplates()) {
			MStyleTemplateReference p = new MStyleTemplateReference(parent, s, -1);
			p.setEditable(editable);
			if (set.contains(fileToBeOpened.getAbsolutePath()))
				continue;
			set.add(fileToBeOpened.getAbsolutePath());

			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IFile[] fs = root.findFilesForLocationURI(fileToBeOpened.toURI());
			if (fs != null && fs.length > 0)
				createTemplateReference(p, s.getLocation(), -1, set, editable, fs[0]);
			else
				createTemplateReference(p, s.getLocation(), -1, set, editable, file);
		}

		for (JRStyle s : jrst.getStyles()) {
			APropertyNode n = (APropertyNode) ReportFactory.createNode(parent, s, -2);
			n.setEditable(editable);
		}
	}

	public static void openEditor(Object obj, IEditorInput editorInput, ANode node) {
		if (obj instanceof JRStyle || obj instanceof JRConditionalStyle) {
			if (node.getParent() instanceof MStyles)
				return;
			if (node instanceof MConditionalStyle)
				node = (ANode) node.getParent();
			if (node instanceof MStyle)
				node = (ANode) node.getParent();
		}
		if (node instanceof MStyleTemplate)
			obj = node.getValue();
		else if (node instanceof MStyleTemplateReference) {
			IFile file = getFile(node, ((FileEditorInput) editorInput).getFile());
			JRTemplateReference st = (JRTemplateReference) node.getValue();
			SelectionHelper.openEditor(file, st.getLocation());
		}

		if (obj instanceof JRDesignReportTemplate) {
			if (editorInput instanceof FileEditorInput) {
				JRDesignReportTemplate s = (JRDesignReportTemplate) obj;
				if (s.getSourceExpression() != null)
					SelectionHelper.openEditor((FileEditorInput) editorInput,
							ExpressionUtil.eval(s.getSourceExpression(), node.getJasperDesign()));
			}
			return;
		}
	}

	private static IFile getFile(ANode node, IFile refFile) {
		List<Object> plist = new ArrayList<Object>();
		ANode p = (ANode) node;
		plist.add(p.getValue());
		while (!(p.getParent() instanceof MStyles)) {
			p = (ANode) p.getParent();
			plist.add(p.getValue());
		}
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		MStyleTemplate mst = (MStyleTemplate) p;
		JRDesignReportTemplate drt = (JRDesignReportTemplate) mst.getValue();
		JasperDesign jd = node.getJasperDesign();
		String str = ExpressionUtil.eval(drt.getSourceExpression(), jd);
		if (str != null) {
			if (refFile == null)
				refFile = ((IFileEditorInput) SelectionHelper.getActiveJRXMLEditor().getEditorInput()).getFile();

			for (int i = plist.size() - 1; i >= 0; i--) {
				Object obj = plist.get(i);
				if (obj instanceof JRDesignReportTemplate) {
					str = ExpressionUtil.eval(((JRDesignReportTemplate) obj).getSourceExpression(), jd);
				} else if (obj instanceof JRTemplateReference) {
					str = ((JRTemplateReference) obj).getLocation();
				}

				SimpleFileResolver fileResolver = SelectionHelper.getFileResolver(refFile);
				File fileToBeOpened = fileResolver.resolveFile(str);
				if (fileToBeOpened != null && fileToBeOpened.exists() && fileToBeOpened.isFile()) {
					IFile[] fs = root.findFilesForLocationURI(fileToBeOpened.toURI());
					if (fs != null && fs.length > 0) {
						refFile = fs[0];
					} else
						break;
				}
			}
			return refFile;
		}
		return null;
	}

}
