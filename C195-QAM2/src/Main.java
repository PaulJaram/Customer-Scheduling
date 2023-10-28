import dao.*;
import model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();

       AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
       ContactDAO contactDAO = new ContactDAOImpl();
       CountryDAO countryDAO = new CountryDAOImpl();
       CustomerDAO customerDAO = new CustomerDAOImpl();
       FirstLevelDivisionDAO firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();
       UserDAO userDAO = new UserDAOImpl();

       /*List<Appointment> appointments = appointmentDAO.getAll();
       List<Contact> contacts = contactDAO.getAll();
       List<Country> countries = countryDAO.getAll();
       List<Customer>  customers = customerDAO.getAll();
       List<FirstLevelDivision> divisions = firstLevelDivisionDAO.getAll();
       List<User> users = userDAO.getAll();
*/
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
/*       Appointment appointment = new Appointment(1000, "test", "test", "ur moms house", "pimples", stamp, stamp, stamp, "ur mom", stamp, "ur mom", 1, 2, 1);
       Contact contact = new Contact(1000, "Paul", "jaramillo.paul12@gmail.com");
       Country country = new Country(1000, "Utopia", stamp, "ur mom", stamp, "ur mom");
       Customer customer = new Customer(1000, "ur mom", "ur moms house", "00000", "o", stamp, "ur mom", stamp, "ur mom", 1);
       FirstLevelDivision division = new FirstLevelDivision(1000, "ur mom", stamp, "ur mom", stamp, "ur mom", 1);*/
       User user = new User(1, "ur face", "password", stamp, "ur mom",  stamp, "ur mom");


       /*appointmentDAO.insert(appointment);
       contactDAO.insert(contact);
       countryDAO.insert(country);
       customerDAO.insert(customer);
       firstLevelDivisionDAO.insert(division);*/
       userDAO.insert(user);
        System.out.println(user);

       JDBC.closeConnection();


    }
}