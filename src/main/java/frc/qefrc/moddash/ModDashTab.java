package frc.qefrc.moddash;

import frc.qefrc.moddash.widgets.ModDashWidget;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.PubSubOption;
import edu.wpi.first.networktables.StringArrayEntry;
import edu.wpi.first.networktables.StringEntry;

/**
 * Tab instance. This manages all the widgets within a tab and updating them.
 */
public class ModDashTab {

    /** The unique name of this tab. */
    public final String tabName;

    private final NetworkTable nt;
    private final StringArrayEntry widgetsEntry;
    private final StringEntry displayName;

    private final Map<String, ModDashWidget> widgetInstances;

    /**
     * Create a new ModDash Tab. <b> USE {@link ModDash#getTab(String)}; Certain features will not work otherwise. </b>
     * @param name the unique string name of this tab
     * @param table the NetworkTables subtable that corresponds to this tab.
     */
    public ModDashTab(String name, NetworkTable table) {
        tabName = name;

        nt = table;
        String[] widgetsDefault = {};
        widgetsEntry = nt.getStringArrayTopic(ModDash.prefixWith(ModDash.MD_TAB_WIDGETS_FIELD, false))
                .getEntry(widgetsDefault, PubSubOption.keepDuplicates(false));

        displayName = nt.getStringTopic(ModDash.prefixWith(ModDash.MD_DISPLAY_NAME_FIELD, false))
                .getEntry(name);

        widgetInstances = new HashMap<String, ModDashWidget>();
    }

    /** Update the widgets in this tab. <b> This is automatically called by the {@link ModDash} class. </b> */
    public void update() {}

    /** Set the display name of this tab. Defaults to the "name" passed to get this tab. */
    public void setDisplayName(String newName) {
        displayName.set(newName);
    }
}
