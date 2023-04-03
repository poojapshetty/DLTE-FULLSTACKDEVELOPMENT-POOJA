package dlte.assessment2.assessment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public void implementUpdate(){
        loanRepository.updateStatus();
    }

    public List<Object[]> implementRejectedLoans(){
        return loanRepository.fetchByStatus();
    }
}

