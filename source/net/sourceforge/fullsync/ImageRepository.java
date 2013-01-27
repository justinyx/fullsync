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

import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * @author <a href="mailto:codewright@gmx.net">Jan Kopcsek</a>
 */
public class ImageRepository {
	/*
	 * TODO for now we'll create/dispose mixed pictures in place, but
	 * it might be interesting to build some global caching mechanism
	 * for all mixes which is still pretty fast (say int -> image)
	 * public static class Overlay
	 * {
	 * public final static int PositionBeginning = 0;
	 * public final static int PositionCenter = 1;
	 * public final static int PositionEnd = 2;
	 *
	 * private Image image;
	 * private int positionX;
	 * private int positionY;
	 *
	 * public Overlay( Image image )
	 * {
	 * this( image, PositionBeginning, PositionBeginning );
	 * }
	 * public Overlay( Image image, int positionX, int positionY )
	 * {
	 * this.image = image;
	 * this.positionX = PositionBeginning;
	 * this.positionY = PositionBeginning;
	 * }
	 * public Image getImage()
	 * {
	 * return image;
	 * }
	 * public int getPositionX()
	 * {
	 * return positionX;
	 * }
	 * public int getPositionY()
	 * {
	 * return positionY;
	 * }
	 * }
	 */

	private Display display;
	private Hashtable<String, Image> cache = new Hashtable<String, Image>();

	public ImageRepository(Display display) {
		this.display = display;
		this.cache = new Hashtable<String, Image>();
	}

	public Image getImage(String imageName) {
		Object obj = cache.get(imageName);
		if (obj == null) {
			Image image = new Image(display, "images/" + imageName);
			cache.put(imageName, image);
			obj = image;
		}
		return (Image) obj;
	}

	public void removeImage(String imageName) {
		Object obj = cache.remove(imageName);
		if (obj != null) {
			((Image) obj).dispose();
		}
	}

	public void dispose() {
		Enumeration<Image> en = cache.elements();
		while (en.hasMoreElements()) {
			Image i = en.nextElement();
			if (!i.isDisposed()) {
				i.dispose();
			}
		}
		cache.clear();
	}
}
