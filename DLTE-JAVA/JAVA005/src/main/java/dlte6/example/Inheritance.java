
    import java.util.Scanner;
class BankDetails {
    private String accno;
    private String name;
    private long balance;
    Scanner sc = new Scanner(System.in);
    //method to open new account
    public void openAccount() {
        System.out.print("Enter Account No: ");
        accno = sc.next();
        System.out.print("Enter Name: ");
        name = sc.next();
        System.out.print("Enter Balance: ");
        balance = sc.nextLong();
    }
    //method to display account details
    public void showAccount() {
        System.out.println("Name of account holder: " + name);
        System.out.println("Account no.: " + accno);
        System.out.println("Balance: " + balance);
    }
    //method to deposit money
    public void deposit() {
        long amt;
        System.out.println("Enter the amount you want to deposit: ");
        amt = sc.nextLong();
        balance = balance + amt;
    }
    //method to withdraw money
    public void withdrawal() {
        long amt;
        System.out.println("Enter the amount you want to withdraw: ");
        amt = sc.nextLong();
        if (balance >= amt) {
            balance = balance - amt;
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!" );
        }
    }
    //method to search an account number
    public boolean search(String ac_no) {
        if (accno.equals(ac_no)) {
            showAccount();
            return (true);
        }
        return (false);
    }
}
public class BankingApp {
    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);
        //create initial accounts
        System.out.print("How many number of customers do you want to input? ");
        int n = sc.nextInt();
        BankDetails C[] = new BankDetails[n];
        for (int i = 0; i < C.length; i++) {
            C[i] = new BankDetails();
            C[i].openAccount();
        }
        // loop runs until number 5 is not pressed to exit
        int ch;
        do {
            System.out.println("\n ***Banking System Application***");
            System.out.println("1. Display all account details \n 2. Search by Account number\n 3. Deposit the amount \n 4. Withdraw the amount \n 5.Exit ");
            System.out.println("Enter your choice: ");
