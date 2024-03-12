package appointmentproducer;

public interface ServicePublisher {
	
	void addAppointment();
	void getAllAppointments();
	void getAppointmentByID();
	void deleteAppointmentByID();
	void updateAppointment();

}
