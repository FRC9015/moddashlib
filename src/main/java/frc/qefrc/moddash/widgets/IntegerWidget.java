package frc.qefrc.moddash.widgets;

import edu.wpi.first.networktables.IntegerEntry;
import edu.wpi.first.networktables.NetworkTable;

/**
 * A simple integer display widget. Default dimensions are 1 x 2 (H x W)
 */
public class IntegerWidget extends ModDashWidgetBase {
    private final IntegerEntry dataInt;

    public IntegerWidget(String name, NetworkTable table, int initialValue) {
        super(name, table);

        setType(table, this.getClass().getName());

        dataInt = nt.getIntegerTopic(name).getEntry(initialValue);
    }

    /**
     * Get the current integer value. <b>Important: This does cast from long to int, so the max value is that of {@link Integer} </b>.
     * @return The current integer value
     */
    public int getValue() {
        return (int) dataInt.get();
    }

    /**
     * Send a value to the dashboard.
     * @param value
     */
    public void setValue(int value) {
        dataInt.set(value);
    }
}
