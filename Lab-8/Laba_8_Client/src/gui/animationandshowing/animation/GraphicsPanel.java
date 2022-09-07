package gui.animationandshowing.animation;

import data.netdata.Request;
import main.Client;
import typesfiles.Flat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel {
    public ArrayList<FlatFugire> figures;

    public GraphicsPanel() {
        figures = new ArrayList<>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                checkForRectangleClick(e);
            }
        });
    }

    private void checkForRectangleClick(MouseEvent e) {
        ArrayList<FlatFugire> list = new ArrayList<>(figures);
        System.out.println(e.getX() + " " + e.getY());

        for (FlatFugire rectangle : list) {
            if (rectangle.contains(e.getX(), e.getY())) {
                Flat t = rectangle.getFlat();
                StringBuilder message = new StringBuilder();
                String id = String.valueOf(t.getId());
                String name = t.getName();
                String numberOfRooms = String.valueOf(t.getNumberOfRooms());
                String numberOfBathrooms = String.valueOf(t.getNumberOfBathrooms());
                String type = String.valueOf(t.getFurnish().toString());
                String tX = String.valueOf(t.getCoordinates().getX());
                String tY = String.valueOf(t.getCoordinates().getY());
                message .append("id -> " + id + "\n")
                        .append("name -> " + name + "\n")
                        .append("rooms -> " + numberOfRooms + "\n")
                        .append("baths -> " + numberOfBathrooms + "\n")
                        .append("type -> " + type + "\n")
                        .append("x -> " + tX + "\n")
                        .append("y -> " + tY + "\n");
                UIManager.put("OptionPane.cancelButtonText", "Delete");
                int response;
                if (rectangle.getColor().equals(new Color(100, 255, 0))) {
                    response = JOptionPane.showConfirmDialog(null, message.toString(), "", 2);
                    if (response == 2) {
                        try {
                            synchronized (this) {
                                Client client = Client.getClient();
                                client.sendRequest(new Request("remove_key", String.valueOf(t.getId())));
                                try {
                                    JOptionPane.showMessageDialog(null, client.getAnswer().getReportBody());
                                } catch (IOException ioException) {
                                    System.out.println("Error");
                                }
                            }
                        } catch (IOException | ClassNotFoundException exception) {
                            JOptionPane.showMessageDialog(null, "Error");
                        }
                    }
                }
            }
        }
    }

    public void addRectangle(FlatFugire fugire) {
        figures.add(fugire);
    }

    public void clearPanel() {
        figures.clear();
    }

    public void deleteFromPanel(int index) {
        figures.remove(index);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (FlatFugire rectangle : figures) {
            g2.setColor(rectangle.getColor());
            g2.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            g2.setColor(new Color(0, 0, 0));
            g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
            g2.drawString(rectangle.getText(), rectangle.x + (rectangle.width / 2) - 1, rectangle.y + (rectangle.height / 2) - 1);
        }
    }
}
