
public class PremiumTicket extends Ticket{
	public PremiumTicket(String movieName,String CinemaName, String showDate,String showTime, int numberOfTicket) {
		super("better",CinemaName, 15, movieName, showDate, showTime, numberOfTicket);
	}
}
