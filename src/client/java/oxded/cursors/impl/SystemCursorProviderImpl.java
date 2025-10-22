package oxded.cursors.impl;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import oxded.cursors.api.Cursor;
import oxded.cursors.api.CursorType;
import oxded.cursors.api.SystemCursorProvider;

@Environment(EnvType.CLIENT)
public class SystemCursorProviderImpl extends SystemCursorProvider {
	@Override
	public Cursor getCursor(CursorType type) {
		int shape = GLFW.GLFW_ARROW_CURSOR;
		switch (type) {
			case POINTER:
				shape = GLFW.GLFW_POINTING_HAND_CURSOR;
			case TEXT:
				shape = GLFW.GLFW_IBEAM_CURSOR;
			case BLOCKED:
				shape = GLFW.GLFW_NOT_ALLOWED_CURSOR;
			case DEFAULT:
			default:
				break;
		}
		return new CursorImpl(shape);
	}
	
}
