package frc.qefrc.moddash.widgets;

import frc.qefrc.moddash.ModDashTab;

import lombok.NonNull;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.StringEntry;

/**
 * A simple string display widget. Default dimensions are 1 x 2 (H x W)
 */
public class StringWidget extends ModDashWidgetBase {
    private final StringEntry dataString;

    /** Create a new StringWidget. <b> USE {@link ModDashTab#getStringWidget(String, String)}; Certain features will not work otherwise. </b> */
    public StringWidget(String name, NetworkTable table, String initialValue) {
        super(name, table);

        setType(table, this.getClass().getName());

        dataString = nt.getStringTopic("value").getEntry(initialValue);

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
}
