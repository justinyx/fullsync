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
package net.sourceforge.fullsync.fs.filesystems;

import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;

import net.sourceforge.fullsync.ConnectionDescription;
import net.sourceforge.fullsync.FileSystemException;
import net.sourceforge.fullsync.FullSync;
import net.sourceforge.fullsync.fs.FileSystem;
import net.sourceforge.fullsync.fs.Site;
import net.sourceforge.fullsync.fs.connection.CommonsVfsConnection;

public class SmbFileSystem implements FileSystem {
	static {
		// even tough VFS-552 is fixed this si still needed
		//[VFS-552][sandbox] include vfs-providers.xml in JAR for dynamic registration of mime and smb providers.
		try {
			FileSystemManager fsm = VFS.getManager();
			if (!fsm.hasProvider("smb") && (fsm instanceof DefaultFileSystemManager)) {
				DefaultFileSystemManager dfsm = (DefaultFileSystemManager) fsm;
				dfsm.addProvider("smb", new org.apache.commons.vfs2.provider.smb.SmbFileProvider());
			}
		}
		catch (org.apache.commons.vfs2.FileSystemException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final Site createConnection(final FullSync fullsync, final ConnectionDescription description) throws FileSystemException {
		return new CommonsVfsConnection(description, new SmbAuthProvider());
	}
}