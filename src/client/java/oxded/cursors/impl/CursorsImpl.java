package oxded.cursors.impl;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.Window;
import oxded.cursors.api.Cursor;
import oxded.cursors.api.CursorProvider;
import oxded.cursors.api.CursorType;
import oxded.cursors.api.WindowProvider;

@Environment(EnvType.CLIENT)
public final class CursorsImpl {
    private static CursorType cursorType;
    private static CursorProvider cursorProvider;
    private static WindowProvider windowProvider;

    public static void init(CursorProvider cursorProvider, WindowProvider windowProvider) {
        setCursorProvider(cursorProvider);
        setWindowProvider(windowProvider);
        setCursorType(CursorType.DEFAULT);
    }
    
    public static Window getWindow() {
        return windowProvider.getWindow();
    }
    public static void setCursorType(CursorType type) {
        cursorType = type;
        setCursor(cursorProvider.getCursor(type));
    }
    public static CursorType getCursorType() {
        return cursorType;
    }
    public static void setCursor(Cursor cursor) {
        if (cursor instanceof CursorImpl impl) {
            GLFW.glfwSetCursor(getWindow().getHandle(), impl.createGlfwCursor());
        }
    }
    public static void setCursorProvider(CursorProvider provider) {
        cursorProvider = provider;
    }
    private static void setWindowProvider(WindowProvider provider) {
        windowProvider = provider;
    }
}
