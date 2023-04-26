package bank.project.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//pojo class of the payee
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payee {
    private int payeeId;
    private String payeeName;
    private long payeeAccountNumber;
    private int customerId;
    }
