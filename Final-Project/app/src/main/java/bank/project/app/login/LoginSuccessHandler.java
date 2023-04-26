package bank.project.app.login;

import bank.project.dao.BankService;
import bank.project.dao.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;


@Component
    public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Autowired
        BankService bankService;

        //method for login success
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            Customer customer= (Customer) authentication.getPrincipal();
            ResourceBundle bundle=ResourceBundle.getBundle("banks");

            if(customer.getCustomerstatus().equalsIgnoreCase("active")){
                bankService.getByUsername(customer.getUsername());
                logger.info("success");
                super.setDefaultTargetUrl("/web/dashboard");

            }
            else{
                logger.info(bundle.getString("deactivate"));
                super.setTargetUrlParameter("login/logout?error="+bundle.getString("deactivate"));
                super.setDefaultTargetUrl("/logout");
            }
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

