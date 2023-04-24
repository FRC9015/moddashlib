package frc.qefrc.moddash.widgets;

import frc.qefrc.moddash.ModDash;

import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.StringPublisher;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Display a boolean on the Dashboard. Default Dimensions are 1x1 (W x H) <br></br>
 * Colors are customizable.
 */
public class BooleanDisplayWidget extends ModDashWidgetBase {

    private final StringPublisher trueColor, falseColor;
    private BooleanPublisher dataBoolean;

    /** Create a new BooleanDisplayWidget. <b> USE {@link ModDashTab#get(String)}; Certain features will not work otherwise. </b> */
    public BooleanDisplayWidget(String name, NetworkTable table, Boolean initialValue) {
        super(name, table);

        setType(table, this.getClass().getSimpleName());

        trueColor = nt.getStringTopic(ModDash.prefixWith("trueColor", false)).publish();
        falseColor = nt.getStringTopic(ModDash.prefixWith("falseColor", false)).publish();
        setTrueColor(Color.kGreen);
        setFalseColor(Color.kRed);

        dataBoolean = nt.getBooleanTopic("value").publish();
        setValue(initialValue);
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
