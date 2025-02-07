import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Gui {
	public static ArrayList<Ticket> tickets = new ArrayList<>();
	public static ArrayList<Customer> customers = new ArrayList<>();

    public static void createGUI() {
        JFrame frame = new JFrame("Movie Ticket Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Movie Ticket Booking System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 5, 5));
        buttonPanel.setBackground(Color.WHITE);

        JButton addCustomerButton = new JButton("Add Customer");
        JButton createBookingButton = new JButton("Create Booking");
        JButton displayCustomersButton = new JButton("Display All Customers");
        JButton displayBookingsButton = new JButton("Display All Bookings");

        addCustomerButton.addActionListener(new AddCustomerAction());
        createBookingButton.addActionListener(new CreateBookingAction());
        displayCustomersButton.addActionListener(new DisplayCustomersAction());
        displayBookingsButton.addActionListener(new DisplayBookingsAction());

        buttonPanel.add(addCustomerButton);
        buttonPanel.add(createBookingButton);
        buttonPanel.add(displayCustomersButton);
        buttonPanel.add(displayBookingsButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static class AddCustomerAction implements ActionListener {
    	Customer customer;
    	String selectedType = null;
        @Override
        public void actionPerformed(ActionEvent e) {
            selectCustomerType();
        }
        
        private void selectCustomerType() {
            String[] customerTypes = {"Regular Customer", "Loyalty Member", "Corporate Client"};
            selectedType = (String) JOptionPane.showInputDialog(
                null,
                "Select Customer Type:",
                "Add Customer",
                JOptionPane.QUESTION_MESSAGE,
                null,
                customerTypes,
                customerTypes[0]
            );
            if (selectedType == null) {
                return;
            }
            
            String name = JOptionPane.showInputDialog("Enter Customer Name:");
            if (name == null) return;
            
        	String email = JOptionPane.showInputDialog("Enter Customer Email:");
        	if (email == null) return;
        	
            if (name == null || email == null) {
            	JOptionPane.showMessageDialog(null,"Name and Email are required!","Message",JOptionPane.ERROR_MESSAGE);
                } 
            else {
            	if(selectedType == "Regular Customer") {
            		customer = new RegularCustomer(1,name,email);
            	} else if(selectedType == "Loyalty Member") {
            		customer = new LoyaltyMember(2,name,email);
            	} else if(selectedType == "Corporate Client") {
            		customer = new CorporateClient(3,name,email);
            	}
            	customers.add(customer);
            	JOptionPane.showMessageDialog(null, "Customer added successfully!","Message" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    static class DisplayCustomersAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (customers.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No customers available.", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder customerList = new StringBuilder("Customers:\n");
                int i = 1;
                for (Customer c:customers) {
                    customerList.append(i).append(". ").append(c.getName()).append(" (").append(c.getEmail()).append(")").append("\n");
                    i++;
                }
                JOptionPane.showMessageDialog(null, customerList.toString(), "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    static class CreateBookingAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if (customers.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No customers available. Add a customer first.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        	else {
        		String[] ticketTypes = {"Regular", "Premium", "VIP", "IMAX"};
        		String selectedType = null;
            	String customerId = JOptionPane.showInputDialog("Enter Customer ID:");
            	if (customerId == null || customerId.trim().isEmpty()) {
            	    JOptionPane.showMessageDialog(null, "Customer ID cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            	    return;
            	}

            	int customerid;
            	try {
            	    customerid = Integer.parseInt(customerId);
            	} catch (NumberFormatException e1) {
            	    JOptionPane.showMessageDialog(null, "Invalid Customer ID! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            	    return;
            	}

            	try {
            	    validateCustomerId(customerid, customers);
            	} catch (InvalidCustomerException e1) {
            	    JOptionPane.showMessageDialog(null, "Customer not Found!", "Error", JOptionPane.ERROR_MESSAGE);
            	    return;
            	}
            	           	
            	String movieName = JOptionPane.showInputDialog("Enter Movie Name:");
            	if(movieName.equals(null) || movieName.trim().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "Movie Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            		return;
            	}
            	String cinemaName = JOptionPane.showInputDialog("Enter Cinema Name:");
            	if(cinemaName.equals(null) || cinemaName.trim().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "Cinema Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            		return;
            	}
            	String showDate = JOptionPane.showInputDialog("Enter Show Date (e.g., June 10):");
            	if(showDate.equals(null) || showDate.trim().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "Show Date cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            		return;
            	}
            	String showTime = JOptionPane.showInputDialog("Enter Show Time (e.g., 18:00:");
            	if(showTime.equals(null) || showTime.trim().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "Show Time cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            		return;
            	}
            	String numberoftickets11 = JOptionPane.showInputDialog("Enter Number of Tickets:");
            	if(numberoftickets11.equals(null) || numberoftickets11.trim().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "Number of Tickets cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            		return;
            	}
            	int numberofTickets = Integer.parseInt(numberoftickets11);
            	selectedType = (String) JOptionPane.showInputDialog(null, "Select Ticket Type:", null, JOptionPane.QUESTION_MESSAGE, null, ticketTypes, ticketTypes[0]);
                try {
                    validateTicketType(selectedType);
                } catch(InvalidTicketTypeException e1) {
                	JOptionPane.showMessageDialog(null, "Error: ", "Error", JOptionPane.ERROR_MESSAGE);
                	return;
                }
                int customerid1 = Integer.parseInt(customerId);
                Customer customerTemp1 = null;
                for(Customer c: customers) {
                	if(c.getCustomerID() == customerid1) {
                		customerTemp1 = c;
                		break;
                	}
                }
                    
                Ticket ticket1 = null;

                if ("Regular".equals(selectedType)) {
                	ticket1 = new RegularTicket(movieName,cinemaName, showDate, showTime, numberofTickets);
                } else if ("Premium".equals(selectedType)) {
                    ticket1 = new PremiumTicket(movieName,cinemaName, showDate, showTime, numberofTickets);
                } else if ("VIP".equals(selectedType)) {
                    ticket1 = new VIPTicket(movieName,cinemaName, showDate, showTime, numberofTickets);
                } else if ("IMAX".equals(selectedType)) {
                	ticket1 = new IMAXTicket(movieName,cinemaName, showDate, showTime, numberofTickets);
                }
                tickets.add(ticket1);
                
                if (selectedType == null||customerId == null || movieName == null || cinemaName == null || showDate == null || showTime == null || numberoftickets11 == null) {
                	JOptionPane.showMessageDialog(null,"You forgot to enter some information","Message",JOptionPane.ERROR_MESSAGE);
                    } 
                else {
                	JOptionPane.showMessageDialog(null, "Booking created successfully!\nOriginal Cost: $" + ticket1.calculateTotalPrice() + "\nDiscounted Cost: $" + customerTemp1.calculateDiscountedPrice(ticket1.calculateTotalPrice()),"Message" , JOptionPane.INFORMATION_MESSAGE);
                }
            }    	
        }
    }

    static class DisplayBookingsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if (tickets.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No bookings available.", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder ticketList = new StringBuilder("Bookings:\n");
                for (Ticket t:tickets) {
                    ticketList.append(t.displayInfo()).append("\n");
                }
                JOptionPane.showMessageDialog(null, ticketList.toString(), "Message", JOptionPane.INFORMATION_MESSAGE);
            }        
        }
    }
    
    public static void validateTicketType(String ticketType) throws InvalidTicketTypeException {
		if(!(ticketType.equals("Regular") || ticketType.equals("Premium") || ticketType.equals("VIP") || ticketType.equals("Balcony") || ticketType.equals("Box") || ticketType.equals("IMAX"))) {
			throw new InvalidTicketTypeException("Invalid ticket type: " + ticketType);
		}
	}
    
    public static void validateCustomerId(int customerId, List<Customer> customers) throws InvalidCustomerException {
		boolean isValid = false;
		for(Customer c: customers) {
			if(customerId == c.getCustomerID()) {
				isValid = true;
			}
		}
		if(!isValid)
			throw new InvalidCustomerException("Customer Id not found: " + customerId);
	}
}

