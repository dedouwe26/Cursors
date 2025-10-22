package oxded.cursors.api;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import oxded.cursors.impl.SystemCursorProviderImpl;

@Environment(EnvType.CLIENT)
public abstract class SystemCursorProvider implements CursorProvider {
	public static SystemCursorProvider getProvider() {
		return new SystemCursorProviderImpl();
	}

	@Override
	public abstract Cursor getCursor(CursorType type);
	
}
