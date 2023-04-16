package frc.qefrc.moddash.widgets;

import frc.qefrc.moddash.ModDash;
import frc.qefrc.moddash.ModDashTab;

import lombok.NonNull;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.StringEntry;

/**
 * A simple string display widget. Default dimensions are 1 x 2 (H x W)
 */
public class StringWidget implements ModDashWidget {
    /** The unique name of this tab. */
    public final String widgetName;

    private final NetworkTable nt;
    private int[] position, dimensions;
    private final StringEntry displayName, dataString;

    /** Create a new StringWidget. <b> USE {@link ModDashTab#get(String)}; Certain features will not work otherwise. </b> */
    public StringWidget(String name, NetworkTable table, String initialValue) {
        widgetName = name;

        nt = table;

        displayName = nt.getStringTopic(ModDash.prefixWith(ModDash.MD_DISPLAY_NAME_FIELD, false))
                .getEntry(name);

        dataString = nt.getStringTopic(name).getEntry(initialValue);

        setHeightAndWidth(1, 2);
    }

    /**
     * Get the string value from NetworkTables.
     * @return
     */
    public String getValue() {
        return dataString.get();
    }

    /**
     * Send a string value.
     * @param value the String to send.
     */
    public void setValue(@NonNull String value) {
        dataString.set(value);
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
