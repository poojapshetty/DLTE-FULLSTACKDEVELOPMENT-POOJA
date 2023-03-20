package dlte.hibernet.spring.springhibernet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/insert")
    public Payment callingSave(@RequestBody Payment payment) {
        return paymentService.implementationOfSave(payment);
    }

    @GetMapping("/view")
    public List<Payment> callingFindAll() {
        return paymentService.implementationOfFindAll();
    }

    @GetMapping("/details/{payeeId}")
    public Optional<Payment> callingFindbyId(@PathVariable("payeeId") int payeeId) {
        return paymentService.implentationOfFindbyId(payeeId);
    }

    @PutMapping("/modify/{payeeId}")
    public Payment callingUpdate(@RequestBody Payment payment) {
        return paymentService.implementationOfSave(payment);
    }

    @DeleteMapping("/delete/{payeeId}")
    public String callingDeletebyId(@PathVariable("payeeId") int payeeId) {
        return paymentService.implementationOfDeletebyId(payeeId);
    }

    @GetMapping("/upi")
    public List<Integer> callingFetchUpi() {
        return paymentService.implementFetchUpi();
    }

    @GetMapping("/payee/{ifscCode}")
    public List<String> callExactPayee(@PathVariable("ifscCode") String ifscCode) {
        return paymentService.implementFetchExactPayee(ifscCode);
    }

    @GetMapping("/all/{ifsc}")
    public List<Payment> callAllByifsc(@PathVariable("ifsc") String ifsc){
        return paymentService.implementFetchAllByIfsc(ifsc);
    }

}


