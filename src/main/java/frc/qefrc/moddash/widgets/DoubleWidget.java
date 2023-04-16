package frc.qefrc.moddash.widgets;

import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.NetworkTable;

public class DoubleWidget extends ModDashWidgetBase {
    private final DoubleEntry dataDouble;

    public DoubleWidget(String name, NetworkTable table, int initialValue) {
        super(name, table);

        setType(table, this.getClass().getName());

        dataDouble = nt.getDoubleTopic(name).getEntry(initialValue);
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
