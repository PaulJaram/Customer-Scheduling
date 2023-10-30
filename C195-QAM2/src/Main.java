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
//       Appointment appointment = new Appointment(3, "HI", "test", "ur moms house", "pimples", stamp, stamp, stamp, "ur mom", stamp, "ur mom", 1, 2, 1);
//       Contact contact = new Contact(4, "PJ", "jaramillo.paul12@gmail.com");
//       Country country = new Country(4, "Utopia", stamp, "ur dad", stamp, "ur mom");
//       Customer customer = new Customer(4, "ur dad", "ur moms house", "00000", "o", stamp, "ur mom", stamp, "ur mom", 1);
//       FirstLevelDivision division = new FirstLevelDivision(105, "ur dad", stamp, "ur mom", stamp, "ur mom", 1);
//       User user = new User(3, "ur dad", "password", stamp, "ur mom",  stamp, "ur mom");


//       appointmentDAO.delete(appointment);
//       contactDAO.delete(contact);
//       countryDAO.delete(country);
//       customerDAO.delete(customer);
//       firstLevelDivisionDAO.delete(division);
//       userDAO.delete(user);
//        System.out.println();

       JDBC.closeConnection();


    }
}