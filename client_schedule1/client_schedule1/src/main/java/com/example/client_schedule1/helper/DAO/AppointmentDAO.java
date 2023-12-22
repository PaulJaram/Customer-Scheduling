package com.example.client_schedule1.helper.DAO;


import com.example.client_schedule1.model.Appointment;


import java.sql.SQLException;
/**This is the appointment dao interface*/
public interface AppointmentDAO extends com.example.client_schedule1.helper.DAO.DAO<Appointment> {
    /**this is implemented to delete an appointment from a database.
     * @param appointment the appointment to be deleted.*/
    int delete(Appointment appointment) throws SQLException;
}
