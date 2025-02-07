
public class RegularTicket extends Ticket {
	public RegularTicket(String movieName,String CinemaName, String showDate,String showTime, int numberOfTicket) {
		super("standart",CinemaName, 10, movieName, showDate, showTime, numberOfTicket);
	}
}
