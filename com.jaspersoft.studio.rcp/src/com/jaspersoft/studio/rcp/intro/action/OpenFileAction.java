package com.jaspersoft.studio.rcp.intro.action;

import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

public class OpenFileAction implements IIntroAction {

	public void run(IIntroSite site, Properties params) {
		String prj = (String) params.get("prj");
		String file = (String) params.get("file");
		// IFileStore fileStore;
		try {
			// fileStore = EFS.getLocalFileSystem().getStore(new URI(file));
			IWorkbenchPage page = site.getPage();

			IWorkspace ws = ResourcesPlugin.getWorkspace();
			IProject project = ws.getRoot().getProject(prj);

			IFile f = project.getFile(file);

			IEditorPart ep = IDE.openEditor(page, f, true);
			ep.setFocus();
			// fileStore.
			//
			// IFile f= new Path(file);
			//
			// IEditorPart ep = IDE.openEditor(page, f, true);

			// IEditorPart ep = IDE.openEditorOnFileStore(page, fileStore,
			// true);

			// page.activate(ep);
			// } catch (URISyntaxException e) {
			// e.printStackTrace();
			
			
		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}

}
