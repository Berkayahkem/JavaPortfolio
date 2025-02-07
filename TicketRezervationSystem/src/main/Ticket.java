public class Ticket implements Comparable<Ticket>{
	public int id;
	protected int ticketPrice;
	protected String seatType;
	protected String CinemaName;
	protected String movieName;
	protected String showDate;
	protected String showTime;
	protected int numberOfTicket;
	protected static int totalNumOfBookings = 0;
	protected int TotalPrice = 0;
	protected int discountedPrice = 0;
	
	public Ticket(String seatType,String CinemaName, int ticketPrice, String movieName, String showDate,String showTime, int numberOfTicket) {
        this.seatType = seatType;
        this.CinemaName = CinemaName;
        this.ticketPrice = ticketPrice;
		this.movieName = movieName;
        this.showDate = showDate;
        this.showTime = showTime;
        this.numberOfTicket = numberOfTicket;
        totalNumOfBookings++;
	}
	
	public String getMovieName() {
        return movieName;
    }

    public String getShowDate() {
        return showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public String getSeatType() {
        return seatType;
    }
    
    public String getCinemaName() {
    	return CinemaName;
    }
    
    public String getMonth() {
    	String[] parts = showDate.split(" ");
        String month = parts[0];
        return month;
	}
	
	public int calculateTotalPrice() {
		this.TotalPrice = ticketPrice * numberOfTicket;
		return TotalPrice;
    }
	
	public int compareTo(Ticket t) {
		return this.TotalPrice - t.calculateTotalPrice();
	}
	
	public String displayInfo() {
		return ("Movie Name: " + getMovieName() + ", Cinema: " + getCinemaName() + ", Date: " + getShowDate() + ", Time: " + getShowTime() + ", Tickets: " + numberOfTicket);
	}
}
