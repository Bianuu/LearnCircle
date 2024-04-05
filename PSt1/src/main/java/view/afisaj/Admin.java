package view.afisaj;

import presenter.AdminP;
import view.resurse.IAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin extends JFrame implements ActionListener, IAdmin {

    private JPanel mainPanel;
    private JButton approveButton;
    private JButton deleteButton;
    private JButton seeAllButton;
    private JButton seeRequestButton;
    private AdminP adminP;
    private JTable displayTable;

    private JScrollPane scrollPane;

    private String nickname;

    private Canvas canvasView;

    public Admin(Canvas cv) {
        canvasView = cv;
        adminP = new AdminP(this);
        displayTable = new JTable();
        setMainPanel();
    }

    private void setMainPanel() {
        SpringLayout springLayout = new SpringLayout();
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        mainPanel = new JPanel(springLayout);
        mainPanel.setBackground(Color.lightGray);
        this.setSize(mode.getWidth(), mode.getHeight());

        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(0, 0);
        this.setTitle("Fereastra Admin");

        approveButton = setButton("<html>ACCEPTA<br />CEREREA</html>");
        approveButton.setActionCommand("ACCEPTA");
        approveButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST, approveButton, 400, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, approveButton, 15, SpringLayout.NORTH, mainPanel);
        mainPanel.add(approveButton);

        deleteButton = setButton("STERGE");
        deleteButton.setActionCommand("STERGE");
        deleteButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST, deleteButton, 15, SpringLayout.EAST, approveButton);
        springLayout.putConstraint(SpringLayout.NORTH, deleteButton, 15, SpringLayout.NORTH, mainPanel);
        mainPanel.add(deleteButton);

        seeAllButton = setButton("TOTI ELEVII");
        seeAllButton.setActionCommand("SEE_ALL");
        seeAllButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST, seeAllButton, 15, SpringLayout.EAST, deleteButton);
        springLayout.putConstraint(SpringLayout.NORTH, seeAllButton, 15, SpringLayout.NORTH, mainPanel);
        mainPanel.add(seeAllButton);

        seeRequestButton = setButton("<html>IN<br />ASTEPTARE</html>");
        seeRequestButton.setActionCommand("SEE_REQUESTS");
        seeRequestButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST, seeRequestButton, 15, SpringLayout.EAST, seeAllButton);
        springLayout.putConstraint(SpringLayout.NORTH, seeRequestButton, 15, SpringLayout.NORTH, mainPanel);
        mainPanel.add(seeRequestButton);

        JButton backButton = setButton("BACK");
        backButton.setActionCommand("BACK");
        backButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.NORTH, backButton, 15, SpringLayout.NORTH, mainPanel);
        springLayout.putConstraint(SpringLayout.EAST, backButton, -15, SpringLayout.EAST, mainPanel);
        mainPanel.add(backButton);

        scrollPane = new JScrollPane(displayTable);
        scrollPane.setPreferredSize(new Dimension(1140, 500));
        scrollPane.setFont(new Font("FONT", Font.PLAIN, 10));
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setForeground(Color.BLACK);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 70, SpringLayout.SOUTH, deleteButton);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 70, SpringLayout.WEST, mainPanel);
        mainPanel.add(scrollPane);

        displayTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = displayTable.rowAtPoint(e.getPoint());
                nickname = displayTable.getValueAt(row, 2).toString();
            }
        });

        this.setContentPane(this.mainPanel);
        this.setVisible(true);
    }

    private JButton setButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.PINK);
        button.setForeground(Color.BLACK);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setPreferredSize(new Dimension(100, 50));
        button.setFocusable(false);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("ACCEPTA")) {
            adminP.approve();
            updateSeeRequests();
        } else if (command.equals("SEE_ALL")) {
            updateSeeAll();
        } else if (command.equals("SEE_REQUESTS")) {
            updateSeeRequests();
        } else if (command.equals("STERGE")) {
            adminP.delete();
            updateSeeAll();
        } else if (command.equals("BACK")) {
            adminP.back();
        }
    }

    private void updateSeeRequests() {
        DefaultTableModel dtm = adminP.seeRequests();
        displayTable.setModel(dtm);
        scrollPane.repaint();
    }

    private void updateSeeAll() {
        DefaultTableModel dtm = adminP.seeAll();
        displayTable.setModel(dtm);
        scrollPane.repaint();
    }

    public String getNickname() {
        return nickname;
    }

    public Canvas getCanvasView() {
        return canvasView;
    }
}
