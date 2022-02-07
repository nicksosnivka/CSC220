package lab03;

// What imports do you need to include? Put them here.
import java.util.GregorianCalendar;

public class LibraryBook extends Book { 

	// A LibraryBook contains a holder (a String) and due date represented by
	// a GregorianCalendar
	
	public String holder;
	
	public GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
	
	}

	public String getHolder() {
		return this.holder;
	}
	
	
	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}
	
	public void checkin() {
		this.holder = null;
		this.dueDate = null;
	}
	
	public void checkout(String holder, GregorianCalendar dueDate){
		this.holder = holder;
		this.dueDate = dueDate;
	}	

	// Do not override the equals method in Book

}