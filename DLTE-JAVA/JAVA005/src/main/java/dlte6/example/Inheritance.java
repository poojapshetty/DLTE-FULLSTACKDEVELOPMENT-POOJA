package dlte6.example;
import java.util.*;
public class Inheritance {
    public static void main(String[] args) {
        SMS sms = new SMS("SURYA BOSS", 12356, 100.0);

        // Performing  a balance enquiry
        sms.UserInteraction(1901);

        // Recharging the account
        sms.UserInteraction(2245);

        // Booking a ticket
        sms.book_ticket();
    }
}


class KYC {
    String name;
    int accnum;
    double balance;

    KYC(String name, int accnum, double balance) {
        this.name = name;
        this.accnum = accnum;
        this.balance = balance;
    }
}

class Transaction extends KYC {
    Transaction(String name, int accnum, double balance) {
        super(name, accnum, balance);
    }

    void balance_enq() {
        System.out.println("CURRENT BALANCE-> " + balance);
    }

    void recharge(double amount) {
        balance += amount;
        System.out.println("RECHARGE DONE!! NEW BALANCE IS-> " + balance);
    }

    void book_ticket() {
        System.out.println("BOOKING TICKET...");
        // code for booking ticket......
    }
}

class SMS extends Transaction {
    SMS(String name, int accnum, double balance) {
        super(name, accnum, balance);
    }

    void UserInteraction(int num) {
        switch (num) {
            case 1901:
                balance_enq();
                break;
            case 2245:
                recharge(50.0); // recharge with 50 units
                break;
            default:
                System.out.println("INVALID");
        }
    }
}

