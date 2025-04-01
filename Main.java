import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bus_booking";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Mysql@123";

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 500);
            frame.setVisible(true);
        });
    }

    public Main() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(createMainMenuPanel(), "MainMenu");
        mainPanel.add(createUserLoginPanel(), "UserLogin");
        mainPanel.add(createAdminLoginPanel(), "AdminLogin");
        mainPanel.add(createRegisterPanel(), "Register");
        mainPanel.add(createUserMenuPanel(), "UserMenu");
        mainPanel.add(createAdminMenuPanel(), "AdminMenu");

        add(mainPanel);
        cardLayout.show(mainPanel, "MainMenu");
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Add padding between components

        // Heading
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel headingLabel = new JLabel("BUS BOOKING SYSTEM");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(headingLabel, gbc);

        // Buttons
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton userLoginButton = new JButton("User Login");
        JButton adminLoginButton = new JButton("Admin Login");
        JButton registerButton = new JButton("Register");
        JButton exitButton = new JButton("Exit");

        userLoginButton.addActionListener(e -> cardLayout.show(mainPanel, "UserLogin"));
        adminLoginButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminLogin"));
        registerButton.addActionListener(e -> cardLayout.show(mainPanel, "Register"));
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(userLoginButton, gbc);

        gbc.gridy++;
        panel.add(adminLoginButton, gbc);

        gbc.gridy++;
        panel.add(registerButton, gbc);

        gbc.gridy++;
        panel.add(exitButton, gbc);

        return panel;
    }

    private JPanel createUserLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add padding between components

        // Username components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        // Password components
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JPasswordField passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        panel.add(loginButton, gbc);

        // Back button
        gbc.gridy = 3;
        JButton backButton = new JButton("Back");
        panel.add(backButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (isValidUser(username, password, false)) {
                JOptionPane.showMessageDialog(this, "User login successful!");
                cardLayout.show(mainPanel, "UserMenu");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));
        return panel;
    }

    private JPanel createAdminLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add padding between components

        // Admin Username components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Admin Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        // Admin Password components
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Admin Password:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JPasswordField passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        panel.add(loginButton, gbc);

        // Back button
        gbc.gridy = 3;
        JButton backButton = new JButton("Back");
        panel.add(backButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if ((username.equals("admin")) || (password.equals("12345"))) {
                JOptionPane.showMessageDialog(this, "Admin login successful!");
                cardLayout.show(mainPanel, "AdminMenu");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));
        return panel;
    }


    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add padding between components

        // New Username components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("New Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        // New Password components
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("New Password:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JPasswordField passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Register button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton registerButton = new JButton("Register");
        panel.add(registerButton, gbc);

        // Back button
        gbc.gridy = 3;
        JButton backButton = new JButton("Back");
        panel.add(backButton, gbc);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (createUser(username, password, false)) {
                JOptionPane.showMessageDialog(this, "User registered successfully!");
                cardLayout.show(mainPanel, "MainMenu");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed.");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));
        return panel;
    }


    private JPanel createUserMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Add padding between components

        // Heading
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel headingLabel = new JLabel("USER MENU");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(headingLabel, gbc);

        // Buttons
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton viewBusesButton = new JButton("View Buses");
        JButton bookBusButton = new JButton("Book Bus");
        JButton cancelBusBookingButton = new JButton("Cancel Bus Booking");
        JButton logoutButton = new JButton("Logout");

        viewBusesButton.addActionListener(e -> viewBuses());
        bookBusButton.addActionListener(e -> bookBus());
        cancelBusBookingButton.addActionListener(e -> cancelBusBooking());
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));

        panel.add(viewBusesButton, gbc);

        gbc.gridy++;
        panel.add(bookBusButton, gbc);

        gbc.gridy++;
        panel.add(cancelBusBookingButton, gbc);

        gbc.gridy++;
        panel.add(logoutButton, gbc);

        return panel;
    }

    private JPanel createAdminMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Add padding between components

        // Heading
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel headingLabel = new JLabel("ADMIN MENU");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(headingLabel, gbc);

        // Buttons
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton addBusButton = new JButton("Add Bus");
        JButton removeBusButton = new JButton("Remove Bus");
        JButton viewBusesButton = new JButton("View Buses");
        JButton removeUserButton = new JButton("Remove User");
        JButton logoutButton = new JButton("Logout");

        addBusButton.addActionListener(e -> addBus());
        removeBusButton.addActionListener(e -> removeBus());
        viewBusesButton.addActionListener(e -> viewBuses());
        removeUserButton.addActionListener(e -> removeUser());
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));

        panel.add(addBusButton, gbc);

        gbc.gridy++;
        panel.add(removeBusButton, gbc);

        gbc.gridy++;
        panel.add(viewBusesButton, gbc);

        gbc.gridy++;
        panel.add(removeUserButton, gbc);

        gbc.gridy++;
        panel.add(logoutButton, gbc);

        return panel;
    }

    private boolean createUser(String username, String password, boolean isAdmin) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password, isAdmin) VALUES (?, ?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setBoolean(3, isAdmin);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean isValidUser(String username, String password, boolean isAdmin) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ? AND isAdmin = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setBoolean(3, isAdmin);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void viewBuses() {
        StringBuilder busesInfo = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM buses")) {

            while (rs.next()) {
                busesInfo.append("Bus ID: ").append(rs.getInt("bus_id")).append("\n")
                        .append("Bus Number: ").append(rs.getString("bus_number")).append("\n")
                        .append("Departure Time: ").append(rs.getString("departure_time")).append("\n")
                        .append("Arrival Time: ").append(rs.getString("arrival_time")).append("\n")
                        .append("Source: ").append(rs.getString("source")).append("\n")
                        .append("Destination: ").append(rs.getString("destination")).append("\n")
                        .append("Total Seats: ").append(rs.getInt("total_seats")).append("\n")
                        .append("------------------------\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, busesInfo.toString());
    }

    private void addBus() {
        JTextField busNumberField = new JTextField();
        JTextField departureTimeField = new JTextField();
        JTextField arrivalTimeField = new JTextField();
        JTextField sourceField = new JTextField();
        JTextField destinationField = new JTextField();
        JTextField totalSeatsField = new JTextField();

        Object[] message = {
                "Bus Number:", busNumberField,
                "Departure Time:", departureTimeField,
                "Arrival Time:", arrivalTimeField,
                "Source:", sourceField,
                "Destination:", destinationField,
                "Total Seats:", totalSeatsField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Bus", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO buses (bus_number, departure_time, arrival_time, source, destination, total_seats) VALUES (?, ?, ?, ?, ?, ?)")) {

                stmt.setString(1, busNumberField.getText());
                stmt.setString(2, departureTimeField.getText());
                stmt.setString(3, arrivalTimeField.getText());
                stmt.setString(4, sourceField.getText());
                stmt.setString(5, destinationField.getText());
                stmt.setInt(6, Integer.parseInt(totalSeatsField.getText()));
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Bus added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add bus.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void removeBus() {
        String busId = JOptionPane.showInputDialog(this, "Enter Bus ID to remove:");
        if (busId != null) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM buses WHERE bus_id = ?")) {

                stmt.setInt(1, Integer.parseInt(busId));
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Bus removed successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove bus.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void removeUser() {
        String username = JOptionPane.showInputDialog(this, "Enter Username to remove:");
        if (username != null) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE username = ?")) {

                stmt.setString(1, username);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "User removed successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove user.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void bookBus() {
        String busId = JOptionPane.showInputDialog(this, "Enter Bus ID to book:");
        String username = JOptionPane.showInputDialog(this, "Enter your Username:");

        if (busId != null && username != null) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO bookings (user_id, bus_id) SELECT user_id, ? FROM users WHERE username = ?")) {

                stmt.setInt(1, Integer.parseInt(busId));
                stmt.setString(2, username);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Bus booked successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to book bus.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void cancelBusBooking() {
        // Get the list of buses booked by the user
        String username = JOptionPane.showInputDialog(this, "Enter your Username:");
        if (username != null) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM buses WHERE bus_id IN (SELECT bus_id FROM bookings WHERE user_id = (SELECT user_id FROM users WHERE username = ?))")) {

                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(this, "You haven't booked any buses yet.");
                    return;
                }

                // Display the list of booked buses
                StringBuilder bookedBusesInfo = new StringBuilder("Buses booked by you:\n\n");
                while (rs.next()) {
                    bookedBusesInfo.append("Bus ID: ").append(rs.getInt("bus_id")).append("\n")
                            .append("Bus Number: ").append(rs.getString("bus_number")).append("\n")
                            .append("Departure Time: ").append(rs.getString("departure_time")).append("\n")
                            .append("Arrival Time: ").append(rs.getString("arrival_time")).append("\n")
                            .append("Source: ").append(rs.getString("source")).append("\n")
                            .append("Destination: ").append(rs.getString("destination")).append("\n")
                            .append("Total Seats: ").append(rs.getInt("total_seats")).append("\n")
                            .append("------------------------\n");
                }

                // Ask the user to choose a bus to cancel booking
                String busIdToCancel = JOptionPane.showInputDialog(this, bookedBusesInfo.toString() + "\nEnter Bus ID to cancel booking:");
                if (busIdToCancel != null) {
                    try (PreparedStatement cancelStmt = conn.prepareStatement("DELETE FROM bookings WHERE user_id = (SELECT user_id FROM users WHERE username = ?) AND bus_id = ?")) {
                        cancelStmt.setString(1, username);
                        cancelStmt.setInt(2, Integer.parseInt(busIdToCancel));
                        int rowsAffected = cancelStmt.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(this, "Bus booking canceled successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to cancel bus booking.");
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}