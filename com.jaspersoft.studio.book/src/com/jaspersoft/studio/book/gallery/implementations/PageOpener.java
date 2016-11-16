/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
public class PageOpener implements IElementOpener {

	/**
	 * Action on the contextual menu
	 */
	@Override
	public String getActionText() {
		return Messages.PageOpener_actionName;
	}

	/**
	 * Open a file dialog and allow to choose any number of jrxml files
	 * and create the gallery elements for them.
	 */
	@Override
	public IGalleryElement[] openResources() {
		FileDialog fd = new FileDialog(UIUtils.getShell(), SWT.OPEN | SWT.MULTI);
		fd.setText(Messages.PageOpener_dialogTitle);
		String[] filterExt = { "*.jrxml" }; //$NON-NLS-1$ 
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		List<IGalleryElement> elementsToCreate = new ArrayList<IGalleryElement>();
		if (selected != null) {
			String[] fileNames = fd.getFileNames();
			File parentFolder = new File(selected).getParentFile();
			for(String fileName : fileNames){
				File actualFile = new File(parentFolder, fileName);
				selected = actualFile.getAbsolutePath();
				if (actualFile.isFile()){
					PageElement selectedContainer = new PageElement(selected);
					elementsToCreate.add(selectedContainer);
				}
			}
		}
		return elementsToCreate.toArray(new IGalleryElement[elementsToCreate.size()]);
	}

}
