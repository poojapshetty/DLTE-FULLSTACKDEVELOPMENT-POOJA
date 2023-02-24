package encapsulation.example;
import java.util.Scanner;
public class Encapsulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer();
        System.out.println("Enter your name:");
        customer.setName(sc.nextLine());
        System.out.println("Enter PAN Number:");
        customer.setPan(sc.nextLine());
        System.out.println("Enter Aadhaar Number:");
        customer.setAadhaar(sc.nextLine());
        System.out.println("Enter your profession:");
        customer.setProfession(sc.nextLine());
        System.out.println("Enter your CIBIL score:");
        customer.setCibil(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter Contact Number:");
        customer.setContact(sc.nextLine());

        System.out.println("\nYour loan request has been received with following details,");
        System.out.println("Name: "+customer.getName()+"\nPAN: "+customer.getPan()+"\nAadhaar Number: "+customer.getAadhaar());
        System.out.println("Profession: "+customer.getProfession()+"\nCIBIL Score: "+customer.getCibil()+"\nContact Number: "+customer.getContact());



    }
}
class Customer{
    private String name;
    private String pan;
    private String aadhaar;
    private String profession;
    private Integer cibil;
    private String contact;

    //Default Constructor
    public Customer() {}

    //Parameterised constructor
    public Customer(String name, String pan, String aadhaar, String profession, Integer cibil, String contact) {
        this.name = name;
        this.pan = pan;
        this.aadhaar = aadhaar;
        this.profession = profession;
        this.cibil = cibil;
        this.contact = contact;
    }

    //Getter methods
    String getName(){
        return this.name;
    }
    String getPan(){
        return this.pan;
    }
    String getAadhaar(){
        return this.aadhaar;
    }
    String getProfession(){
        return this.profession;
    }
    Integer getCibil(){
        return this.cibil;
    }
    String getContact(){
        return this.contact;
    }

    //Setter Methods
    void setName(String name){
        this.name = name;
    }
    void setPan(String pan){
        this.pan = pan;
    }
    void setAadhaar(String aadhaar){
        this.aadhaar = aadhaar;
    }
    void setProfession(String profession){
        this.profession = profession;
    }
    void  setCibil(int cibil){
        this.cibil = cibil;
    }
    void setContact(String contact){
        this.contact = contact;
    }

}

