package inheritance.example;
import java.util.Scanner;

public class inheritance {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sms obj = new Sms("Gourav","123456789",6000);
        System.out.println("Enter the code:");
        Integer num = sc.nextInt();
        obj.message(num);
    }
}
//Multilevel Inheritance
class Kyc{
    private String name;
    private String accno;
    private Integer balance;

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
//Transaction class inherits Kyc class
class Transaction extends Kyc{
    Scanner sc = new Scanner(System.in);

    public void enquiry(){
        System.out.println("Name: "+ getName());
        System.out.println("Account Number: "+ getAccno());
        System.out.println("Balance: "+ getBalance());

    }
    public void recharge(){
        System.out.println("Enter the mobile number:");
        Long number = sc.nextLong();
        System.out.println("Enter the recharge amount:");
        Integer amount = sc.nextInt();
        if(amount<=getBalance()){
            System.out.println("Recharge successful....");
            setBalance(getBalance()-amount);
            System.out.println("Remaining balance: "+getBalance());
        }
        else {
            System.out.println("Insufficient Balance...");
        }
    }
    public void booking(){
        System.out.println("Ticket price: Rs.200\nEnter the number of tickets you want to purchase: ");
        Integer tickets = sc.nextInt();
        tickets *= 200;
        if(tickets<=getBalance()){
            System.out.println("Booking successful....");
            setBalance(getBalance()-tickets);
            System.out.println("Remaining balance: "+getBalance());
        }
        else {
            System.out.println("Insufficient Balance...");
        }
    }
}
//Sms class inherits Transaction class
class Sms extends Transaction{
    Scanner sc = new Scanner((System.in));

    public Sms(String name, String accno, Integer balance) {
        setName(name);
        setAccno(accno);
        setBalance(balance);
    }

    public void message(Integer num){
        if(num == 1901){
            enquiry();
        }
        else if(num == 2245){
            recharge();
        }
        else if(num == 7624){
            booking();
        }
        else {
            System.out.println("Invalid code...");
        }
    }

}