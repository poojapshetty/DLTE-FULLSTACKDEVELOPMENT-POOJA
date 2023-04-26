package bank.project.dao;

import java.util.List;

public interface BankOperations {
   Customer getByUsername(String username);
   String Login(String username,String password);
   List<Payee> listPayee(String username);
    int getAttempts(int id);
    void decrementAttempts(int id);
    void setAttempts(int id);
    void updateStatus();
    public String insertion(Payee payee);
}
