package bank.project.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class MvcController {

     //login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

         //dashboard
        @GetMapping("/dashboard")
        public String dashboard () {
            return "dashboard";
        }

        //add payee
        @GetMapping("/addpayee")
        public String addpayee () {
            return "AddPayee";
        }

        //update payee
            @GetMapping("/update")
            public String update () {
                return "Update";
            }

            //add transaction
    @GetMapping("/transaction")
    public String transaction () {
        return "Transaction";
    }
        }





