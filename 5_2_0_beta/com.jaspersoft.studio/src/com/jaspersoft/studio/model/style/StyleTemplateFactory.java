/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.style;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.eclipse.viewer.IEditorContributor;
import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplateReference;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.FileResolver;
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
import com.jaspersoft.studio.utils.CacheMap;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

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
		String str = ExpressionUtil.eval(jrObject.getSourceExpression(), parent.getJasperConfiguration());
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

	public static final File getFile(String location, IFile file) {
		return SelectionHelper.getFileResolver(file).resolveFile(location);
	}

	public static void createTemplateReference(ANode parent, String location, int newIndex, Set<String> set,
			boolean editable, IFile file) {
		if (file == null)
			return;
		File fileToBeOpened = getFile(location, file);
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
							ExpressionUtil.eval(s.getSourceExpression(), node.getJasperConfiguration()));
			}
			return;
		}
	}

	private static IFile getFile(ANode node, IFile refFile) {
		List<Object> plist = new ArrayList<Object>();
		ANode p = (ANode) node;
		JasperReportsConfiguration jConfig = node.getJasperConfiguration();
		plist.add(p.getValue());
		while (!(p.getParent() instanceof MStyles)) {
			p = (ANode) p.getParent();
			plist.add(p.getValue());
		}

		MStyleTemplate mst = (MStyleTemplate) p;
		JRDesignReportTemplate drt = (JRDesignReportTemplate) mst.getValue();

		return resolveTemplates(refFile, plist, jConfig, drt);
	}

	private static Map<JasperDesign, String[]> cstyles = new CacheMap<JasperDesign, String[]>(2000);

	public static String[] getAllStyles(JasperReportsConfiguration jConf, JRDesignElement jrElement) {
		// IMPROVEMENT: listen for all file changes, and update only when needed
		JasperDesign jd = jConf.getJasperDesign();
		String[] items = cstyles.get(jd);
		if (items == null) {
			JRStyle[] styles = jd.getStyles();
			List<JRStyle> slist = getStyles(jConf, jd, (IFile) jConf.get(IEditorContributor.KEY_FILE));
			int size = 1;
			if (styles != null)
				size += styles.length;
			if (slist != null)
				size += slist.size();
			items = new String[size];
			items[0] = jrElement.getStyleNameReference() != null ? jrElement.getStyleNameReference() : ""; //$NON-NLS-1$
			for (int j = 0; j < styles.length; j++)
				items[j + 1] = styles[j].getName();
			for (int j = 0; j < slist.size(); j++)
				items[styles.length + j + 1] = slist.get(j).getName();
			cstyles.put(jd, items);
		}
		return items;
	}

	private static List<JRStyle> getStyles(JasperReportsConfiguration jConfig, JasperDesign jd, IFile file) {
		List<JRStyle> list = new ArrayList<JRStyle>();
		for (JRReportTemplate t : jd.getTemplatesList())
			getStylesReference(file, ExpressionUtil.eval(t.getSourceExpression(), jConfig), list, new HashSet<File>());
		return list;
	}

	public static void getStylesReference(IFile file, String location, List<JRStyle> list, Set<File> files) {
		if (location == null)
			return;
		File fileToBeOpened = getFile(location, file);
		if (files.contains(fileToBeOpened))
			return;
		if (fileToBeOpened != null && fileToBeOpened.exists() && fileToBeOpened.isFile()) {
			files.add(fileToBeOpened);
			JRSimpleTemplate jrst = (JRSimpleTemplate) JRXmlTemplateLoader.load(fileToBeOpened);
			list.addAll(jrst.getStylesList());
			List<JRTemplateReference> tlist = jrst.getIncludedTemplatesList();
			if (tlist != null && !tlist.isEmpty()) {
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				IFile[] fs = root.findFilesForLocationURI(fileToBeOpened.toURI());
				if (fs != null && fs[0] != null)
					for (JRTemplateReference tr : tlist)
						getStylesReference(fs[0], tr.getLocation(), list, files);
			}
		}
	}

	protected static IFile resolveTemplates(IFile refFile, List<Object> plist, JasperReportsConfiguration jConfig,
			JRDesignReportTemplate drt) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String str = ExpressionUtil.eval(drt.getSourceExpression(), jConfig);
		if (str != null) {
			if (refFile == null)
				refFile = ((IFileEditorInput) SelectionHelper.getActiveJRXMLEditor().getEditorInput()).getFile();

			for (int i = plist.size() - 1; i >= 0; i--) {
				Object obj = plist.get(i);
				if (obj instanceof JRDesignReportTemplate) {
					str = ExpressionUtil.eval(((JRDesignReportTemplate) obj).getSourceExpression(), jConfig);
				} else if (obj instanceof JRTemplateReference) {
					str = ((JRTemplateReference) obj).getLocation();
				}

				FileResolver fileResolver = SelectionHelper.getFileResolver(refFile);
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
