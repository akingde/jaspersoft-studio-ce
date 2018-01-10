/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import com.jaspersoft.studio.editor.outline.actions.field.SortFieldsAction;
import com.jaspersoft.studio.editor.outline.part.TreeEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.EditPartVisitor;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

public class FieldUtils {
	public static void buildFieldsTree(MFields parentNode, final TreeEditPart treePart) {
		ISelection oldSelection = treePart.getViewer().getSelection();
		List<ANode> oldNodes = new ArrayList<ANode>();
		for (Object obj : ((StructuredSelection) oldSelection).toList())
			if (obj instanceof EditPart) {
				ANode m = (ANode) ((EditPart) obj).getModel();
				if (m instanceof MFieldsContainer) {
					// mfields can change the key, fields are not changing, so we can remember
					// fields and set selection on them ..., maybe we should remember which are in
					// place of fieldscontainer and replace them with father? create a new set here
					MField mf = new ModelVisitor<MField>(m) {

						@Override
						public boolean visit(INode n) {
							if (n instanceof MField) {
								setObject((MField) n);
								stop();
							}
							return true;
						}
					}.getObject();
					if (mf != null)
						oldNodes.add(mf);
				}
				oldNodes.add(m);
			}

		Map<String, List<MField>> map = new LinkedHashMap<String, List<MField>>();
		Map<String, String> pLabels = new HashMap<String, String>();
		new ModelVisitor<Object>(parentNode) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MField) {
					JRField f = ((MField) n).getValue();
					String path = Misc.nvl(f.getPropertiesMap().getProperty(DataQueryAdapters.FIELD_PATH));
					if (!path.isEmpty()) {
						String plabel = getLastName(path);
						if (!Misc.isNullOrEmpty(plabel))
							pLabels.put(path, plabel);
					}

					List<MField> fs = map.get(path);
					if (fs == null)
						fs = new ArrayList<>();
					fs.add((MField) n);
					map.put(path, fs);
				}
				return true;
			}
		};

		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		if (SortFieldsAction.areFieldsSorted(parentNode.getJasperConfiguration())) {
			for (List<MField> list : map.values()) {
				if (list != null) {
					Collections.sort(list, new Comparator<INode>() {

						@Override
						public int compare(INode o1, INode o2) {
							MField var1 = (MField) o1;
							MField var2 = (MField) o2;
							String nameVar1 = (String) var1.getPropertyActualValue(JRDesignField.PROPERTY_NAME);
							String nameVar2 = (String) var2.getPropertyActualValue(JRDesignField.PROPERTY_NAME);
							return nameVar1.toLowerCase().compareTo(nameVar2.toLowerCase());
						}
					});
				}
			}
		}
		parentNode.removeChildren();
		Map<String, MFields> parents = new HashMap<String, MFields>();
		List<String> mkeys = new ArrayList<String>(map.keySet());
		Collections.sort(mkeys);
		for (String key : mkeys) {
			String pkey = key;
			int indx = key.lastIndexOf(".");
			if (indx > 0)
				pkey = key.substring(0, indx);
			MFields parent = null;
			// calculate parent, go until parentNode and compare keys
			for (String pk : parents.keySet())
				if (pk.equals(pkey) || pkey.startsWith(pk + ".")) {
					parent = parents.get(pk);
					// break;
				}
			if (parent == null)
				parent = parentNode;
			if (!Misc.isNullOrEmpty(key)) {
				MFieldsContainer mfc = getMFieldContainer(oldNodes, key);
				if (mfc != null) {
					oldNodes.add(mfc);
					parent = mfc;
					mfc.removeChildren();
				} else {
					String k = "";
					if (parent instanceof MFieldsContainer)
						k = ((MFieldsContainer) parent).getKey();
					String[] parray = k.isEmpty() ? new String[0] : k.split("\\.");
					String[] pkeyArray = key.split("\\.");
					if (parray.length < pkeyArray.length - 1) {
						for (int i = parray.length; i < pkeyArray.length - 1; i++) {
							String ikey = "";
							String del = "";
							for (int j = 0; j <= i; j++) {
								ikey += del + pkeyArray[j];
								del = ".";
							}
							parent = new MFieldsContainer(parent, parentNode.getValue(), pLabels, ikey);
							parents.put(ikey, parent);
						}
					}

					parent = new MFieldsContainer(parent, parentNode.getValue(), pLabels, key);
				}
			}
			parents.put(key, parent);
			for (MField f : map.get(key))
				f.setParent(parent, -1);
		}
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				if (treePart == null || treePart.getViewer() == null)
					return;

				List<EditPart> parts = new ArrayList<EditPart>();
				new EditPartVisitor<Boolean>(treePart.getViewer().getContents()) {

					@Override
					public boolean visit(EditPart n) {
						if (oldNodes.contains(n.getModel()))
							parts.add(n);
						return true;
					}
				};
				treePart.getViewer().setSelection(new StructuredSelection(parts));
			}
		});
	}

	private static MFieldsContainer getMFieldContainer(List<ANode> nodes, String key) {
		for (int i = 0; i < nodes.size(); i++) {
			ANode n = nodes.get(i);
			if (n instanceof MFieldsContainer && ((MFieldsContainer) n).getKey().equals(key))
				return (MFieldsContainer) n;
		}
		return null;
	}

	private static String getLastName(String path) {
		int indx = path.lastIndexOf(".");
		if (indx > 0)
			path = path.substring(indx + 1);
		try {
			return URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return path;
		}
	}

	public static void flatTree(MFields parentNode) {
		List<MField> fields = new ArrayList<MField>();
		Boolean refresh = new ModelVisitor<Boolean>(parentNode) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MFields)
					setObject(true);
				if (n instanceof MField)
					fields.add((MField) n);
				return true;
			}
		}.getObject();
		if (refresh != null && refresh) {
			parentNode.removeChildren();
			for (MField f : fields)
				f.setParent(parentNode, -1);
		}
	}

	public static List<MField> getFields(MFields parentNode) {
		List<MField> fields = new ArrayList<MField>();
		new ModelVisitor<Object>(parentNode) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MField)
					fields.add((MField) n);
				return true;
			}
		}.getObject();
		return fields;
	}

	public static List<JRField> getFields(JRDesignDataset ds, String key) {
		List<JRField> fields = new ArrayList<JRField>();
		for (JRField f : ds.getFields()) {
			String k = f.getPropertiesMap().getProperty(DataQueryAdapters.FIELD_PATH);
			if (Misc.isNullOrEmpty(k))
				continue;
			if (k.startsWith(key))
				fields.add(f);
			// is this enough? should I look by separator instead?
		}
		return fields;
	}
}
