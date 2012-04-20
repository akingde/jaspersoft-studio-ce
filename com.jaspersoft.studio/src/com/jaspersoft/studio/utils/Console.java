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
package com.jaspersoft.studio.utils;

import net.sf.jasperreports.eclipse.util.xml.SourceLocation;

import org.eclipse.jdt.core.compiler.CategorizedProblem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import com.jaspersoft.studio.editor.preview.view.control.VErrorPreview;

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
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(myConsole);
		return new Console(myConsole);
	}

	private MessageConsole console;
	private VErrorPreview errorPreview;

	private Console(MessageConsole console) {
		this.console = console;
	}

	public void setErrorPreview(VErrorPreview errorPreview) {
		this.errorPreview = errorPreview;
	}

	public static Color REDCOLOR = Display.getDefault().getSystemColor(SWT.COLOR_RED);

	public void addError(final Throwable e) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				MessageConsoleStream out = console.newMessageStream();
				Color color = out.getColor();
				out.setColor(color);
				out.println(ErrorUtil.getStackTrace(e) + "\n\r");
				out.setColor(REDCOLOR);
				if (errorPreview != null)
					errorPreview.addError(e);
			}
		});
	}

	public void addMessage(final String message) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				MessageConsoleStream out = console.newMessageStream();
				out.println(message);
				if (errorPreview != null)
					errorPreview.addMessage(message);
			}
		});
	}

	public void addProblem(final CategorizedProblem problem, final SourceLocation location) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				errorPreview.addProblem(problem, location);
			}
		});
	}

	public void addProblem(final String message, final SourceLocation location) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				errorPreview.addProblem(message, location);
			}
		});
	}

	public void showConsole() {
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
	}

	public void clearConsole() {
		console.clearConsole();
		if (errorPreview != null)
			errorPreview.clear();
	}
}
