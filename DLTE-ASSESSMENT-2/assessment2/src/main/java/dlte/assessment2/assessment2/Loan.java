package dlte.assessment2.assessment2;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Loan {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "loan_seq")
        private int loanRequestNumber;
        @Column(nullable = false)
        private int requestedAmount;
        @Column(nullable = false)
        private int cibilcredit;
        @Column(nullable = false)
        private int aadharNumber;
        @Column(nullable = false)
        private String panNumber;
        @Column(nullable = false)
        private String profession;
        @Column(nullable = false)
        private int incomePerAnnum;
        @Column(nullable = false)
        private String statusOfApproval;
        @Column(nullable = false)
        private int dateOfTheRequest;
    }


