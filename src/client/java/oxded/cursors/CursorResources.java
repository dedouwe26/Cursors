package oxded.cursors;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import oxded.cursors.api.CursorType;
import oxded.cursors.api.Cursor;
import oxded.cursors.api.CursorProvider;

@Environment(EnvType.CLIENT)
public final class CursorResources implements CursorProvider {
    public static final CursorResources INSTANCE = new CursorResources();
    public static final Map<CursorType, Identifier> CURSOR_TEXTURES = new HashMap<CursorType, Identifier>() {{
        put(CursorType.DEFAULT, Identifier.of("cursors", "textures/cursors/default.png"));
        put(CursorType.POINTER, Identifier.of("cursors", "textures/cursors/pointer.png"));
        put(CursorType.TEXT, Identifier.of("cursors", "textures/cursors/text.png"));
        put(CursorType.GRAB, Identifier.of("cursors", "textures/cursors/grab.png"));
        put(CursorType.GRABBING, Identifier.of("cursors", "textures/cursors/grabbing.png"));
        put(CursorType.BLOCKED, Identifier.of("cursors", "textures/cursors/blocked.png"));
    }};
    public static final Map<CursorType, Identifier> CURSOR_PROPERTIES = new HashMap<CursorType, Identifier>() {{
        put(CursorType.DEFAULT, Identifier.of("cursors", "cursors/default.json"));
        put(CursorType.POINTER, Identifier.of("cursors", "cursors/pointer.json"));
        put(CursorType.TEXT, Identifier.of("cursors", "cursors/text.json"));
        put(CursorType.GRAB, Identifier.of("cursors", "cursors/grab.json"));
        put(CursorType.GRABBING, Identifier.of("cursors", "cursors/grabbing.json"));
        put(CursorType.BLOCKED, Identifier.of("cursors", "cursors/blocked.json"));
    }};
    public static Map<CursorType, Cursor> cursors = null;

    private static void parseCursor(ResourceManager manager, Entry<CursorType, Identifier> entry) {
        Identifier propertyIdentifier = CURSOR_PROPERTIES.get(entry.getKey());
        NativeImage image;
        try {
            Resource texture = manager.getAllResources(entry.getValue()).get(0);
            InputStream stream = texture.getInputStream();
            image = NativeImage.read(stream);
        } catch(Exception e) {
            CursorsClient.LOGGER.error("Could not get texture "+entry.getValue().toString(), e);
            return;
        }
        int xHot = 0, yHot = 0;
        try {
            Resource properties = manager.getAllResources(propertyIdentifier).get(0);
            BufferedReader reader = properties.getReader();
            JsonObject object = JsonParser.parseReader(reader).getAsJsonObject();
            xHot = object.get("xHot").getAsInt();
            yHot = object.get("yHot").getAsInt();
        } catch(Exception e) {
            CursorsClient.LOGGER.warn("Could not get properties "+propertyIdentifier.toString(), e);
        }

        cursors.put(entry.getKey(), Cursor.create(image, xHot, yHot));
    }

    public static void init() {
        cursors = new HashMap<>();
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return Identifier.of("cursors", "cursors");
            }
        
            @Override
            public void reload(ResourceManager manager) {
                cursors.clear();
                for (var entry : CURSOR_TEXTURES.entrySet()) {
                    parseCursor(manager, entry);
                }
            }
        });
    }

    @Override
    public Cursor getCursor(CursorType type) {
        return cursors.get(type);
    }
}
