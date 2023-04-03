package dlte.assessment2.assessment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PutMapping("/status")
    public void callStatus(){
        loanService.implementUpdate();
    }

    @GetMapping("/rejected")
    public List<Object[]> callRejected(){
        return loanService.implementRejectedLoans();
    }
}


