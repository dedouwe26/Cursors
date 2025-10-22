package oxded.cursors.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@FunctionalInterface
@Environment(EnvType.CLIENT)
public interface CursorProvider {
	public Cursor getCursor(CursorType type);
}
