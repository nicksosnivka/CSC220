package lab04;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Testing class for generic Library.
 *
 */
public class LibraryGenericTest {

  public static void main(String[] args) {

    // test a library that uses names (String) to id patrons
    Library<String> lib1 = new Library<String>();
    lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib1.add(9780446580342L, "David Baldacci", "Simple Genius");

    String patron1 = "Jane Doe";

    if (!lib1.checkout(9780330351690L, patron1, 1, 1, 2008))
      System.err.println("TEST FAILED: first checkout");
    if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2008))
      System.err.println("TEST FAILED: second checkout");
    ArrayList<LibraryBook<String>> booksCheckedOut1 = lib1
        .lookup(patron1);
    if (booksCheckedOut1 == null
        || booksCheckedOut1.size() != 2
        || !booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer",
            "Into the Wild"))
        || !booksCheckedOut1.contains(new Book(9780374292799L,
            "Thomas L. Friedman", "The World is Flat"))
        || !booksCheckedOut1.get(0).getHolder().equals(patron1)
        || !booksCheckedOut1.get(0).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1))
        || !booksCheckedOut1.get(1).getHolder().equals(patron1)
        || !booksCheckedOut1.get(1).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1)))
      System.err.println("TEST FAILED: lookup(holder)");
    if (!lib1.checkin(patron1))
      System.err.println("TEST FAILED: checkin(holder)");

    // test a library that uses phone numbers (PhoneNumber) to id patrons
    Library<PhoneNumber> lib2 = new Library<PhoneNumber>();
    lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib2.add(9780446580342L, "David Baldacci", "Simple Genius");

    PhoneNumber patron2 = new PhoneNumber("305.555.1234");

    if (!lib2.checkout(9780330351690L, patron2, 1, 1, 2008))
      System.err.println("TEST FAILED: first checkout");
    if (!lib2.checkout(9780374292799L, patron2, 1, 1, 2008))
      System.err.println("TEST FAILED: second checkout");
    ArrayList<LibraryBook<PhoneNumber>> booksCheckedOut2 = lib2
        .lookup(patron2);
    if (booksCheckedOut2 == null
        || booksCheckedOut2.size() != 2
        || !booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer",
            "Into the Wild"))
        || !booksCheckedOut2.contains(new Book(9780374292799L,
            "Thomas L. Friedman", "The World is Flat"))
        || !booksCheckedOut2.get(0).getHolder().equals(patron2)
        || !booksCheckedOut2.get(0).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1))
        || !booksCheckedOut2.get(1).getHolder().equals(patron2)
        || !booksCheckedOut2.get(1).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1)))
      System.err.println("TEST FAILED: lookup(holder)");
    if (!lib2.checkin(patron2))                           
      System.err.println("TEST FAILED: checkin(holder)");
    
    System.out.println("Testing done.");
    
    // FILL IN for tests
    
    // FOR LAB: write tests for getInventoryList
    
    
    ArrayList<LibraryBook<String>> lib1Sorted = lib1.getInventoryList();
    
    for (int i = 0; i < lib1Sorted.size(); i++) {
    	long temp = lib1Sorted.get(i).getIsbn();
    	System.out.println(Long.toString(temp));
    }
    
    System.out.println("\n---------------------------------------\n");
    
    ArrayList<LibraryBook<PhoneNumber>> lib2Sorted = lib2.getInventoryList();
    
    for (int i = 0; i < lib2Sorted.size(); i++) {
    	long temp = lib2Sorted.get(i).getIsbn();
    	System.out.println(Long.toString(temp));
    }
    
    System.out.println("\n---------------------------------------\n");
    
    ArrayList<LibraryBook<String>> lib1Sorted2 = lib1.getOrderedByAuthor();
    
    for (int i = 0; i < lib1Sorted2.size(); i++) {
    	System.out.println(lib1Sorted2.get(i).getAuthor());
    }
    
    System.out.println("\n---------------------------------------\n");
    
    // test a medium library: you will use this for homework
    Library<String> lib3 = new Library<String>();    
    lib3.addAll("Mushroom_Publishing.txt");
    
    ArrayList<LibraryBook<String>> lib3AuthorSorted = lib3.getOrderedByAuthor();
    
    for (int i = 0; i < lib3AuthorSorted.size(); i++) {
    	System.out.println(lib3AuthorSorted.get(i));
    }
    
    lib3.checkout(9781843190042L, patron1, 9, 15, 2020);
    lib3.checkout(9781843190349L, "Nicky", 9, 13, 2020);
    lib3.checkout(9781843190516L, "Joe", 9, 14, 2020);
    lib3.checkout(9781843190998L, "Mike", 9, 12, 2020);
    
    ArrayList<LibraryBook<String>> lib3dateSorted = lib3.getOverdueList(9, 20, 2020);
    
    System.out.println("\n---------------------------------------\n");
    
    for (int i = 0; i < lib3dateSorted.size(); i++) {
    	System.out.println(lib3dateSorted.get(i));
    }
    
    
  }
}
