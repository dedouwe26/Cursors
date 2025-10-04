package oxded.cursors.api;

@FunctionalInterface
public interface CursorProvider {
    public Cursor getCursor(CursorType type);
}
