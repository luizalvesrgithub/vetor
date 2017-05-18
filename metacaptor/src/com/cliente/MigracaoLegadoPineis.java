package com.cliente;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class MigracaoLegadoPineis {

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JFrame f = new JFrame();
    private String[] columnNames = {"First Name", "Last Name", "Sport",
        "# of Years", "Vegetarian"};
    private Object[][] data = {
        {"Kathy", "Smith", "Snowboarding", new Integer(5), (false)},
        {"John", "Doe", "Rowing", new Integer(3), (true)},
        {"Sue", "Black", "Knitting", new Integer(2), (false)},
        {"Jane", "White", "Speed reading", new Integer(20), (true)},
        {"Joe", "Brown", "Pool", new Integer(10), (false)}
    };
    private DefaultTableModel model = new DefaultTableModel(data, columnNames) {
        private static final long serialVersionUID = 1L;

        @Override
        public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
    };

    public MigracaoLegadoPineis() {
        //tabbedPane.addTab("Tab1", new JScrollPane(new JTable(model)));
        //tabbedPane.addTab("Tab2", new JScrollPane(new JTable(model)));
        //tabbedPane.addTab("Tab3", new JScrollPane(new JTable(model)));
        //tabbedPane.addTab("Tab4", new JScrollPane(new JTable(model)));
        tabbedPane.addTab("Duas Colunas", new JScrollPane(new DualTableFrame()));
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	MigracaoLegadoPineis frame = new MigracaoLegadoPineis();    
            }
        });
    }
    
    public class DualTableFrame extends JPanel {

        private JTable leftTable;
        private JTable rightTable;
        private JButton addButton;
        private JButton removeButton;

        public DualTableFrame() {

            setLayout(new GridBagLayout());

            leftTable = new JTable(new SimpleColorTableModel());
            rightTable = new JTable(new SimpleColorTableModel());

            setupTable(leftTable);
            setupTable(rightTable);

            populate((SimpleColorTableModel) leftTable.getModel());

            addButton = new JButton("Add >>");
            removeButton = new JButton("<< Remove");

            JPanel pnlActions = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            pnlActions.add(addButton, gbc);

            gbc.gridy++;
            pnlActions.add(removeButton, gbc);

            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.33;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            add(new JLabel("Available Choices"), gbc);
            gbc.gridx++;
            add(new JPanel(), gbc);
            gbc.gridx++;
            add(new JLabel("Your Choices"), gbc);

            gbc.gridy++;
            gbc.gridx = 0;
            gbc.weighty++;
            gbc.fill = GridBagConstraints.BOTH;

            add(new JScrollPane(leftTable), gbc);
            gbc.gridx++;
            add(pnlActions, gbc);
            gbc.gridx++;
            add(new JScrollPane(rightTable), gbc);

            setSize(400, 600);

            addButton.setEnabled(false);
            removeButton.setEnabled(false);

            leftTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    int count = leftTable.getSelectedRowCount();
                    addButton.setEnabled(count > 0);

                }
            });
            rightTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    int count = rightTable.getSelectedRowCount();
                    removeButton.setEnabled(count > 0);

                }
            });

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    moveSelectedRow(leftTable, rightTable);

                }
            });

            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    moveSelectedRow(rightTable, leftTable);

                }
            });

        }

        protected void moveSelectedRow(JTable from, JTable to) {

            SimpleColorTableModel fromModel = (SimpleColorTableModel) from.getModel();
            SimpleColorTableModel toModel = (SimpleColorTableModel) to.getModel();

            for (int index : from.getSelectedRows()) {

                Vector rowValue = (Vector) fromModel.getDataVector().get(index);

                toModel.addRow(rowValue);

            }

            int selectedRow = -1;
            while ((selectedRow = from.getSelectedRow()) != -1) {

                fromModel.removeRow(selectedRow);

            }

            from.clearSelection();

        }

        protected void populate(SimpleColorTableModel model) {

            model.addRow(new Object[]{"Black", Color.BLACK});
            model.addRow(new Object[]{"Blue", Color.BLUE});
            model.addRow(new Object[]{"Cyan", Color.CYAN});
            model.addRow(new Object[]{"Dark Gray", Color.DARK_GRAY});
            model.addRow(new Object[]{"Gray", Color.GRAY});
            model.addRow(new Object[]{"Green", Color.GREEN});
            model.addRow(new Object[]{"Light Gary", Color.LIGHT_GRAY});
            model.addRow(new Object[]{"Mangenta", Color.MAGENTA});
            model.addRow(new Object[]{"Orange", Color.ORANGE});
            model.addRow(new Object[]{"Pink", Color.PINK});
            model.addRow(new Object[]{"Red", Color.RED});
            model.addRow(new Object[]{"White", Color.WHITE});
            model.addRow(new Object[]{"Yellow", Color.YELLOW});

        }

        protected void setupTable(JTable table) {

            table.setFillsViewportHeight(true);

            table.setDefaultRenderer(Color.class, new ColorTableCellRenderer());

        }
    }
    
    class SimpleColorTableModel extends DefaultTableModel {

        public SimpleColorTableModel() {

            addColumn("Name");
            addColumn("Color");

        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {

            Class clazz = String.class;

            switch (columnIndex) {

                case 1:
                    clazz = Color.class;
                    break;

            }

            return clazz;

        }
        
        

    }
    
    class ColorTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            setText(null);
            if (value instanceof Color) {

                setOpaque(true);
                setBackground((Color)value);

            }

            return this;

        }

    }
}