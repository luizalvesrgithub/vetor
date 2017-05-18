package ui.layouts.ribbonBar.tabs.home;

import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;

/**
 * HomeTab. This class represents the "Home Tab"; where common functions
 * might be placed.
 */
public class HomeTab {

    private Tab tab;

    /**
     * Default Constructor.
     */
    public HomeTab() {
        tab = new Tab("Home");
        buildTab();
    }

    /**
     * get. Returns an instance of the Home Tab. This will be added to the 
     * TabPane in the RibbonBar class.
     * @return 
     */
    public Tab get() {
        return this.tab;
    }

    /**
     * buildTab. Helper method to build the Home Tab UI.
     */
    private void buildTab() {

        //Do not allow tab to close.
        tab.setClosable(false);

        //The container holds all toolbar sections specific to a Tab.
        HBox container = new HBox();

        //Set ID (for CSS styles)
        container.setId("container");        //Set preferred height.
        container.setPrefHeight(90);

        //Put spacing b/n each toolbar block
        container.setSpacing(5);

        //Add the Actions Ribbon Component
        Actions actions = new Actions();
        container.getChildren().add(actions.get());

        //Add the Clipboard Ribbon Component.
        ui.layouts.ribbonBar.tabs.home.Clipboard clipboard = new ui.layouts.ribbonBar.tabs.home.Clipboard();
        container.getChildren().add(clipboard.get());

        //Add the Fonts Ribbon Component.
        ui.layouts.ribbonBar.tabs.home.Font font = new ui.layouts.ribbonBar.tabs.home.Font();
        container.getChildren().add(font.get());

        //Add Container.
        tab.setContent(container);

    }

}