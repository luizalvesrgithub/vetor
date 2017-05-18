package br.com.rib;

import javafx.scene.control.TabPane;
import ui.layouts.ribbonBar.tabs.commerce.CommerceTab;
import ui.layouts.ribbonBar.tabs.home.HomeTab;

/**
 * Ribbon Bar.
 */
public class RibbonBar {

    private TabPane tabPane;

    public RibbonBar() {

        tabPane = new TabPane();

        buildTabs();
    }

    /**
     * get. Return instance of the RibbonBar (TabPane).
     * @return 
     */
    public TabPane get() {
        return this.tabPane;
    }

    private void buildTabs() {

        HomeTab homeTab = new HomeTab();
        CommerceTab insertTab = new CommerceTab();

        tabPane.getTabs().addAll(homeTab.get(), insertTab.get());
    }
}