package Master.HistoricalCandles.ViewPKG.ChartComponents;

import javax.swing.*;

public class CustomMenuBar extends JMenuBar {

    public static JMenuBar createMenuBar() {

        // Create an instance of Class
        CustomMenuBar menuBar = new CustomMenuBar();

        // Create a JMenu
        JMenu Services = new JMenu("Services");
        JMenu ChartActions = new JMenu("Display");
        JMenu Settings = new JMenu("Settings");

        // Create JMenuItems for File menu
        JMenuItem services1 = new JMenuItem("Live Data Stream");
        JMenuItem services6 = new JMenuItem("Historical Analysis");
        JMenuItem services2 = new JMenuItem("Fundamental Analysis");
        JMenuItem services3 = new JMenuItem("Economic Indicators");
        JMenuItem services4 = new JMenuItem("Back Testing");
        JMenuItem services5 = new JMenuItem("Algorithmic Trading");

        JMenuItem chartActions1 = new JMenuItem("Save ChartComponents");
        JMenuItem chartActions2 = new JMenuItem("Load ChartComponents");
        JMenuItem chartActions3  = new JMenuItem("My Saved Data");
        JMenuItem chartActions4 = new JMenuItem("Add/Remove Indicators");
        JMenuItem chartActions5 = new JMenuItem("Populate Trend Lines");
        JMenuItem chartActions6 = new JMenuItem("Enable/Disable Drawing");

        JMenuItem settings1 = new JMenuItem("Login");
        JMenuItem settings2 = new JMenuItem("Display Settings");
        JMenuItem settings3 = new JMenuItem("Program Settings");


        // Add ActionListeners to menu items
        Services.addActionListener(e -> {
        });

        ChartActions.addActionListener(e -> {
        });

        Settings.addActionListener(e -> {
        });


        // Add the menu items to the File menu
        Services.add(services1);
        Services.add(services2);
        Services.add(services3);
        Services.add(services4);
        Services.add(services5);
        Services.add(services6);

        ChartActions.add(chartActions1);
        ChartActions.add(chartActions2);
        ChartActions.add(chartActions3);
        ChartActions.add(chartActions4);
        ChartActions.add(chartActions5);
        ChartActions.add(chartActions6);

        Settings.add(settings1);
        Settings.add(settings2);
        Settings.add(settings3);


        // Add the File menu to the menu bar
        menuBar.add(Services);
        menuBar.add(ChartActions);
        menuBar.add(Settings);

        return menuBar;
    }
}

