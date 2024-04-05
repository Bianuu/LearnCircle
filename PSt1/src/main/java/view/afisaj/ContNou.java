package view.afisaj;

import presenter.ContNouP;
import view.resurse.IContNou;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContNou extends JFrame implements ActionListener, IContNou {

    private JPanel mainPanel;

    private JTextField nameField;
    private JTextField surnameField;
    private JTextField nicknameField;
    private JPasswordField passwordField;
    private JButton requestButton;
    private JButton backButton;
    private Canvas canvasView;
    private ContNouP contNouP;

    public ContNou(Canvas cv) {
        canvasView = cv;
        contNouP = new ContNouP(this);
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
        this.setTitle("Cont Nou");

        JLabel titleLabel = new JLabel("Creeaza un cont");
        titleLabel.setBackground(null);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("FONT", Font.BOLD, 40));
        springLayout.putConstraint(SpringLayout.WEST, titleLabel, (int) (mode.getWidth() * 0.4), SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 80, SpringLayout.NORTH, mainPanel);
        mainPanel.add(titleLabel);

        JLabel nameLabel = new JLabel("Prenume:");
        nameLabel.setBackground(null);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("FONT", Font.BOLD, 25));
        springLayout.putConstraint(SpringLayout.WEST, nameLabel, 500, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 100, SpringLayout.NORTH, titleLabel);
        mainPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(140, 25));
        nameField.setBackground(Color.PINK);
        nameField.setForeground(Color.BLACK);
        nameField.setFont(new Font("Font", Font.PLAIN, 20));
        springLayout.putConstraint(SpringLayout.WEST, nameField, 10, SpringLayout.EAST, nameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, nameField, 100, SpringLayout.NORTH, titleLabel);
        mainPanel.add(nameField);

        JLabel surnameLabel = new JLabel("Nume:");
        surnameLabel.setBackground(null);
        surnameLabel.setForeground(Color.BLACK);
        surnameLabel.setFont(new Font("FONT", Font.BOLD, 25));
        springLayout.putConstraint(SpringLayout.WEST, surnameLabel, 500, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, surnameLabel, 10, SpringLayout.SOUTH, nameLabel);
        mainPanel.add(surnameLabel);

        surnameField = new JTextField();
        surnameField.setPreferredSize(new Dimension(140, 25));
        surnameField.setBackground(Color.PINK);
        surnameField.setForeground(Color.BLACK);
        surnameField.setFont(new Font("Font", Font.PLAIN, 20));
        springLayout.putConstraint(SpringLayout.WEST, surnameField, 10, SpringLayout.EAST, surnameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, surnameField, 10, SpringLayout.SOUTH, nameLabel);
        mainPanel.add(surnameField);

        JLabel nicknameLabel = new JLabel("Porecla:");
        nicknameLabel.setBackground(null);
        nicknameLabel.setForeground(Color.BLACK);
        nicknameLabel.setFont(new Font("FONT", Font.BOLD, 25));
        springLayout.putConstraint(SpringLayout.WEST, nicknameLabel, 500, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, nicknameLabel, 10, SpringLayout.SOUTH, surnameLabel);
        mainPanel.add(nicknameLabel);

        nicknameField = new JTextField();
        nicknameField.setPreferredSize(new Dimension(140, 25));
        nicknameField.setBackground(Color.PINK);
        nicknameField.setForeground(Color.BLACK);
        nicknameField.setFont(new Font("Font", Font.PLAIN, 20));
        springLayout.putConstraint(SpringLayout.WEST, nicknameField, 10, SpringLayout.EAST, nicknameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, nicknameField, 10, SpringLayout.SOUTH, surnameLabel);
        mainPanel.add(nicknameField);

        JLabel passwordLabel = new JLabel("Parola:");
        passwordLabel.setBackground(null);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("FONT", Font.BOLD, 25));
        springLayout.putConstraint(SpringLayout.WEST, passwordLabel, 500, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 10, SpringLayout.SOUTH, nicknameLabel);
        mainPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(140, 25));
        passwordField.setBackground(Color.PINK);
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(new Font("Font", Font.PLAIN, 20));
        springLayout.putConstraint(SpringLayout.WEST, passwordField, 10, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordField, 10, SpringLayout.SOUTH, nicknameLabel);
        mainPanel.add(passwordField);

        requestButton = new JButton("<html>TRIMITE</br> SOLICITAREA</html>");
        requestButton.setPreferredSize(new Dimension(180, 60));
        requestButton.setBackground(Color.PINK);
        requestButton.setForeground(Color.BLACK);
        requestButton.setFont(new Font("Font", Font.BOLD, 25));
        requestButton.setActionCommand("REQUEST");
        requestButton.addActionListener(this);
        requestButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST, requestButton, (int) (mode.getWidth() * 0.45), SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, requestButton, 40, SpringLayout.SOUTH, passwordLabel);
        mainPanel.add(requestButton);

        backButton = new JButton("BACK");
        backButton.setPreferredSize(new Dimension(140, 60));
        backButton.setBackground(Color.PINK);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Font", Font.BOLD, 25));
        backButton.setActionCommand("BACK");
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST, backButton, (int) (mode.getWidth() * 0.45), SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, backButton, 140, SpringLayout.SOUTH, passwordLabel);
        mainPanel.add(backButton);

        this.setContentPane(this.mainPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("REQUEST")) {
            contNouP.request();
        } else if (command.equals("BACK")) {
            contNouP.back();
        }
    }

    public String getNameField() {
        return nameField.getText();
    }

    public String getSurnameField() {
        return surnameField.getText();
    }

    public String getNicknameField() {
        return nicknameField.getText();
    }

    public char[] getPasswordField() {
        return passwordField.getPassword();
    }

    public Canvas getCanvasView() {
        return canvasView;
    }
}
