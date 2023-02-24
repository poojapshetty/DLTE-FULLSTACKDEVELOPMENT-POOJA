package java004.example;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.util.Scanner;

@Data


class Gpay{
    Scanner sc = new Scanner(System.in);

    private String accno;
    private  int balance;
    private int upi;
    private String name;

    public Gpay(String accno, int balance, int upi, String name) {
        this.accno = accno;
        this.balance = balance;
        this.upi = upi;
        this.name = name;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getUpi() {
        return upi;
    }

    public void setUpi(int upi) {
        this.upi = upi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Gpay{" +
                "accno=" + accno +
                ", balance=" + balance +
                ", upi=" + upi +
                ", name='" + name + '\'' +
                '}';
    }
    public void fundTransfer(){
        String recep;
        Integer amount;
        System.out.println("Enter the name:");
        recep = sc.nextLine();
        System.out.println("Enter the amount:");
        amount = sc.nextInt();
        System.out.println("Enter the upi:");
        if(this.upi==sc.nextInt()){
            System.out.println(amount.toString()+" transferred to "+recep);
        }
        else {
            System.out.println("Invalid UPI pin");
        }


    }
}
class Application {
    public static void main(String[] args) {
        Gpay user = new Gpay("123456789",5000,4321,"Gourav");
        user.fundTransfer();
    }
}