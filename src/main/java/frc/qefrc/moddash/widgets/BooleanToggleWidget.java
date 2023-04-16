package frc.qefrc.moddash.widgets;

import frc.qefrc.moddash.ModDash;
import frc.qefrc.moddash.ModDashTab;

import lombok.Getter;

import edu.wpi.first.networktables.BooleanEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.StringEntry;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Display a toggle widget on the dashboard. This differs from {@link BooleanDisplayWidget} in that it can be updated from the dashboard. Default Dimensions are 1x1 (W x H) <br></br>
 * Colors are customizable.
 */
public class BooleanToggleWidget implements ModDashWidget {
    @Getter
    public final String widgetName;

    private final NetworkTable nt;
    private final StringEntry displayName, trueColor, falseColor;
    private int[] position, dimensions;
    private BooleanEntry dataBoolean;

    /** Create a new BooleanDisplayWidget. <b> USE {@link ModDashTab#get(String)}; Certain features will not work otherwise. </b> */
    public BooleanToggleWidget(String name, NetworkTable table, Boolean initialValue) {
        widgetName = name;

        nt = table;

        displayName = nt.getStringTopic(ModDash.prefixWith(ModDash.MD_DISPLAY_NAME_FIELD, false))
                .getEntry(name);

        trueColor = nt.getStringTopic(ModDash.prefixWith("trueColor", false)).getEntry(Color.kGreen.toHexString());
        falseColor = nt.getStringTopic(ModDash.prefixWith("falseColor", false)).getEntry(Color.kRed.toHexString());

        dataBoolean = nt.getBooleanTopic(name).getEntry(initialValue);
    }

    public boolean getValue() {
        return dataBoolean.get();
    }

    public void setValue(boolean value) {
        dataBoolean.set(value);
    }

    /**
     * Set color when true
     */
    public void setTrueColor(Color color) {
        trueColor.set(color.toHexString());
    }

    /**
     * Set color when false
     */
    public void setFalseColor(Color color) {
        falseColor.set(color.toHexString());
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
