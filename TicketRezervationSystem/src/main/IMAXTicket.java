
public class IMAXTicket extends Ticket{
	public IMAXTicket(String movieName,String CinemaName, String showDate,String showTime, int numberOfTicket) {
		super("largeFormat",CinemaName, 35, movieName, showDate, showTime, numberOfTicket);
	}
}
