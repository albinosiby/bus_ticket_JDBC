# Bus Booking System - Java Swing Application

## Overview

This is a Java Swing-based Bus Booking System that allows users to:
- Register new accounts
- Login as regular users or administrators
- View available buses
- Book and cancel bus tickets
- (For admins) Manage buses and user accounts

The application uses MySQL for data storage and follows a multi-panel interface with CardLayout for smooth navigation.

## Features

### User Features
- User registration and login
- View all available buses
- Book a bus ticket
- Cancel existing bookings

### Admin Features
- Admin login (username: "admin", password: "12345")
- Add new buses to the system
- Remove existing buses
- View all buses
- Remove user accounts

## Prerequisites

Before running the application, ensure you have:

1. **Java Development Kit (JDK) 8 or later**
2. **MySQL Server** installed and running
3. **MySQL Connector/J** (JDBC driver for MySQL)

## Database Setup

1. Create a MySQL database named `bus_booking`:
   ```sql
   CREATE DATABASE bus_booking;
   ```

2. Create the required tables:
   ```sql
   USE bus_booking;
   
   CREATE TABLE users (
       user_id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) NOT NULL UNIQUE,
       password VARCHAR(50) NOT NULL,
       isAdmin BOOLEAN DEFAULT FALSE
   );
   
   CREATE TABLE buses (
       bus_id INT AUTO_INCREMENT PRIMARY KEY,
       bus_number VARCHAR(20) NOT NULL,
       departure_time VARCHAR(20),
       arrival_time VARCHAR(20),
       source VARCHAR(50),
       destination VARCHAR(50),
       total_seats INT
   );
   
   CREATE TABLE bookings (
       booking_id INT AUTO_INCREMENT PRIMARY KEY,
       user_id INT,
       bus_id INT,
       FOREIGN KEY (user_id) REFERENCES users(user_id),
       FOREIGN KEY (bus_id) REFERENCES buses(bus_id)
   );
   ```

3. Create an admin account (optional):
   ```sql
   INSERT INTO users (username, password, isAdmin) VALUES ('admin', '12345', TRUE);
   ```

## Configuration

Update the database connection details in `Main.java` if needed:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/bus_booking";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "Mysql@123";
```

## How to Run

1. Compile the Java files:
   ```bash
   javac Main.java
   ```

2. Run the application:
   ```bash
   java Main
   ```

## Application Flow

1. **Main Menu**: Choose between User Login, Admin Login, Register, or Exit
2. **User Login/Register**: Access user features after authentication
3. **Admin Login**: Access admin features after authentication
4. **Navigation**: Use the Back/Logout buttons to return to previous screens


## Troubleshooting

1. **Database Connection Issues**:
   - Verify MySQL server is running
   - Check database credentials in the code
   - Ensure MySQL Connector/J is in the classpath

2. **SQL Errors**:
   - Verify the database schema matches the expected structure
   - Check for duplicate usernames during registration

## Future Enhancements

1. Add seat selection functionality
2. Implement payment processing
3. Add search/filter options for buses
4. Improve UI with better styling
5. Add input validation

## License

This project is open-source and available under the [MIT License](LICENSE).
