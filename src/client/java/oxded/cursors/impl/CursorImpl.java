package oxded.cursors.impl;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.texture.NativeImage;
import oxded.cursors.CursorsClient;
import oxded.cursors.api.Cursor;

@Environment(EnvType.CLIENT)
public class CursorImpl implements Cursor {
	private final NativeImage image;
	private Optional<Long> glfwCursorHandle = Optional.empty();
	private final int xHot;
	private final int yHot;

	public CursorImpl(NativeImage image, int xHot, int yHot) {
		this.image = image;
		this.xHot = xHot;
		this.yHot = yHot;
	}
	public CursorImpl(int shape) {
		image = null;
		xHot = 0;
		yHot = 0;
		createSystemCursor(shape);
	}

	@Override
	public @Nullable NativeImage getImage() {
		return image;
	}

	@Override
	public int getXHot() {
		return xHot;
	}

	@Override
	public int getYHot() {
		return yHot;
	}
	private void createSystemCursor(int shape) {
		glfwCursorHandle =Optional.of(GLFW.glfwCreateStandardCursor(shape));
	}
	private void createGlfwImage(Consumer<GLFWImage> imageConsumer) throws RuntimeException {
		Optional<ByteBuffer> byteBuffer = Optional.empty();
		try {
			MemoryStack memoryStack = MemoryStack.stackPush();
			GLFWImage img = GLFWImage.malloc(memoryStack);

			byteBuffer = Optional.of(MemoryUtil.memAlloc(image.getWidth() * image.getHeight() * 4));
			byteBuffer.get().asIntBuffer().put(image.copyPixelsRgba());
			img.width(image.getWidth());
			img.height(image.getHeight());
			img.pixels(byteBuffer.get());

			imageConsumer.accept(img);

			if (memoryStack != null) {
				memoryStack.close();
			}
		} catch(Throwable e) {
			CursorsClient.LOGGER.error("Failed at creating GLFW image.", e);
			throw new RuntimeException("Failed at creating GLFW image", e);
		} finally {
			if (byteBuffer.isPresent()) {
				MemoryUtil.memFree(byteBuffer.get());
			}
		}
	}

	public long createGlfwCursor() throws RuntimeException {
		if (!glfwCursorHandle.isPresent()) {
			createGlfwImage((img) -> {
				glfwCursorHandle = Optional.ofNullable(GLFW.glfwCreateCursor(img, xHot, yHot));
			});
			if (!glfwCursorHandle.isPresent()) {
				CursorsClient.LOGGER.error("Failed to make GLFW cursor.");
				throw new RuntimeException("Failed to make GLFW cursor");
			}
		}
		return glfwCursorHandle.get();
	}

	@Override
	public void close() throws Exception {
		if (glfwCursorHandle.isPresent()) {
			GLFW.glfwDestroyCursor(glfwCursorHandle.get());
		}
	}
}
