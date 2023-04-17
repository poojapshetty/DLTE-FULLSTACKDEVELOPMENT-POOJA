package bank.project.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class MvcController {


    @GetMapping("/log")
    public String login() {
        return "login";
    }

        @GetMapping("/dash")
        public String dashboard () {
            return "dashboard";
        }
        @GetMapping("/add")
        public String addpayee () {
           return "AddPayee";
        }
}





