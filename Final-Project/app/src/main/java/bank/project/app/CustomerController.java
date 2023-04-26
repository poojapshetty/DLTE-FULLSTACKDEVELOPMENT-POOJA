package bank.project.app;

import bank.project.dao.BankService;
import bank.project.dao.Customer;
import bank.project.dao.Payee;
import com.sun.org.apache.xml.internal.utils.res.XResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@ComponentScan({"bank.project.dao"})
@RestController
@RequestMapping("/payee")
public class CustomerController {

    @Autowired
    private BankService bankService;
    private ResourceBundle bundle=ResourceBundle.getBundle("bank");
    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    //to get the list of customers
    @GetMapping("/")
    public List<Customer> callList() {
        logger.info("Controller about print All the records");
        return bankService.listAll();
    }

    //for the insertion of new payee
    @PostMapping("/insert")
    public String addPayee(@RequestParam("Fullname") String payeename,@RequestParam("Payeeacc") Long payeeaccno,@RequestParam("customerid") int customerid) throws ParseException {
        Payee payee=new Payee();
        payee.setPayeeName(payeename);
        payee.setPayeeAccountNumber(payeeaccno);
        payee.setCustomerId(customerid);
        logger.info("trying to insert");
        return bankService.insertion(payee);
    }

    @GetMapping("/username")
    String getUserName(Principal principal){
        return principal.getName();
    }

    //for the authentication of login page
    @PostMapping("/authenticate")
    public String Authorize(@RequestParam("username") String username, @RequestParam("password") String password) {
        logger.info("Enter the values");
        Customer customer = bankService.getByUsername(username);
        if (customer == null)
            return "User doesn't exist";
        else {
            logger.info(customer.getCustomerstatus());
            if (customer.getCustomerstatus().equalsIgnoreCase("Inactive"))
                return "Your account is deactivated";
            if (!password.equals(customer.getPassword())) {
                bankService.decrementAttempts(customer.getCustomerid());
                return "Incorrect Password";
            }
           else return "Success";
        }
    }
}