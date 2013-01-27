/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA 02110-1301, USA.
 *
 * For information about the authors of this project Have a look
 * at the AUTHORS file in the root of this project.
 */
package net.sourceforge.fullsync.ui;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

public class ImportProfilesComposite {
	private Text textPath;
	private Button buttonBrowse;

	public ImportProfilesComposite(final Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		textPath = new Text(parent, SWT.BORDER);
		GridData textData = new GridData();
		textData.horizontalAlignment = SWT.FILL;
		textData.grabExcessHorizontalSpace = true;
		textData.grabExcessVerticalSpace = true;
		textData.verticalAlignment = SWT.CENTER;
		textPath.setLayoutData(textData);
		buttonBrowse = new Button(parent, SWT.NONE);
		buttonBrowse.setText("...");
		buttonBrowse.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				FileDialog fd = new FileDialog(parent.getShell());
				fd.setFileName("profiles.xml"); //$NON-NLS-1$
				fd.setFilterExtensions(new String[] {
					"profiles.xml", //$NON-NLS-1$
					"*.xml", //$NON-NLS-1$
					"*" //$NON-NLS-1$
				});
				fd.setFilterIndex(0);
				fd.setFilterPath(textPath.getText());
				String file = fd.open();
				if (file != null) {
					File f = new File(file);
					try {
						textPath.setText(f.getCanonicalPath());
					} catch (IOException e) {
						textPath.setText(""); //$NON-NLS-1$
						e.printStackTrace();
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent evt) {
			}
		});
	}

	public String getSelectedFilename() {
		return textPath.getText();
	}
}
