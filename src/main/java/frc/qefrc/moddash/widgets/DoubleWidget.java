package frc.qefrc.moddash.widgets;

import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.NetworkTable;

/**
 * A simple double display widget. Default dimensions are 1 x 2 (H x W)
 */
public class DoubleWidget extends ModDashEditableWidgetBase {
    private final DoubleEntry dataDouble;

    public DoubleWidget(String name, NetworkTable table, int initialValue) {
        super(name, table);

        setType(table, this.getClass().getSimpleName());

        dataDouble = nt.getDoubleTopic("value").getEntry(initialValue);
        setValue(initialValue);
    }

    /**
     * Get the current double value
     * @return The current double value
     */
    public double getValue() {
        return (double) dataDouble.get();
    }

    /**
     * Send a value to the dashboard.
     * @param value
     */
    public void setValue(double value) {
        dataDouble.set(value);
    }
}
