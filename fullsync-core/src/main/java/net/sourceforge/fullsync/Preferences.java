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
package net.sourceforge.fullsync;

public interface Preferences {
	void save();

	boolean confirmExit();

	void setConfirmExit(boolean bool);

	boolean closeMinimizesToSystemTray();

	void setCloseMinimizesToSystemTray(boolean bool);

	boolean minimizeMinimizesToSystemTray();

	void setMinimizeMinimizesToSystemTray(boolean bool);

	String getProfileListStyle();

	void setProfileListStyle(String profileListStyle);

	boolean getAutostartScheduler();

	void setAutostartScheduler(boolean bool);

	String getLanguageCode();

	void setLanguageCode(String code);

	boolean getHelpShown();

	void setHelpShown(boolean shown);

	boolean getSkipWelcomeScreen();

	void setSkipWelcomeScreen(boolean skip);

	String getLastVersion();

	WindowState getWindowState(String name);

	void setWindowState(String name, WindowState state);
}
