
import java.lang.*;
import java.util.*;

class Customer {
    private String name;
    private Vector rentals = new Vector();
    public Customer (String newname){
        name = newname;
    };
    public void addRental(Rental arg) {
        rentals.addElement(arg);
    };
    public String getName (){
        return name;
    };
    private double amountFor(Rental aRental) {
        return aRental.getCharge();
    }
    public String statement() {

        int frequentRenterPoints = 0;
        Enumeration enum_rentals = rentals.elements();	    
        String result = "Rental Record for " + this.getName() + "\n";
        result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";

        while (enum_rentals.hasMoreElements()) {

            Rental each = (Rental) enum_rentals.nextElement();
            //determine amounts for each line
            frequentRenterPoints += each.getFrequentRenterPoints(frequentRenterPoints, each);
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t"  + String.valueOf(each.getCharge()) + "\n";

        }
        //add footer lines
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalfrequentRenterPoints()) + " frequent renter points";
        return result;
    }

    private int getTotalfrequentRenterPoints() {
        int result = 0;
        Enumeration enum_rentals = rentals.elements();
        while (enum_rentals.hasMoreElements()) {
            Rental each = (Rental) enum_rentals.nextElement();
            result += each.getFrequentRenterPoints(result, each);
        }
        return result;

    }

    public double getTotalCharge() {
        double result = 0;
        Enumeration enum_rentals = rentals.elements();
        while (enum_rentals.hasMoreElements()) {
            Rental each = (Rental) enum_rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }
    public String htmlStatement() {
        Enumeration enum_rentals = rentals.elements();
        String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";
        while (enum_rentals.hasMoreElements()) {
            Rental each = (Rental) enum_rentals.nextElement();
            //show figures for each rental
            result += each.getMovie().getTitle()+ ": " +
                    String.valueOf(each.getCharge()) + "<BR>\n";
        }
        //add footer lines
        result += "<P>You owe <EM>" + String.valueOf(getTotalCharge()) +
                "</EM><P>\n";
        result += "On this rental you earned <EM>" +
                String.valueOf(getTotalfrequentRenterPoints()) +
                "</EM> frequent renter points<P>";
        return result;
    }

}
    