package frc.qefrc.moddash.widgets;

import frc.qefrc.moddash.ModDash;

import edu.wpi.first.networktables.BooleanEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.StringEntry;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Display a boolean on the Dashboard. Default Dimensions are 1x1 (W x H) <br></br>
 * Colors are customizable.
 */
public class BooleanDisplayWidget extends ModDashWidgetBase {

    private final StringEntry trueColor, falseColor;
    private BooleanEntry dataBoolean;

    /** Create a new BooleanDisplayWidget. <b> USE {@link ModDashTab#get(String)}; Certain features will not work otherwise. </b> */
    public BooleanDisplayWidget(String name, NetworkTable table, Boolean initialValue) {
        super(name, table);

        trueColor = nt.getStringTopic(ModDash.prefixWith("trueColor", false)).getEntry(Color.kGreen.toHexString());
        falseColor = nt.getStringTopic(ModDash.prefixWith("falseColor", false)).getEntry(Color.kRed.toHexString());

        dataBoolean = nt.getBooleanTopic(name).getEntry(initialValue);
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
}
