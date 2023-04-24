package frc.qefrc.moddash;

import frc.qefrc.moddash.widgets.AlreadyExistsException;
import frc.qefrc.moddash.widgets.BooleanDisplayWidget;
import frc.qefrc.moddash.widgets.BooleanToggleWidget;
import frc.qefrc.moddash.widgets.ColorDisplayWidget;
import frc.qefrc.moddash.widgets.DoubleWidget;
import frc.qefrc.moddash.widgets.IntegerWidget;
import frc.qefrc.moddash.widgets.ModDashWidget;
import frc.qefrc.moddash.widgets.StringWidget;

import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import lombok.Synchronized;
import lombok.val;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.PubSubOption;
import edu.wpi.first.networktables.StringArrayPublisher;
import edu.wpi.first.networktables.StringPublisher;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Tab instance. This manages all the widgets within a tab and updating them.
 */
public class ModDashTab {

    /** The unique name of this tab. */
    public final String tabName;

    private final NetworkTable nt;
    private final StringArrayPublisher widgetsEntry;
    private final StringPublisher displayName;

    private final Map<String, ModDashWidget> widgetInstances;

    /**
     * Create a new ModDash Tab. <b> USE {@link ModDash#getTab(String)}; Certain features will not work otherwise. </b>
     * @param name the unique string name of this tab
     * @param table the NetworkTables subtable that corresponds to this tab.
     */
    public ModDashTab(String name, NetworkTable table) {
        tabName = name;

        nt = table;
        widgetsEntry = nt.getStringArrayTopic(ModDash.prefixWith(ModDash.MD_TAB_WIDGETS_FIELD))
                .publish(PubSubOption.keepDuplicates(false), PubSubOption.disableRemote(true));

        displayName = nt.getStringTopic(ModDash.prefixWith(ModDash.MD_DISPLAY_NAME_FIELD))
                .publish();
        displayName.set(name);

        widgetInstances = new HashMap<String, ModDashWidget>();
    }

    /** Update the widgets in this tab. <b> This is automatically called by the {@link ModDash} class. </b> */
    public void update() {
        for (ModDashWidget widget : widgetInstances.values()) {
            widget.update();
        }
    }

    /** Set the display name of this tab. Defaults to the "name" passed to get this tab. */
    public void setDisplayName(String newName) {
        displayName.set(newName);
    }

    /* ---------- WIDGET FACTORY METHODS ---------- */

    /**
     * Gets a widget if it exists, creating a new widget otherwise.
     * @param name unique name of your widget
     * @param initialValue initial value to pass in if the widget is instantiated.
     * @return a new {@link StringWidget} instance!
     * @throws AlreadyExistsException
     */
    public StringWidget getStringWidget(@NonNull String name, String initialValue) throws AlreadyExistsException {
        if (widgetInstances.get(name) == null) {
            val widget = new StringWidget(name, nt.getSubTable(name), initialValue);
            addWidget(name, widget);
            return widget;
        } else if (widgetInstances.get(name) instanceof StringWidget) {
            return (StringWidget) widgetInstances.get(name);
        } else {
            // Throw an error if the widget is not null but is not an instance of StringWidget
            throw new AlreadyExistsException(
                    "Widget with name " + name + " in tab " + tabName + " already exists as a different type!");
        }
    }

    /**
     * Gets a widget if it exists, creating a new widget otherwise.
     * @param name unique name of your widget
     * @param initialValue initial value to pass in if the widget is instantiated.
     * @return a new {@link BooleanDisplayWidget} instance!
     * @throws AlreadyExistsException
     */
    public BooleanDisplayWidget getBooleanDisplayWidget(@NonNull String name, boolean initialValue)
            throws AlreadyExistsException {
        if (widgetInstances.get(name) == null) {
            val widget = new BooleanDisplayWidget(name, nt.getSubTable(name), initialValue);
            addWidget(name, widget);
            return widget;
        } else if (widgetInstances.get(name) instanceof BooleanDisplayWidget) {
            return (BooleanDisplayWidget) widgetInstances.get(name);
        } else {
            // Throw an error if the widget is not null but is not an instance of StringWidget
            throw new AlreadyExistsException(
                    "Widget with name " + name + " in tab " + tabName + " already exists as a different type!");
        }
    }

    /**
     * Gets a widget if it exists, creating a new widget otherwise.
     * @param name unique name of your widget
     * @param initialValue initial value to pass in if the widget is instantiated.
     * @return a new {@link BooleanToggleWidget} instance!
     * @throws AlreadyExistsException
     */
    public BooleanToggleWidget getBooleanToggleWidget(@NonNull String name, boolean initialValue)
            throws AlreadyExistsException {
        if (widgetInstances.get(name) == null) {
            val widget = new BooleanToggleWidget(name, nt.getSubTable(name), initialValue);
            addWidget(name, widget);
            return widget;
        } else if (widgetInstances.get(name) instanceof BooleanToggleWidget) {
            return (BooleanToggleWidget) widgetInstances.get(name);
        } else {
            // Throw an error if the widget is not null but is not an instance of StringWidget
            throw new AlreadyExistsException(
                    "Widget with name " + name + " in tab " + tabName + " already exists as a different type!");
        }
    }

    /**
     * Gets a widget if it exists, creating a new widget otherwise.
     * @param name unique name of your widget
     * @param initialValue initial value to pass in if the widget is instantiated.
     * @return a new {@link IntegerWidget} instance!
     * @throws AlreadyExistsException
     */
    public IntegerWidget getIntegerWidget(@NonNull String name, int initialValue) throws AlreadyExistsException {
        if (widgetInstances.get(name) == null) {
            val widget = new IntegerWidget(name, nt.getSubTable(name), initialValue);
            addWidget(name, widget);
            return widget;
        } else if (widgetInstances.get(name) instanceof IntegerWidget) {
            return (IntegerWidget) widgetInstances.get(name);
        } else {
            // Throw an error if the widget is not null but is not an instance of StringWidget
            throw new AlreadyExistsException(
                    "Widget with name " + name + " in tab " + tabName + " already exists as a different type!");
        }
    }

    /**
     * Gets a widget if it exists, creating a new widget otherwise.
     * @param name unique name of your widget
     * @param initialValue initial value to pass in if the widget is instantiated.
     * @return a new {@link DoubleWidget} instance!
     * @throws AlreadyExistsException
     */
    public DoubleWidget getDoubleWidget(@NonNull String name, int initialValue) throws AlreadyExistsException {
        if (widgetInstances.get(name) == null) {
            val widget = new DoubleWidget(name, nt.getSubTable(name), initialValue);
            addWidget(name, widget);
            return widget;
        } else if (widgetInstances.get(name) instanceof DoubleWidget) {
            return (DoubleWidget) widgetInstances.get(name);
        } else {
            // Throw an error if the widget is not null but is not an instance of StringWidget
            throw new AlreadyExistsException(
                    "Widget with name " + name + " in tab " + tabName + " already exists as a different type!");
        }
    }

    /**
     * Gets a widget if it exists, creating a new widget otherwise.
     * @param name unique name of your widget
     * @param initialValue initial value to pass in if the widget is instantiated.
     * @return a new {@link ColorDisplayWidget} instance!
     * @throws AlreadyExistsException
     */
    public ColorDisplayWidget getColorDisplayWidget(@NonNull String name, Color initialValue)
            throws AlreadyExistsException {
        if (widgetInstances.get(name) == null) {
            val widget = new ColorDisplayWidget(name, nt.getSubTable(name), initialValue);
            addWidget(name, widget);
            return widget;
        } else if (widgetInstances.get(name) instanceof ColorDisplayWidget) {
            return (ColorDisplayWidget) widgetInstances.get(name);
        } else {
            // Throw an error if the widget is not null but is not an instance of StringWidget
            throw new AlreadyExistsException(
                    "Widget with name " + name + " in tab " + tabName + " already exists as a different type!");
        }
    }

    @Synchronized("widgetsEntry")
    private void addWidget(String name, ModDashWidget widget) {
        widgetInstances.put(name, widget);

        widget.update();

        // Only update the NT entry once the widget has been added and any initial updates have ran
        widgetsEntry.set(widgetInstances.keySet().toArray(new String[0]));
    }
}
