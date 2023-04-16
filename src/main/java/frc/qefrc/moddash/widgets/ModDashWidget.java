package frc.qefrc.moddash.widgets;

/**
 * Base interface for Widgets on ModDash. This defines core behavior that every widget shares. This may also be used to create custom widgets.
 */
public interface ModDashWidget {

    /**
     * Override this method to run any periodic update logic if needed.
     */
    public default void update() {}
    ;

    /**
     * Return the widget's height and width in an array.
     * @return array of length 2, formatted [height, width]
     */
    public int[] getHeightAndWidth();

    /**
     * Return the widget's X & Y position in an array.
     * @return array of length 2, formatted [x, y]
     */
    public int[] getPosition();

    /**
     * Set the Height and Width of the widget
     * @return array of length 2, formatted [height, width]
     */
    public void setHeightAndWidth(int height, int width);

    /**
     * Set X & Y position of the widget
     * @return array of length 2, formatted [x, y]
     */
    public void setPosition(int x, int y);

    /**
     * Update the widget's display name.
     * @param name name to set.
     */
    public void setDisplayName(String name);
}
