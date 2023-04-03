package dlte.assessment2.assessment2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository  extends JpaRepository<Loan,Long> {

    @Modifying
    @Transactional
    @Query("update Loan set statusOfApproval='Approved' where cibilcredit>700 and profession in ('salaried','self-employed') and incomePerAnnum >= requestedAmount*3")
    void updateStatus();
}
