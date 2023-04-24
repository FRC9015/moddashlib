package frc.qefrc.moddash.widgets;

import lombok.Synchronized;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.PubSubOption;
import edu.wpi.first.networktables.StringPublisher;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

/**
 * Display any color on the dashboard. Default Dimensions are 1x1 (W x H)
 */
public class ColorDisplayWidget extends ModDashWidgetBase {

    private final StringPublisher colorToDisplay;
    private Color currentColor;

    public ColorDisplayWidget(String name, NetworkTable table, Color initialValue) {
        super(name, table);

        setType(table, this.getClass().getSimpleName());
        setHeightAndWidth(1, 1);

        colorToDisplay = nt.getStringTopic("color").publish(PubSubOption.keepDuplicates(true));
        colorToDisplay.set(initialValue.toHexString());
        currentColor = initialValue;
    }

    @Override
    public void update() {
        // Make sure that the color in NT doesn't get out of sync with currentColor
        colorToDisplay.set(currentColor.toHexString());
    }

    @Synchronized("currentColor")
    public void setColor(Color color) {
        currentColor = color;
    }

    @Synchronized("currentColor")
    public void setColor(Color8Bit color) {
        currentColor = new Color(color);
    }

    public Color getColor() {
        return currentColor;
    }
}
