package oxded.cursors.api;

import oxded.cursors.impl.SystemCursorProviderImpl;

public abstract class SystemCursorProvider implements CursorProvider {
    public static SystemCursorProvider getProvider() {
        return new SystemCursorProviderImpl();
    }

    @Override
    public abstract Cursor getCursor(CursorType type);
    
}
