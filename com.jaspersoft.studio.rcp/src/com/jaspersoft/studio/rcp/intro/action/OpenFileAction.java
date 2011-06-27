package com.jaspersoft.studio.rcp.intro.action;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

public class OpenFileAction implements IIntroAction {

	public void run(IIntroSite site, Properties params) {
		String file = (String) params.get("file");
		IFileStore fileStore;
		try {
			fileStore = EFS.getLocalFileSystem().getStore(new URI(file));
			IWorkbenchPage page = site.getPage();

			IDE.openEditorOnFileStore(page, fileStore);

			page.zoomOut();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}

}
