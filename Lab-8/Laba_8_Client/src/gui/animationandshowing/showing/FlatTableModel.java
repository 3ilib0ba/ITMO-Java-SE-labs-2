package gui.animationandshowing.showing;

import commands.exceptions.InvalidArgExcaption;
import data.netdata.Report;
import data.netdata.ReportState;
import data.netdata.Request;
import main.Client;
import typesfiles.Coordinates;
import typesfiles.Flat;
import typesfiles.Furnish;
import typesfiles.House;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class FlatTableModel extends DefaultTableModel {
    public String oldValue = "";

    public FlatTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    Class<?>[] columnTypes = new Class<?>[] {
            Integer.class, // id
            String.class,  // name
            Date.class,    // date of create
            Long.class,    // area
            Long.class,    // number of rooms
            Integer.class, // number of baths
            Long.class,    // time to metro
            Float.class,   // Coordinate->x
            Double.class,  // Coordinate->y
            Furnish.class, // furnish
            String.class,  // House->name
            Integer.class, // House->year
            Integer.class, // House->number of lifts
            Integer.class, // House->number of flats
    };

    boolean[] columnEditable = new boolean[] {
            false, // id
            true,  // name
            false, // date of create
            true,  // area
            true,  // number of rooms
            true,  // number of baths
            true,  // time to metro
            true,  // Coordinate->x
            true,  // Coordinate->y
            true,  // furnish
            true,  // House->name
            true,  // House->year
            true,  // House->number of lifts
            true   // House->number of flats
    };

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnEditable[columnIndex];
    }

    @Override
    public void addRow(Vector rowData) {
        super.addRow(rowData);
    }

    @Override
    public void removeRow(int row) {
        super.removeRow(row);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        oldValue = String.valueOf(getValueAt(row, column));
        if (String.valueOf(aValue).isEmpty()) return;
        try {
            Request request = generateUpdateRequest(aValue, row, column);
            Client client = Client.getInstance();
            client.sendRequest(request);
            Report report = client.getAnswer();

            JOptionPane.showMessageDialog(null, report.getReportBody());
            if (report.getReportState() != ReportState.OK) return;
        } catch (InvalidArgExcaption exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
            return;
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, "Error with IO");
        } catch (ClassNotFoundException ignored) { }
        super.setValueAt(aValue, row, column);
    }

    public Request generateUpdateRequest(Object newValue, int row, int column)
            throws InvalidArgExcaption {
        Integer id = (Integer) getValueAt(row, 0);
        
        Flat flat = Flat.newBuilder()
                .setId(id)
                .setName((String) this.getValueAt(row, 1))
                .setCreationDate((Date) this.getValueAt(row, 2))
                .setArea((Long) this.getValueAt(row, 3))
                .setNumberOfRooms((Long) this.getValueAt(row, 4))
                .setNumberOfBathrooms((Integer) this.getValueAt(row, 5))
                .setTimeToMetroOnFoot((Long) this.getValueAt(row, 6))
                .setCoordinates(new Coordinates((Float) this.getValueAt(row, 7),
                                                (Double) this.getValueAt(row, 8)))
                .setFurnish((Furnish) this.getValueAt(row, 9))
                .setHouse(new House(
                        (String) this.getValueAt(row, 10),
                        (Integer) this.getValueAt(row, 11),
                        (Integer) this.getValueAt(row, 12),
                        (Integer) this.getValueAt(row, 13)
                ))
                .build();

        switch (column) {
            case 1:
                flat.setName((String) newValue);
                break;
            case 2:
                flat.setCreationDate((Date) newValue);
                break;
            case 3:
                flat.setArea((Long) newValue);
                break;
            case 4:
                flat.setNumberOfRooms((Long) newValue);
                break;
            case 5:
                flat.setNumberOfBathrooms((Integer) newValue);
                break;
            case 6:
                flat.setTimeToMetroOnFoot((Long) newValue);
                break;
            case 7:
                flat.getCoordinates().setX((Float) newValue);
                break;
            case 8:
                flat.getCoordinates().setY((Double) newValue);
                break;
            case 9:
                flat.setFurnish((Furnish) newValue);
                break;
            case 10:
                flat.getHouse().setName((String) newValue);
                break;
            case 11:
                flat.getHouse().setYear((Integer) newValue);
                break;
            case 12:
                flat.getHouse().setNumberOfLifts((Integer) newValue);
                break;
            case 13:
                flat.getHouse().setNumberOfFlatsOnFloor((Integer) newValue);
                break;
        }


        return new Request("update", id.toString(), flat);
    }
}
