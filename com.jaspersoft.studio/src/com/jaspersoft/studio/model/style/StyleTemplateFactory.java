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
import net.sf.jasperreports.engine.util.JRExpressionUtil;
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
import com.jaspersoft.studio.utils.SelectionHelper;

public class StyleTemplateFactory {
	public static ANode createTemplate(ANode parent, JRDesignReportTemplate jrObject, int newIndex, IFile file) {
		MStyleTemplate mStyleTemplate = new MStyleTemplate(parent, (JRDesignReportTemplate) jrObject, newIndex);
		String str = JRExpressionUtil.getSimpleExpressionText(jrObject.getSourceExpression());
		Set<String> set = new HashSet<String>();
		if (file == null) {
			IEditorPart ep = SelectionHelper.getActiveJRXMLEditor();
			if (ep != null)
				file = ((IFileEditorInput) ep.getEditorInput()).getFile();
		}
		createTemplateReference(mStyleTemplate, str, -1, set, false, file);

		return mStyleTemplate;
	}

	private static void createTemplateReference(ANode parent, String location, int newIndex, Set<String> set,
			boolean editable, IFile file) {
		if (file == null)
			return;
		SimpleFileResolver fileResolver = SelectionHelper.getFileResolver(file);

		File fileToBeOpened = fileResolver.resolveFile(location);
		if (fileToBeOpened != null && fileToBeOpened.exists() && fileToBeOpened.isFile()) {
			JRSimpleTemplate jrst = (JRSimpleTemplate) JRXmlTemplateLoader.load(fileToBeOpened);
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
							JRExpressionUtil.getSimpleExpressionText(s.getSourceExpression()));
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
		String str = JRExpressionUtil.getSimpleExpressionText(drt.getSourceExpression());

		if (refFile == null)
			refFile = ((IFileEditorInput) SelectionHelper.getActiveJRXMLEditor().getEditorInput()).getFile();

		for (int i = plist.size() - 1; i >= 0; i--) {
			Object obj = plist.get(i);
			if (obj instanceof JRDesignReportTemplate) {
				str = JRExpressionUtil.getSimpleExpressionText(((JRDesignReportTemplate) obj).getSourceExpression());
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

}
