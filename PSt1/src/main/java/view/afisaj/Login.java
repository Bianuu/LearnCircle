package view.afisaj;

import presenter.LoginP;
import view.resurse.ILogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login extends JFrame implements ActionListener, ILogin {
    private JPanel mainPanel;
    private JTextField nicknameTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JButton backButton;
    private LoginP loginP;
    private JLabel errorLabel;

    private Canvas canvasView;

    public Login(Canvas cv) {
        canvasView = cv;
        setMainPanel();
        loginP = new LoginP(this);
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
        this.setTitle("Login");

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setBackground(null);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("FONT", Font.BOLD, 40));
        springLayout.putConstraint(SpringLayout.WEST, titleLabel, (int) (mode.getWidth() * 0.45), SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 80, SpringLayout.NORTH, mainPanel);
        mainPanel.add(titleLabel);

        JLabel nicknameLabel = new JLabel("Porecla:");
        nicknameLabel.setBackground(null);
        nicknameLabel.setForeground(Color.BLACK);
        nicknameLabel.setFont(new Font("FONT", Font.BOLD, 25));
        springLayout.putConstraint(SpringLayout.EAST, nicknameLabel, -(int) (mode.getWidth() * 0.5), SpringLayout.EAST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, nicknameLabel, (int) (mode.getHeight() * 0.35), SpringLayout.NORTH, mainPanel);
        mainPanel.add(nicknameLabel);

        JLabel passwordLabel = new JLabel("Parola:");
        passwordLabel.setBackground(null);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("FONT", Font.BOLD, 25));
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, -(int) (mode.getWidth() * 0.5), SpringLayout.EAST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 15, SpringLayout.SOUTH, nicknameLabel);
        mainPanel.add(passwordLabel);

        nicknameTextField = new JTextField();
        nicknameTextField.setPreferredSize(new Dimension(140, 24));
        nicknameTextField.setBackground(Color.PINK);
        nicknameTextField.setForeground(Color.BLACK);
        nicknameTextField.setFont(new Font("Font", Font.PLAIN, 20));
        springLayout.putConstraint(SpringLayout.WEST, nicknameTextField, 10, SpringLayout.EAST, nicknameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, nicknameTextField, (int) (mode.getHeight() * 0.36), SpringLayout.NORTH, mainPanel);
        mainPanel.add(nicknameTextField);

        passwordTextField = new JPasswordField();
        passwordTextField.setPreferredSize(new Dimension(140, 24));
        passwordTextField.setBackground(Color.PINK);
        passwordTextField.setForeground(Color.BLACK);
        passwordTextField.setFont(new Font("Font", Font.PLAIN, 20));
        springLayout.putConstraint(SpringLayout.WEST, passwordTextField, 10, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordTextField, 18, SpringLayout.SOUTH, nicknameTextField);
        mainPanel.add(passwordTextField);

        loginButton = new JButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(140, 35));
        loginButton.setBackground(Color.PINK);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Font", Font.BOLD, 25));
        loginButton.setActionCommand("LOGIN");
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);
        springLayout.putConstraint(SpringLayout.WEST, loginButton, (int) (mode.getWidth() * 0.45), SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, 40, SpringLayout.SOUTH, passwordLabel);
        mainPanel.add(loginButton);

        errorLabel = new JLabel("Ceva nu a mers bine cu autentificarea ta.");
        errorLabel.setBackground(Color.RED);
        errorLabel.setForeground(Color.BLACK);
        errorLabel.setFont(new Font("FONT", Font.BOLD, 25));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, errorLabel, 0, SpringLayout.HORIZONTAL_CENTER, loginButton);
        springLayout.putConstraint(SpringLayout.NORTH, errorLabel, 0, SpringLayout.SOUTH, loginButton);
        errorLabel.setVisible(false);
        mainPanel.add(errorLabel);

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

    public String getNicknameField() {
        if (nicknameTextField.getText().isEmpty())
            return null;
        else return nicknameTextField.getText().trim();
    }

    public String getPasswordField() {
        if (passwordTextField.getPassword().length == 0)
            return null;
        else return Arrays.toString(passwordTextField.getPassword());
    }

    public void setNicknameTextField(String text) {
        this.nicknameTextField.setText(text);
    }

    public void setPasswordTextField(String text) {
        this.passwordTextField.setText(text);
    }

    public void getErrorLabel() {
        errorLabel.setVisible(true);
    }

    public Canvas getCanvasView() {
        return canvasView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("LOGIN")) {
            loginP.login();
        } else if (command.equals("BACK")) {
            loginP.back();
        }
    }
}
