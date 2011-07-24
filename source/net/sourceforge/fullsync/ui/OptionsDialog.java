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

import net.sourceforge.fullsync.ExceptionHandler;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * This code was generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * *************************************
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
 * for this machine, so Jigloo or this code cannot be used legally
 * for any corporate or commercial purpose.
 * *************************************
 */
public class OptionsDialog extends Dialog implements SelectionListener {
	private Composite compositeButtons;
	private Label labelImage;
	private Label labelMessage;
	private Shell dialogShell;

	private String message;
	private String[] options;
	private String result;

	public OptionsDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Opens the Dialog Shell.
	 * Auto-generated code - any changes you make will disappear.
	 */
	public String open() {
		try {
			result = null;

			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			dialogShell.setText(getText());
			labelImage = new Label(dialogShell, SWT.NULL);
			labelMessage = new Label(dialogShell, SWT.NULL);
			compositeButtons = new Composite(dialogShell, SWT.NULL);

			dialogShell.setSize(new Point(418, 139));

			Image i = parent.getDisplay().getSystemImage(getStyle());
			labelImage.setImage(i);

			GridData labelMessageLData = new GridData();
			labelMessageLData.verticalAlignment = GridData.CENTER;
			labelMessageLData.horizontalAlignment = GridData.FILL;
			labelMessageLData.widthHint = -1;
			labelMessageLData.heightHint = -1;
			labelMessageLData.horizontalIndent = 0;
			labelMessageLData.horizontalSpan = 1;
			labelMessageLData.verticalSpan = 1;
			labelMessageLData.grabExcessHorizontalSpace = true;
			labelMessageLData.grabExcessVerticalSpace = false;
			labelMessage.setLayoutData(labelMessageLData);
			labelMessage.setText(getMessage());

			GridData compositeButtonsLData = new GridData();
			compositeButtonsLData.verticalAlignment = GridData.CENTER;
			compositeButtonsLData.horizontalAlignment = GridData.CENTER;
			compositeButtonsLData.widthHint = -1;
			compositeButtonsLData.heightHint = -1;
			compositeButtonsLData.horizontalIndent = 0;
			compositeButtonsLData.horizontalSpan = 2;
			compositeButtonsLData.verticalSpan = 1;
			compositeButtonsLData.grabExcessHorizontalSpace = true;
			compositeButtonsLData.grabExcessVerticalSpace = false;
			compositeButtons.setLayoutData(compositeButtonsLData);
			RowLayout compositeButtonsLayout = new RowLayout(256);
			compositeButtons.setLayout(compositeButtonsLayout);
			compositeButtonsLayout.type = SWT.HORIZONTAL;
			compositeButtonsLayout.marginWidth = 0;
			compositeButtonsLayout.marginHeight = 0;
			compositeButtonsLayout.spacing = 3;
			compositeButtonsLayout.wrap = true;
			compositeButtonsLayout.pack = true;
			compositeButtonsLayout.fill = false;
			compositeButtonsLayout.justify = false;
			compositeButtonsLayout.marginLeft = 3;
			compositeButtonsLayout.marginTop = 3;
			compositeButtonsLayout.marginRight = 3;
			compositeButtonsLayout.marginBottom = 3;
			for (String option : options) {
				Button b = new Button(compositeButtons, SWT.PUSH);
				b.setText(option);
				b.addSelectionListener(this);
			}
			compositeButtons.layout();
			GridLayout dialogShellLayout = new GridLayout(2, true);
			dialogShell.setLayout(dialogShellLayout);
			dialogShellLayout.marginWidth = 14;
			dialogShellLayout.marginHeight = 14;
			dialogShellLayout.numColumns = 2;
			dialogShellLayout.makeColumnsEqualWidth = false;
			dialogShellLayout.horizontalSpacing = 14;
			dialogShellLayout.verticalSpacing = 14;
			dialogShell.layout();
			dialogShell.pack();
			dialogShell.open();
			Display display = dialogShell.getDisplay();
			while (!dialogShell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}
		catch (Exception e) {
			ExceptionHandler.reportException(e);
		}
		return result;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setOptions(String[] newOptions) {
		this.options = newOptions;
	}

	public String[] getOptions() {
		return options;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		result = options[0];
		dialogShell.close();
		dialogShell.dispose();
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		result = ((Button) arg0.widget).getText();
		dialogShell.close();
		dialogShell.dispose();
	}
}
