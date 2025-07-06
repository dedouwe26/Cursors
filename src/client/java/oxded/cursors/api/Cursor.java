package oxded.cursors.api;

import net.minecraft.client.texture.NativeImage;
import oxded.cursors.impl.CursorImpl;

public interface Cursor extends AutoCloseable {
    public static Cursor fromSystem(int shape) {
        return new CursorImpl(shape);
    }
    public static Cursor create(NativeImage image, int xHot, int yHot) {
        return new CursorImpl(image, xHot, yHot);
    }

    public NativeImage getImage();
    public int getXHot();
    public int getYHot();
}
