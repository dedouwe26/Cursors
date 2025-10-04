package oxded.cursors.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import oxded.cursors.impl.CursorsImpl;

@Environment(EnvType.CLIENT)
public final class Cursors {
    public static void init(CursorProvider cursorProvider, WindowProvider windowProvider) {
        CursorsImpl.init(cursorProvider, windowProvider);
    }

    public static void setCursorType(CursorType type) {
        CursorsImpl.setCursorType(type);
    }
    public static CursorType getCursorType() {
        return CursorsImpl.getCursorType();
    }
    public static void setCursor(Cursor cursor) {
        CursorsImpl.setCursor(cursor);
    }
    public static void setCursorProvider(CursorProvider provider) {
        CursorsImpl.setCursorProvider(provider);
    }
}
