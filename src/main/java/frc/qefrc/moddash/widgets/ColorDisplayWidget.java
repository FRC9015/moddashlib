package frc.qefrc.moddash.widgets;

import java.util.function.Supplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.StringEntry;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Display any color on the dashboard. Default Dimensions are 1x1 (W x H)
 */
public class ColorDisplayWidget extends ModDashWidgetBase {

    private final StringEntry colorToDisplay;
    private Supplier<Color> getCurrentColor;

    public ColorDisplayWidget(String name, NetworkTable table, Color initialValue) {
        super(name, table);

        colorToDisplay = nt.getStringTopic(name).getEntry(initialValue.toHexString());
    }

    @Override
    public void update() {
        if (getCurrentColor != null) {
            colorToDisplay.set(getCurrentColor.get().toHexString());
        }
    }

    public void setColorSupplier(Supplier<Color> supplyColor) {
        getCurrentColor = supplyColor;
    }
}
