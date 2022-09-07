package gui.animationandshowing.animation;

import data.netdata.Report;
import data.netdata.Request;
import gui.MainFrameManager;
import gui.generalmenuframe.GeneralMenu;
import main.Client;
import typesfiles.Flat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class VizualizeWindow extends JPanel{
    private JLabel flatMgLabel;
    private GraphicsPanel panel;
    private JButton backButton;

    private float MAX_X_COORDINATE_VALUE = (float) 483;
    private float MIN_X_COORDINATE_VALUE = (float) -483;
    private double MAX_Y_COORDINATE_VALUE = 311;
    private double MIN_Y_COORDINATE_VALUE = -311;

    private boolean isActive = false;

    //private CommandListWindow cmdListWindow;
    private ArrayList<Flat> flats;
    private ArrayList<FlatFugire> figures;
    Thread drawThread;

    public VizualizeWindow() {
        //flats = new ArrayList<>();
        figures = new ArrayList<>();
        initComponents();
        initListening();
    }


    public void initListening() {
        isActive = true;
        drawThread = new Thread(() -> {
            while (isActive) {
                panel.clearPanel();
                visualize();
                panel.repaint();
                MainFrameManager.getInstance().validate();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        drawThread.start();
    }


    private void visualize() {
        synchronized (panel) {
            try {
                panel.removeAll();
                Client client = Client.getClient();
                client.sendRequest(new Request("show", ""));
                Report response = client.getAnswer();

                TreeMap<Integer, Flat> map = response.getMyMap();
                flats = new ArrayList<>();
                for (Map.Entry entry : map.entrySet()) {
                    flats.add((Flat) entry.getValue());
                }
                //figures.removeIf(rect -> !flats.contains(rect.getFlat()));

                for (Flat t : flats) {
                    defineMaxAndMinCoordinates();
                    FlatFugire rectangle = drawObject(t);
                    //panel.addRectangle(rectangle);
                    initAnimation(rectangle);
                }
            } catch (IOException | ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "error");
            }
        }
    }

    private void initAnimation(FlatFugire rectangle) {
        int initialWidth = rectangle.width;
        int initialHeight = rectangle.height;
        if (!figures.contains(rectangle)) {
            rectangle.width = 10;
            rectangle.height = 10;
            figures.add(rectangle);
        } else {
            int index = 0;
            for (FlatFugire rect: figures) {
                if (rect.equals(rectangle)) break;
                index++;
            }
            figures.get(index).width += 5;
            if (figures.get(index).width > initialWidth) figures.get(index).width = initialWidth;
            figures.get(index).height += 3;
            if (figures.get(index).height > initialHeight) figures.get(index).height = initialHeight;
            rectangle.width = figures.get(index).width;
            rectangle.height = figures.get(index).height;
        }
        panel.addRectangle(rectangle);
    }

    private void defineMaxAndMinCoordinates() {
        for (Flat t: flats) {
            float x = t.getCoordinates().getX();
            double y = t.getCoordinates().getY();
            if (x > MAX_X_COORDINATE_VALUE) MAX_X_COORDINATE_VALUE = (int) x;
            if (x < MIN_X_COORDINATE_VALUE) MIN_X_COORDINATE_VALUE = (int) x;
            if (y > MAX_Y_COORDINATE_VALUE) MAX_Y_COORDINATE_VALUE = (int) y;
            if (y < MIN_Y_COORDINATE_VALUE) MIN_Y_COORDINATE_VALUE = (int) y;
        }
    }

    private FlatFugire drawObject(Flat flat) {
        int width = (int) (0.8 * panel.getWidth());
        int height = (int) (0.8 * panel.getHeight());
        float x = flat.getCoordinates().getX();
        double y = flat.getCoordinates().getY();
        //Color color = Client.getClient().getUsername().equals(ticket.getOwner()) ? new Color(0, 255, 0) : new Color(255, 0, 0);

        int screenX = (int) ((x - MIN_X_COORDINATE_VALUE) * (width / (MAX_X_COORDINATE_VALUE - MIN_X_COORDINATE_VALUE)) + panel.getWidth() * 0.05);
        int screenY = (int) ((y - MIN_Y_COORDINATE_VALUE) * (height / (MAX_Y_COORDINATE_VALUE - MIN_Y_COORDINATE_VALUE)) + panel.getHeight() * 0.05);
        Dimension rectangleSize = evaluateRectangleSize(flat.getArea());
        FlatFugire rectangle = new FlatFugire(screenX, screenY, rectangleSize.width, rectangleSize.height, new Color(100, 255, 0), flat);
        //   panel.addRectangle(rectangle);
        return rectangle;
    }

    private Dimension evaluateRectangleSize(long area) {
        if (area > 10000) return new Dimension(panel.getWidth() / 40 + 110, panel.getHeight() / 40 + 55);
        if (area > 1000) return new Dimension(panel.getWidth() / 40 + 90, panel.getHeight() / 40 + 45);
        if (area > 100) return new Dimension(panel.getWidth() / 40 + 75, panel.getHeight() / 40 + 35);
        return new Dimension(panel.getWidth() / 40 + 60, panel.getHeight() / 40 + 100);
    }


    private void backButtonMouseReleased(MouseEvent e) {
        MainFrameManager.getInstance().switchPanel(new GeneralMenu(Client.getClient()).getPanel());
        isActive = false;
    }

    private void initComponents() {
        flatMgLabel = new JLabel();
        panel = new GraphicsPanel();
        backButton = new JButton();

        setBackground(new Color(60, 96, 205));
        setLayout(new GridBagLayout());
        GridBagConstraints table = new GridBagConstraints();

        //---- ticketMgLabel ----
        flatMgLabel.setText("Visualize Panel");
        flatMgLabel.setFont(new Font("Droid Sans Mono Dotted", Font.PLAIN, 48));
        flatMgLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        flatMgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        table.gridy = 0; table.gridx = 0;
        add(flatMgLabel, table);

        //======== visualizePanel ========
        {
            panel.setBackground(new Color(102, 102, 255));
            panel.setForeground(new Color(255, 51, 51));
            panel.setLayout(null);
            {
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel.getComponentCount(); i++) {
                    Rectangle bounds = panel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel.setMinimumSize(preferredSize);
                panel.setPreferredSize(preferredSize);
            }
        }
        table.gridy = 1; table.gridx = 0;
        table.ipadx = 1800;
        table.ipady = 700;
        add(panel, table);

        table = new GridBagConstraints();
        //---- backButton ----
        backButton.setText("Back");
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                backButtonMouseReleased(e);
            }
        });
        table.gridy = 2; table.gridx = 0;
        add(backButton, table);
    }

}
