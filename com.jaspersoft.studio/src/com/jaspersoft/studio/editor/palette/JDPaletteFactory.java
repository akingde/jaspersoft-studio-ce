/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.palette;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.editor.tools.CompositeElementManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MBreak;
import com.jaspersoft.studio.model.MEllipse;
import com.jaspersoft.studio.model.MLine;
import com.jaspersoft.studio.model.MRectangle;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.model.genericElement.MGenericElement;
import com.jaspersoft.studio.model.image.MImage;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.model.textfield.MDate;
import com.jaspersoft.studio.model.textfield.MPageNumber;
import com.jaspersoft.studio.model.textfield.MPageXofY;
import com.jaspersoft.studio.model.textfield.MPercentage;
import com.jaspersoft.studio.model.textfield.MTime;
import com.jaspersoft.studio.model.textfield.MTotalPages;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.plugin.IPaletteContributor;
import com.jaspersoft.studio.plugin.PaletteGroup;
import com.jaspersoft.studio.preferences.PalettePreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * A factory for creating JDPalette objects.
 */
public class JDPaletteFactory {

	public static PaletteGroup getBaseElementsGroup() {
		PaletteGroup pgc = new PaletteGroup();
		pgc.setId(IPaletteContributor.KEY_COMMON_ELEMENTS);
		pgc.setName(Messages.common_elements);
		pgc.setImage("icons/resources/elementgroup-16.png"); //$NON-NLS-1$
		return pgc;
	}

	public static PaletteGroup getOtherElementsGroup() {
		PaletteGroup pgc = new PaletteGroup();
		pgc.setId(IPaletteContributor.KEY_COMMON_TOOLS);
		pgc.setName(Messages.common_tools);
		pgc.setImage("icons/resources/fields-16.png"); //$NON-NLS-1$
		return pgc;
	}

	/**
	 * Creates a new JDPalette object.
	 * 
	 * @return the palette root
	 */
	public static PaletteRoot createPalette(List<String> ignore, JasperReportsConfiguration jrConfig) {
		if (ignore == null)
			ignore = new ArrayList<String>();
		PaletteRoot paletteRoot = new PaletteRoot();

		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		List<PaletteGroup> pgroups = m.getPaletteGroups();
		Map<String, PaletteGroup> map = new TreeMap<String, PaletteGroup>();

		PaletteGroup baseElementsGroup = getBaseElementsGroup();
		map.put(baseElementsGroup.getId(), baseElementsGroup);

		PaletteGroup otherElementsGroup = getOtherElementsGroup();
		map.put(otherElementsGroup.getId(), otherElementsGroup);

		for (PaletteGroup p : pgroups) {
			map.put(p.getId(), p);
		}

		Map<String, List<PaletteEntry>> mapEntry = m.getPaletteEntries();
		for (String key : mapEntry.keySet()) {
			if (!key.isEmpty() && map.get(key) == null) {
				PaletteGroup pgc = new PaletteGroup();
				pgc.setId(key);
				pgc.setName(Messages.JDPaletteFactory_unknown_group);
				pgc.setImage(""); //$NON-NLS-1$
				map.put(pgc.getId(), pgc);
			}

			for (PaletteEntry pe : mapEntry.get(key)) {
				String id = PalettePreferencePage.getId(pe);
				if (ignore.contains(pe.getId()))
					continue;
				if (jrConfig.getPropertyBooleanDef(id, false) && pe instanceof CombinedTemplateCreationEntry)
					ignore.add(((CombinedTemplateCreationEntry) pe).getTemplate().toString());
			}
		}

		pgroups = new ArrayList<PaletteGroup>(map.values());
		List<PaletteGroup> ordpgrps = new ArrayList<PaletteGroup>(pgroups);

		for (PaletteGroup p : pgroups) {
			if (p.getAfterGroup() != null && !p.getAfterGroup().trim().isEmpty() && !p.getId().equals(p.getAfterGroup())) {
				PaletteGroup opbefore = null;
				for (PaletteGroup op : ordpgrps) {
					if (op.getId().equals(p.getAfterGroup())) {
						opbefore = op;
						break;
					}
				}
				ordpgrps.remove(p);
				int ind = -1;
				if (opbefore != null)
					ind = ordpgrps.indexOf(opbefore) + 1;
				if (ind >= 0 && ind < ordpgrps.size())
					ordpgrps.add(ind, p);
				else
					ordpgrps.add(p);
			}
		}

		CompositeElementHandler handler = new CompositeElementHandler(paletteRoot);
		PalettePrenferencesListener hiddenElementsHandler = new PalettePrenferencesListener();
		for (PaletteGroup p : ordpgrps) {
			if (p.getId().equals(IPaletteContributor.KEY_COMMON_ELEMENTS)) {
				PaletteDrawer drawer = createElements(paletteRoot, ignore, p, mapEntry);
				getEntries4Key(drawer, ignore, "", mapEntry); //$NON-NLS-1$
				handler.addPaletteGroup(p.getId(), drawer);
				hiddenElementsHandler.addDrawer(drawer);
			} else if (p.getId().equals(IPaletteContributor.KEY_COMMON_CONTAINER)) {
				PaletteDrawer drawer = createGroup(paletteRoot, ignore, p.getName(), p.getImage());
				createContainers(drawer, ignore, p, mapEntry);
				handler.addPaletteGroup(p.getId(), drawer);
				hiddenElementsHandler.addDrawer(drawer);
			} else if (p.getId().equals(IPaletteContributor.KEY_COMMON_TOOLS)) {
				PaletteDrawer drawer = createGroup(paletteRoot, ignore, p.getName(), p.getImage());
				createOtherElements(drawer, ignore, p, mapEntry);
				handler.addPaletteGroup(p.getId(), drawer);
				hiddenElementsHandler.addDrawer(drawer);
			} else {
				PaletteDrawer drawer = createGroup(paletteRoot, ignore, p.getName(), p.getImage());
				getEntries4Key(drawer, ignore, p.getId(), mapEntry);
				handler.addPaletteGroup(p.getId(), drawer);
				hiddenElementsHandler.addDrawer(drawer);
			}
		}
		// create the current composite elements entry
		handler.createAllCompositeElements();
		// Add the modify listener to the tools manager to get notified when the composite elements
		// changes
		CompositeElementManager.INSTANCE.addModifyListener(handler);
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(hiddenElementsHandler);
		return paletteRoot;
	}

	/**
	 * Create simple palette, with only a subset of the available elements
	 * 
	 * @return the palette root
	 */
	public static PaletteRoot createSimplePalette(List<String> ignore, JasperReportsConfiguration jrConfig) {
		if (ignore == null)
			ignore = new ArrayList<String>();
		PaletteRoot paletteRoot = new PaletteRoot();

		CompositeElementHandler handler = new CompositeElementHandler(paletteRoot);

		PaletteGroup baseGroup = getBaseElementsGroup();

		PaletteDrawer drawer = createGroup(paletteRoot, ignore, baseGroup.getName(), baseGroup.getImage());
		drawer.add(createJDEntry(MCallout.getIconDescriptor(), MCallout.class));
		drawer.add(createJDEntry(MTextField.getIconDescriptor(), MTextField.class));
		drawer.add(createJDEntry(MStaticText.getIconDescriptor(), MStaticText.class));
		drawer.add(createJDEntry(MImage.getIconDescriptor(), MImage.class));
		drawer.add(createJDEntry(MBreak.getIconDescriptor(), MBreak.class));
		drawer.add(createJDEntry(MRectangle.getIconDescriptor(), MRectangle.class));
		drawer.add(createJDEntry(MEllipse.getIconDescriptor(), MEllipse.class));
		drawer.add(createJDEntry(MLine.getIconDescriptor(), MLine.class));
		drawer.add(createJDEntry(MGenericElement.getIconDescriptor(), MGenericElement.class));
		drawer.add(createJDEntry(MFrame.getIconDescriptor(), MFrame.class));
		handler.addPaletteGroup(baseGroup.getId(), drawer);

		PaletteGroup otherGroup = getOtherElementsGroup();
		drawer = createGroup(paletteRoot, ignore, otherGroup.getName(), otherGroup.getImage());
		drawer.add(createJDEntry(MPageNumber.getIconDescriptor(), MPageNumber.class));
		drawer.add(createJDEntry(MTotalPages.getIconDescriptor(), MTotalPages.class));
		drawer.add(createJDEntry(MDate.getIconDescriptor(), MDate.class));
		drawer.add(createJDEntry(MTime.getIconDescriptor(), MTime.class));
		drawer.add(createJDEntry(MPercentage.getIconDescriptor(), MPercentage.class));
		drawer.add(createJDEntry(MPageXofY.getIconDescriptor(), MPageXofY.class));
		handler.addPaletteGroup(otherGroup.getId(), drawer);

		// create the current composite elements entry
		handler.createAllCompositeElements();
		// Add the modify listener to the tools manager to get notified when the composite elements
		// changes
		CompositeElementManager.INSTANCE.addModifyListener(handler);

		return paletteRoot;
	}

	/**
	 * Creates a new JDPalette object.
	 * 
	 * @param iconDescriptor
	 *          the icon descriptor
	 * @param aclass
	 *          the aclass
	 * @return the palette entry
	 */
	public static PaletteEntry createJDEntry(IIconDescriptor iconDescriptor, Class<?> aclass) {
		CombinedTemplateCreationEntry paletteEntry = new CombinedTemplateCreationEntry(iconDescriptor.getTitle(),
				iconDescriptor.getDescription(), aclass, new JDPaletteCreationFactory(aclass), iconDescriptor.getIcon16(),
				iconDescriptor.getIcon32());
		// Override default CreationTool class with ours
		paletteEntry.setToolClass(JDCreationTool.class);
		return paletteEntry;
	}

	public static PaletteDrawer createElements(PaletteRoot paletteRoot, List<String> ignore, PaletteGroup p,
			Map<String, List<PaletteEntry>> map) {
		PaletteDrawer drawer = createGroup(paletteRoot, ignore, p.getName(), p.getImage());

		drawer.add(createJDEntry(MCallout.getIconDescriptor(), MCallout.class));

		drawer.add(createJDEntry(MTextField.getIconDescriptor(), MTextField.class));
		drawer.add(createJDEntry(MStaticText.getIconDescriptor(), MStaticText.class));
		drawer.add(createJDEntry(MImage.getIconDescriptor(), MImage.class));
		drawer.add(createJDEntry(MBreak.getIconDescriptor(), MBreak.class));
		drawer.add(createJDEntry(MRectangle.getIconDescriptor(), MRectangle.class));
		drawer.add(createJDEntry(MEllipse.getIconDescriptor(), MEllipse.class));
		drawer.add(createJDEntry(MLine.getIconDescriptor(), MLine.class));
		drawer.add(createJDEntry(MGenericElement.getIconDescriptor(), MGenericElement.class));

		drawer.add(createJDEntry(MFrame.getIconDescriptor(), MFrame.class));
		drawer.add(createJDEntry(MSubreport.getIconDescriptor(), MSubreport.class));

		getEntries4Key(drawer, ignore, p.getId(), map);
		return drawer;
	}

	public static PaletteDrawer createContainers(PaletteDrawer drawer, List<String> ignore, PaletteGroup p,
			Map<String, List<PaletteEntry>> map) {

		drawer.add(createJDEntry(MFrame.getIconDescriptor(), MFrame.class));
		drawer.add(createJDEntry(MSubreport.getIconDescriptor(), MSubreport.class));

		getEntries4Key(drawer, ignore, p.getId(), map);
		return drawer;
	}

	/**
	 * Creates a new JDPalette object.
	 * 
	 * @param paletteRoot
	 *          the palette root
	 */
	public static void createOtherElements(PaletteDrawer drawer, List<String> ignore, PaletteGroup p,
			Map<String, List<PaletteEntry>> map) {
		drawer.add(createJDEntry(MPageNumber.getIconDescriptor(), MPageNumber.class));
		drawer.add(createJDEntry(MTotalPages.getIconDescriptor(), MTotalPages.class));
		drawer.add(createJDEntry(MDate.getIconDescriptor(), MDate.class));
		drawer.add(createJDEntry(MTime.getIconDescriptor(), MTime.class));
		drawer.add(createJDEntry(MPercentage.getIconDescriptor(), MPercentage.class));
		drawer.add(createJDEntry(MPageXofY.getIconDescriptor(), MPageXofY.class));
		getEntries4Key(drawer, ignore, p.getId(), map);
	}

	public static PaletteDrawer createGroup(PaletteRoot paletteRoot, List<String> ignore, String name,
			ImageDescriptor imageDescriptor) {
		PaletteDrawer drawer = new PaletteDrawer(name, imageDescriptor);
		paletteRoot.add(drawer);
		return drawer;
	}

	private static void getEntries4Key(PaletteDrawer drawer, List<String> ignore, String id,
			Map<String, List<PaletteEntry>> map) {
		List<PaletteEntry> plist = map.get(id);
		if (plist != null)
			for (PaletteEntry entry : plist) {
				if (ignore != null && entry instanceof CombinedTemplateCreationEntry
						&& ignore.contains(((CombinedTemplateCreationEntry) entry).getTemplate().toString())) {
					entry.setVisible(false);
				}
				drawer.add(entry);
			}
	}
}
