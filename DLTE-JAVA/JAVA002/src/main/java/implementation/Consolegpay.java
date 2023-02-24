import java.util.*;

public class Consolegpay {
    public static void main(String[] args) {
        Double amount=0.0,interestRate=0.0;
        Integer tenure=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Loan Amount: ");
        amount = sc.nextDouble();
        System.out.println("Enter the Loan Interest Rate(in percentage): ");
        interestRate = sc.nextDouble();
        System.out.println("Enter the Loan Tenure: ");
        tenure = sc.nextInt();
        interestRate=interestRate/(12*100);
        tenure=tenure*12;
        Double interestAmount = 0.0;
        Double totalRepayment = 0.0;
        Double emiPerMonth = (amount*interestRate*Math.pow(1+interestRate,tenure))/(Math.pow(1+interestRate,tenure)-1); // calculation of emi per month
        totalRepayment = emiPerMonth*tenure; //calculation of total repayment
        interestAmount = totalRepayment-amount; //calculation of interest paid over tenure
        System.out.println("The EMI per month to be paid175 is: "+emiPerMonth);
        System.out.println("The total interest paid is: "+interestAmount);
        System.out.println("The total repayment to be done is: "+totalRepayment);
        sc.close();
    }
}
