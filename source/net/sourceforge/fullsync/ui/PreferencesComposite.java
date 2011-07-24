/**
 * @license
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 *
 * For information about the authors of this project Have a look
 * at the AUTHORS file in the root of this project.
 */
package net.sourceforge.fullsync.ui;

import java.rmi.RemoteException;
import java.util.Arrays;

import net.sourceforge.fullsync.ExceptionHandler;
import net.sourceforge.fullsync.Preferences;
import net.sourceforge.fullsync.remote.RemoteController;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
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
public class PreferencesComposite extends org.eclipse.swt.widgets.Composite {

	/**
	 * supported language codes.
	 */
	private static String[] languageCodes = { "en", "it", "de", "fr", "es", "ar" };
	/**
	 * supported language names.
	 */
	private static String[] languageNames = { "English", "Italiano", "Deutsch", "Français", "Español", "Arabic" };

	/**
	 * search an element in an array and get the result from another array at the same index.
	 * @param in array to search in
	 * @param result array to take the result from
	 * @param key key to search
	 * @return the element in the result array on the same index as the key in the in array
	 */
	private static String arraySearch(final String[] in, final String[] result, final String key) {
		int i = 0;
		for (String s : in) {
			if (s.equals(key)) {
				return result[i];
			}
			++i;
		}
		return "";
	}

	/**
	 * map language name to code.
	 * @param name language name
	 * @return language code
	 */
	private static String getLanguageCode(final String name) {
		return arraySearch(languageNames, languageCodes, name);
	}

	/**
	 * map language code to name.
	 * @param code language code
	 * @return language name
	 */
	private static String getLanguageName(final String code) {
		return arraySearch(languageCodes, languageNames, code);
	}

	private Group groupInterface;
	private Button cbConfirmExit;
	private Button cbCloseMinimizesToSystemTray;
	private Button cbMinimizeMinimizesToSystemTray;
	private Label label5;
	private Combo comboLanguage;
	private Label label4;
	private Button cbAutostartScheduler;
	private Text textPassword;
	private Label label3;
	private Group groupRemoteConnection;
	private Text textListeningPort;
	private Label label2;
	private Button cbListenForIncomming;
	private Label label1;
	private Combo comboProfileList;
	//private Button cbEnableSystemTray;

	private Preferences preferences;

	public PreferencesComposite(Composite parent, int style, Preferences preferences)
	{
		super(parent, style);
		this.preferences = preferences;
		initGui();
	}

	public void initGui() {
		try {
			GridLayout thisLayout = new GridLayout();
			thisLayout.numColumns = 2;
			thisLayout.horizontalSpacing = 20;
			thisLayout.makeColumnsEqualWidth = true;
			this.setLayout(thisLayout);
			this.setSize(500, 350);
            {
                groupInterface = new Group(this, SWT.NONE);
                GridLayout GeneralPreferencesGroupLayout = new GridLayout();
                GeneralPreferencesGroupLayout.numColumns = 3;
                GridData GeneralPreferencesGroupLData = new GridData();
                GeneralPreferencesGroupLData.horizontalSpan = 2;
                GeneralPreferencesGroupLData.grabExcessHorizontalSpace = true;
                GeneralPreferencesGroupLData.grabExcessVerticalSpace = true;
                GeneralPreferencesGroupLData.horizontalAlignment = GridData.FILL;
                GeneralPreferencesGroupLData.verticalAlignment = GridData.FILL;
                groupInterface.setLayoutData(GeneralPreferencesGroupLData);
                groupInterface.setLayout(GeneralPreferencesGroupLayout);
                groupInterface.setText(Messages.getString("PreferencesComposite.Interface")); //$NON-NLS-1$
                {
                    cbConfirmExit = new Button(groupInterface, SWT.CHECK
                        | SWT.LEFT);
                    cbConfirmExit.setText(Messages.getString("PreferencesComposite.ConfirmExit")); //$NON-NLS-1$
                    GridData askOnClosingCheckBoxLData = new GridData();
                    askOnClosingCheckBoxLData.widthHint = 246;
                    askOnClosingCheckBoxLData.heightHint = 19;
                    askOnClosingCheckBoxLData.horizontalSpan = 3;
                    cbConfirmExit.setLayoutData(askOnClosingCheckBoxLData);
                }
                {
                    cbCloseMinimizesToSystemTray = new Button(
                        groupInterface,
                        SWT.CHECK | SWT.LEFT);
                    cbCloseMinimizesToSystemTray
                        .setText(Messages.getString("PreferencesComposite.CloseMinimizes")); //$NON-NLS-1$
                    GridData closeButtonMinimizesCheckBoxLData = new GridData();
                    closeButtonMinimizesCheckBoxLData.widthHint = 246;
                    closeButtonMinimizesCheckBoxLData.heightHint = 18;
                    closeButtonMinimizesCheckBoxLData.horizontalSpan = 3;
                    cbCloseMinimizesToSystemTray.setLayoutData(closeButtonMinimizesCheckBoxLData);
                }
                {
                    cbMinimizeMinimizesToSystemTray = new Button(
                        groupInterface,
                        SWT.CHECK | SWT.LEFT);
                    cbMinimizeMinimizesToSystemTray
                        .setText(Messages.getString("PreferencesComposite.MinimizeMinimizes")); //$NON-NLS-1$
                    GridData cbMinimizeMinimizesToSystemTrayLData = new GridData();
                    cbMinimizeMinimizesToSystemTrayLData.widthHint = 246;
                    cbMinimizeMinimizesToSystemTrayLData.heightHint = 18;
                    cbMinimizeMinimizesToSystemTrayLData.horizontalSpan = 3;
                    cbMinimizeMinimizesToSystemTray.setLayoutData(cbMinimizeMinimizesToSystemTrayLData);
                }
                {
                    cbAutostartScheduler = new Button(
                        groupInterface,
                        SWT.CHECK | SWT.LEFT);
                    cbAutostartScheduler.setText(Messages.getString("PreferencesComposite.AutostartScheduler")); //$NON-NLS-1$
                    GridData cbAutostartSchedulerLData = new GridData();
                    cbAutostartSchedulerLData.horizontalSpan = 3;
                    cbAutostartSchedulerLData.widthHint = 171;
                    cbAutostartSchedulerLData.heightHint = 16;
                    cbAutostartScheduler.setLayoutData(cbAutostartSchedulerLData);
                }
                /*
                 {
                 cbEnableSystemTray = new Button(groupInterface, SWT.CHECK | SWT.LEFT);
                 cbEnableSystemTray.setText("Enable System Tray Icon");
                 GridData cbEnableSystemTrayLData = new GridData();
                 cbEnableSystemTrayLData.widthHint = 188;
                 cbEnableSystemTrayLData.heightHint = 18;
                 cbEnableSystemTrayLData.horizontalSpan = 2;
                 cbEnableSystemTray.setLayoutData(cbEnableSystemTrayLData);
                 }
                 */
                {
                    label1 = new Label(groupInterface, SWT.NONE);
                    label1.setText(Messages.getString("PreferencesComposite.ProfileListStyle")+": "); //$NON-NLS-1$ //$NON-NLS-2$
                    GridData label1LData = new GridData();
                    label1LData.heightHint = 15;
                    label1.setLayoutData(label1LData);
                }
                {
                    comboProfileList = new Combo(groupInterface, SWT.DROP_DOWN
                        | SWT.READ_ONLY);
                    GridData comboProfileListLData = new GridData();
                    comboProfileListLData.widthHint = 105;
                    comboProfileListLData.heightHint = 21;
                    comboProfileListLData.horizontalSpan = 2;
                    comboProfileList.setLayoutData(comboProfileListLData);
                    comboProfileList.add(Messages.getString("PreferencesComposite.Table")); //$NON-NLS-1$
                    comboProfileList.add(Messages.getString("PreferencesComposite.NiceListView")); //$NON-NLS-1$
                }
				{
					label4 = new Label(groupInterface, SWT.NONE);
					label4.setText(Messages.getString("PreferencesComposite.Language")+":"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				{
					comboLanguage = new Combo(groupInterface, SWT.DROP_DOWN | SWT.READ_ONLY);
					GridData comboLanguageLData = new GridData();
					comboLanguageLData.widthHint = 97;
					comboLanguageLData.heightHint = 21;
					comboLanguage.setLayoutData(comboLanguageLData);
					String[] languages = new String[languageNames.length];
					System.arraycopy(languageNames, 0, languages, 0, languageNames.length);
					Arrays.sort(languages);
					for (String language : languages) {
						comboLanguage.add(language);
					}
				}
				{
					label5 = new Label(groupInterface, SWT.NONE);
					label5.setText(Messages.getString("PreferencesComposite.NeedsRestart")); //$NON-NLS-1$
				}
            }
            {
                groupRemoteConnection = new Group(this, SWT.NONE);
                GridLayout groupRemoteConnectionLayout = new GridLayout();
                GridData groupRemoteConnectionLData = new GridData();
                groupRemoteConnectionLData.horizontalSpan = 2;
                groupRemoteConnectionLData.horizontalAlignment = GridData.FILL;
                groupRemoteConnectionLData.grabExcessVerticalSpace = true;
                groupRemoteConnectionLData.verticalAlignment = GridData.FILL;
                groupRemoteConnection.setLayoutData(groupRemoteConnectionLData);
                groupRemoteConnectionLayout.numColumns = 2;
                groupRemoteConnection.setLayout(groupRemoteConnectionLayout);
                groupRemoteConnection.setText(Messages.getString("PreferencesComposite.RemoteConnection")); //$NON-NLS-1$
                {
                    cbListenForIncomming = new Button(
                        groupRemoteConnection,
                        SWT.CHECK | SWT.LEFT);
                    cbListenForIncomming.setText(Messages.getString("PreferencesComposite.EnableRemoteConnections")); //$NON-NLS-1$
                    GridData cbListenForIncommingLData = new GridData();
                    cbListenForIncomming
                        .addSelectionListener(new SelectionAdapter() {
                            @Override
							public void widgetSelected(SelectionEvent evt) {
                                updateRemoteConnectionGroup();
                            }
                        });
                    cbListenForIncommingLData.horizontalSpan = 2;
                    cbListenForIncomming
                        .setLayoutData(cbListenForIncommingLData);
                }
                {
                    label2 = new Label(groupRemoteConnection, SWT.NONE);
                    label2.setText(Messages.getString("PreferencesComposite.IncomingPort")+":"); //$NON-NLS-1$ //$NON-NLS-2$
                    label2.setEnabled(false);
                }
                {
                    textListeningPort = new Text(
                        groupRemoteConnection,
                        SWT.BORDER);
                    textListeningPort.setText("10000"); //$NON-NLS-1$
                    GridData textListeningPortLData = new GridData();
                    textListeningPort.setEnabled(false);
                    textListeningPortLData.widthHint = 39;
                    textListeningPortLData.heightHint = 12;
                    textListeningPort.setLayoutData(textListeningPortLData);
                }
                {
                    label3 = new Label(groupRemoteConnection, SWT.NONE);
                    label3.setText(Messages.getString("PreferencesComposite.IncomingPassword")+":"); //$NON-NLS-1$ //$NON-NLS-2$
                    label3.setEnabled(false);
                }
                {
                    textPassword = new Text(groupRemoteConnection, SWT.BORDER);
                    GridData textPasswordLData = new GridData();
                    textPassword.setEnabled(false);
                    textPasswordLData.widthHint = 118;
                    textPasswordLData.heightHint = 13;
                    textPasswordLData.grabExcessHorizontalSpace = true;
                    textPassword.setLayoutData(textPasswordLData);
                }
            }

			updateComponent();
			this.layout();
		} catch (Exception e) {
			ExceptionHandler.reportException( e );
		}
	}

	public void updateComponent()
	{
		textPassword.setEchoChar('*');

		cbConfirmExit.setSelection(preferences.confirmExit());
		cbCloseMinimizesToSystemTray.setSelection(preferences.closeMinimizesToSystemTray());
		cbMinimizeMinimizesToSystemTray.setSelection(preferences.minimizeMinimizesToSystemTray());
		//cbEnableSystemTray.setSelection(preferences.systemTrayEnabled());
		comboProfileList.setText( preferences.getProfileListStyle() );
		comboLanguage.setText(getLanguageName(preferences.getLanguageCode()));
		cbListenForIncomming.setSelection(preferences.listeningForRemoteConnections());
		textListeningPort.setText(String.valueOf(preferences.getRemoteConnectionsPort()));
		textPassword.setText(preferences.getRemoteConnectionsPassword());
		cbAutostartScheduler.setSelection(preferences.getAutostartScheduler());
		updateRemoteConnectionGroup();
	}

	private void updateRemoteConnectionGroup() {
		if (cbListenForIncomming.getSelection()) {
			label2.setEnabled(true);
			textListeningPort.setEnabled(true);
			label3.setEnabled(true);
			textPassword.setEnabled(true);
		}
		else {
			label2.setEnabled(false);
			textListeningPort.setEnabled(false);
			label3.setEnabled(false);
			textPassword.setEnabled(false);
		}

	}

	public void apply()
	{
		preferences.setConfirmExit(cbConfirmExit.getSelection());
		preferences.setCloseMinimizesToSystemTray(cbCloseMinimizesToSystemTray.getSelection());
		preferences.setMinimizeMinimizesToSystemTray(cbMinimizeMinimizesToSystemTray.getSelection());
		//preferences.setSystemTrayEnabled(cbEnableSystemTray.getSelection());
		boolean profileListStyleChanged = (!preferences.getProfileListStyle().equals(comboProfileList.getText()));
		preferences.setProfileListStyle(comboProfileList.getText());
		preferences.setLanguageCode(getLanguageCode(comboLanguage.getText()));
		preferences.setAutostartScheduler(cbAutostartScheduler.getSelection());

		if (profileListStyleChanged) {
			GuiController.getInstance().getMainWindow().createProfileList();
		}

		boolean listenForIncoming = cbListenForIncomming.getSelection();
		preferences.setListeningForRemoteConnections(listenForIncoming);
		int port = -1;
		String password = null;

		if (listenForIncoming) {
			try {
				port = Integer.parseInt(textListeningPort.getText());
			} catch (NumberFormatException e) {
				ExceptionHandler.reportException( e );
			}

			preferences.setRemoteConnectionsPort(port);

			password = textPassword.getText();
			preferences.setRemoteConnectionsPassword(password);

			if (RemoteController.getInstance().isActive()) {
				int oldPort = RemoteController.getInstance().getPort();

				RemoteController.getInstance().setPassword(password);

				if (oldPort != port) {
					MessageBox mb = new MessageBox(getShell(), SWT.ICON_WARNING | SWT.OK);
					mb.setText(Messages.getString("PreferencesComposite.Warning")); //$NON-NLS-1$
					mb.setMessage(Messages.getString("PreferencesComposite.RequiresRestart")); //$NON-NLS-1$
					mb.open();
				}

			}
			else {
				if (port > 0) {
					try {
						RemoteController.getInstance().startServer(port, password,
								GuiController.getInstance().getProfileManager(),
								GuiController.getInstance().getSynchronizer());
					} catch (RemoteException e) {
						ExceptionHandler.reportException( e );
						MessageBox mb = new MessageBox(getShell(), SWT.ICON_ERROR | SWT.OK);
						mb.setText(Messages.getString("PreferencesComposite.ConnectionError")); //$NON-NLS-1$
						mb.setMessage(Messages.getString("PreferencesComposite.UnableToStart")+".\n("+e.getMessage()+")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						mb.open();
					}
				}
			}
		} else {
			try {
				RemoteController.getInstance().stopServer();
			} catch (RemoteException e) {
				ExceptionHandler.reportException( e );
				MessageBox mb = new MessageBox(getShell(), SWT.ICON_ERROR | SWT.OK);
				mb.setText(Messages.getString("PreferencesComposite.ConnectionError")); //$NON-NLS-1$
				mb.setMessage(Messages.getString("PreferencesComposite.UnableToStop")+".\n("+e.getMessage()+")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				mb.open();
			}
		}

		preferences.save();
	}
}
