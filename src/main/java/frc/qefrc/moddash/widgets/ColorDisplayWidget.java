package frc.qefrc.moddash.widgets;

import frc.qefrc.moddash.ModDash;

import java.util.function.Supplier;
import lombok.Getter;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.StringEntry;
import edu.wpi.first.wpilibj.util.Color;

public class ColorDisplayWidget implements ModDashWidget {
    @Getter
    public final String widgetName;

    private final NetworkTable nt;
    private final StringEntry displayName, colorToDisplay;
    private Supplier<Color> getCurrentColor;
    private int[] position, dimensions;

    public ColorDisplayWidget(String name, NetworkTable table, Color initialValue) {
        widgetName = name;

        nt = table;

        displayName = nt.getStringTopic(ModDash.prefixWith(ModDash.MD_DISPLAY_NAME_FIELD, false))
                .getEntry(name);

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
