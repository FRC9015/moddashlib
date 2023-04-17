package frc.qefrc.moddash.widgets;

import frc.qefrc.moddash.ModDash;

import lombok.Getter;

import edu.wpi.first.networktables.IntegerArrayEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.StringEntry;

/**
 * Base class for creating widget types.
 */
public abstract class ModDashWidgetBase implements ModDashWidget {
    @Getter
    public final String widgetName;

    protected final NetworkTable nt;
    private long[] position, dimensions;
    private final StringEntry displayName;
    private final IntegerArrayEntry positionEntry, dimensionsEntry;

    public ModDashWidgetBase(String name, NetworkTable table) {
        widgetName = name;

        nt = table;

        displayName = nt.getStringTopic(ModDash.prefixWith(ModDash.MD_DISPLAY_NAME_FIELD, false))
                .getEntry(name);
        setDisplayName(name);

        // Make sure all these values appear as expected on the Dashboard

        long[] initPosition = new long[2];
        initPosition[0] = 0;
        initPosition[1] = 0;
        positionEntry = nt.getIntegerArrayTopic(ModDash.prefixWith(ModDash.MD_X_Y_POSITION_FIELD, false))
                .getEntry(initPosition);
        positionEntry.set(initPosition);

        long[] initDimensions = new long[2];
        initDimensions[0] = 0;
        initDimensions[1] = 0;
        dimensionsEntry = nt.getIntegerArrayTopic(ModDash.prefixWith(ModDash.MD_HEIGHT_WIDTH_FIELD, false))
                .getEntry(initDimensions);
        dimensionsEntry.set(initDimensions);
    }

    @Override
    public int[] getHeightAndWidth() {
        int[] intDimensions = new int[2];
        intDimensions[0] = (int) dimensions[0];
        intDimensions[1] = (int) dimensions[1];
        return intDimensions;
    }

    @Override
    public int[] getPosition() {
        int[] intPosition = new int[2];
        intPosition[0] = (int) position[0];
        intPosition[1] = (int) position[1];
        return intPosition;
    }

    @Override
    public void setPosition(int x, int y) {
        position = new long[2];
        position[0] = x;
        position[1] = y;
        positionEntry.set(position);
    }

    @Override
    public void setHeightAndWidth(int height, int width) {
        dimensions = new long[2];
        dimensions[0] = height;
        dimensions[1] = width;
        dimensionsEntry.set(dimensions);
    }

    @Override
    public void setDisplayName(String name) {
        displayName.set(name);
    }

    /**
     * Static helper to set the widget type property on NetworkTables
     * @param table
     * @param type
     * @return
     */
    public static StringEntry setType(NetworkTable table, String type) {
        String prefix = ModDash.prefixWith(ModDash.MD_WIDGET_TYPE_FIELD, false);

        StringEntry entry = table.getStringTopic(prefix).getEntry("");
        entry.set(type);

        return entry;
    }
}
