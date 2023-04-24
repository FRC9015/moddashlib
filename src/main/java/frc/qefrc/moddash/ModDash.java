package frc.qefrc.moddash;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.PubSubOption;
import edu.wpi.first.networktables.StringArrayEntry;

/**
 * The root ModDash instance. This class is a singleton, i.e. there is a single global instance that manages all communication for ModDash.
 */
public final class ModDash {
    /* Singleton Stuff */
    private static ModDash INSTANCE;

    public static ModDash getInstance() {
        if (INSTANCE == null) INSTANCE = new ModDash();
        return INSTANCE;
    }

    /* Constants. These are the subtables/properties that configure widgets */
    public static final String MODDASH_SUBTABLE_NAME = "moddash";
    public static final String METADATA_PREFIX = "md.";
    public static final String MD_DISPLAY_NAME_FIELD = "displayName";
    // Tab Specific Fields
    public static final String MD_TABS_FIELD = "tabs";
    public static final String MD_TAB_WIDGETS_FIELD = "widgets";
    // Widget Specific Fields
    public static final String MD_WIDGET_TYPE_FIELD = "type";
    public static final String MD_HEIGHT_WIDTH_FIELD = "h_w";
    public static final String MD_X_Y_POSITION_FIELD = "x_y";

    /* Meat and Potatoes Code */
    final NetworkTable nt;
    final StringArrayEntry tabsEntry;
    private final Map<String, ModDashTab> tabInstances;
    private final ModDashTab defaultTab;

    private ModDash() {
        nt = NetworkTableInstance.getDefault().getTable(MODDASH_SUBTABLE_NAME);

        String[] tabsDefault = {};
        tabsEntry = nt.getStringArrayTopic(prefixWith(MD_TABS_FIELD))
                .getEntry(tabsDefault, PubSubOption.keepDuplicates(false), PubSubOption.disableRemote(true));

        tabInstances = new HashMap<String, ModDashTab>();

        // Get the Default Tab
        defaultTab = getTab("Dashboard");
    }

    /**
     * Get a {@link ModDashTab} instance. If one does not exist with the specified name, it will create a new tab instance.
     * This also syncs with the necessary values on NT to make sure autodiscovery works.
     * @param name Name of this tab you wish to get
     * @return {@link ModDashTab} instance with the given name
     */
    public ModDashTab getTab(String name) {
        ModDashTab tab = tabInstances.get(name);
        if (tab == null) {
            tab = new ModDashTab(name, nt.getSubTable(name));
            tabInstances.put(name, tab);
        }
        // Make sure networktables stays updated
        tabsEntry.set(tabInstances.keySet().toArray(new String[0]));
        return tab;
    }

    /**
     * Get the default {@link ModDashTab} instance. Its name is "Dashboard".
     * @return the default {@link ModDashTab}
     */
    public ModDashTab getDefaultTab() {
        return defaultTab;
    }

    /** Update the data in tabs.
     * This must be called in {@link edu.wpi.first.wpilibj.TimedRobot#robotPeriodic()} (or another periodic method) in order to function properly. */
    public void update() {
        for (ModDashTab tab : tabInstances.values()) {
            tab.update();
        }
    }

    /* Helper Methods */
    public static String prefixWith(String toPrefix) {
        return METADATA_PREFIX + toPrefix;
    }
}
