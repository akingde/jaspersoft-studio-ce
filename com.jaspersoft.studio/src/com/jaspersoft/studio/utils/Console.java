package com.jaspersoft.studio.utils;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class Console {
	public static MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		// no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}

	public static Console showConsole(String name) {
		MessageConsole myConsole = findConsole(name);
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();// obtain the active page
		String id = IConsoleConstants.ID_CONSOLE_VIEW;
		IConsoleView view;
		try {
			view = (IConsoleView) page.showView(id);
			view.display(myConsole);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return new Console(myConsole);
	}

	private MessageConsole console;

	private Console(MessageConsole console) {
		this.console = console;
	}

	public void addError(final Throwable e) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				MessageConsoleStream out = console.newMessageStream();
				out.println(e.getMessage() + "\n\r" + ErrorUtil.getStackTrace(e) + "\n\r");
			}
		});
	}

	public void addMessage(final String message) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				MessageConsoleStream out = console.newMessageStream();
				out.println(message);
			}
		});
	}

	public void showConsole() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();// obtain the active page
		String id = IConsoleConstants.ID_CONSOLE_VIEW;
		IConsoleView view;
		try {
			view = (IConsoleView) page.showView(id);
			view.display(console);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	public void clearConsole() {
		console.clearConsole();
	}
}
