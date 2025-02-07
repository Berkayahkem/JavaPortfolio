
public class VIPTicket extends Ticket{
	public VIPTicket(String movieName,String CinemaName, String showDate,String showTime, int numberOfTicket) {
		super("loungeAccess",CinemaName, 20, movieName, showDate, showTime, numberOfTicket);
	}
}
