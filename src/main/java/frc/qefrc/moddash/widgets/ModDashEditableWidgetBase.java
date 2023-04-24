package frc.qefrc.moddash.widgets;

import frc.qefrc.moddash.ModDash;

import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.NetworkTable;

/**
 * Use this for widgets whose state can be changed from the dashboard.
 */
public abstract class ModDashEditableWidgetBase extends ModDashWidgetBase {
    protected final BooleanPublisher isEditable;

    public ModDashEditableWidgetBase(String name, NetworkTable table) {
        super(name, table);
        isEditable = nt.getBooleanTopic(ModDash.prefixWith("editable")).publish();
        isEditable.set(true);
    }

    /**
     * Whether this value should be editable on the dashboard
     * @param editable if false, the dashboard field is disabled and becomes display-only.
     */
    public void setEditable(boolean editable) {
        isEditable.set(editable);
    }
}
