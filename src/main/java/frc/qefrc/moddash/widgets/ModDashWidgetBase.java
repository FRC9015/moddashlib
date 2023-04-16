package frc.qefrc.moddash.widgets;

import frc.qefrc.moddash.ModDash;

import lombok.Getter;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.StringEntry;

/**
 * Base class for creating widget types.
 */
public abstract class ModDashWidgetBase implements ModDashWidget {
    @Getter
    public final String widgetName;

    protected final NetworkTable nt;
    private int[] position, dimensions;
    private final StringEntry displayName;

    public ModDashWidgetBase(String name, NetworkTable table) {
        widgetName = name;

        nt = table;

        displayName = nt.getStringTopic(ModDash.prefixWith(ModDash.MD_DISPLAY_NAME_FIELD, false))
                .getEntry(name);
    }

    @Override
    public int[] getHeightAndWidth() {
        return dimensions;
    }

    @Override
    public int[] getPosition() {
        return position;
    }

    @Override
    public void setPosition(int x, int y) {
        position = new int[2];
        position[0] = x;
        position[1] = y;
    }

    @Override
    public void setHeightAndWidth(int height, int width) {
        dimensions = new int[2];
        dimensions[0] = height;
        dimensions[1] = width;
    }

    @Override
    public void setDisplayName(String name) {
        displayName.set(name);
    }
}
