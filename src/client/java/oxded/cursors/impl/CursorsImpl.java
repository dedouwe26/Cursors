package oxded.cursors.impl;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.Window;
import oxded.cursors.api.Cursor;
import oxded.cursors.api.CursorProvider;
import oxded.cursors.api.CursorType;

@Environment(EnvType.CLIENT)
public final class CursorsImpl {
	private static Window window;
    private static CursorType cursorType;
    private static CursorProvider provider;

    public static void init(Window win, CursorProvider provider) {
        window = win;
        setCursorProvider(provider);
    }
    public static Window getWindow() {
        return window;
    }
    public static void setCursorType(CursorType type) {
        cursorType = type;
        setCursor(provider.getCursor(type));
    }
    public static CursorType getCursorType() {
        return cursorType;
    }
    public static void setCursor(Cursor cursor) {
        if (cursor instanceof CursorImpl impl) {
            GLFW.glfwSetCursor(window.getHandle(), impl.createGlfwCursor());
        }
    }
    public static void setCursorProvider(CursorProvider provider) {

    }
}
