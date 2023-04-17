package bank.project.dao;

public interface BankOperations {
   Customer getByUsername(String username);
   //void incrementFailedAttempts(int id);
    int getAttempts(int id);
    void decrementAttempts(int id);
    void setAttempts(int id);
    void updateStatus();
    public String insertion(Payee payee);
}
