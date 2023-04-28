package frc.qefrc.moddash.widgets;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class ChooserWidget extends ModDashWidgetBase {
    private final SendableChooser<? extends Object> chooser;

    public ChooserWidget(String name, NetworkTable table, SendableChooser<? extends Object> sChooser) {
        super(name, table);

        setType(table, this.getClass().getSimpleName());

        chooser = sChooser;

        var builder = new SendableBuilderImpl();
        builder.setTable(table);
        SendableRegistry.publish(chooser, builder);
        builder.startListeners();
    }

    /**
     * <b>Important: the height is fixed to 2 on this widget.
     */
    @Override
    public void setHeightAndWidth(int height, int width) {
        super.setHeightAndWidth(2, width);
    }
}
