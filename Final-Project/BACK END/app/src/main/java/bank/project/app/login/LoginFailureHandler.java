package bank.project.app.login;

import bank.project.dao.BankService;
import bank.project.dao.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

    @Component
    public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
        @Autowired
        BankService bankService;


        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            ResourceBundle bundle=ResourceBundle.getBundle("Alphabank");
            String userName=request.getParameter("username");
            String passWord=request.getParameter("password");
            Customer customer=bankService.getByUsername(userName);
            if(customer==null){
                exception=new LockedException(bundle.getString("notExist"));
            }
            else{
                if(customer.getCustomerstatus().equalsIgnoreCase("inactive")){
                    logger.info(bundle.getString("deactivate"));
                    exception=new LockedException(bundle.getString("deactivate"));
                }
                else{
                    bankService.decrementAttempts(customer.getCustomerid());
                    int attempts=bankService.getAttempts(customer.getCustomerid());
                    if(attempts==2){
                        logger.info(bundle.getString("wrongpass")+bundle.getString("attempt2"));
                        exception=new LockedException(bundle.getString("attempt2")+bundle.getString("wrongpass"));
                        super.setDefaultFailureUrl("/web/login/?error="+ bundle.getString("wrongpass")+bundle.getString("attempt2"));
                    }
                    else if(attempts==1){
                        logger.info(bundle.getString("wrongpass")+bundle.getString("attempt1"));
                        exception=new LockedException(bundle.getString("attempt1")+bundle.getString("wrongpass"));
                        super.setDefaultFailureUrl("/web/login/?error="+ bundle.getString("wrongpass")+bundle.getString("attempt1"));
                    }
                    else{
                        logger.info(bundle.getString("deactivate"));
                        exception=new LockedException(bundle.getString("deactivate"));
                        bankService.updateStatus();
                        super.setDefaultFailureUrl("/web/login/?error=" + bundle.getString("deactivate"));
                    }
                }}
// super.setDefaultFailureUrl("/web/login?error");
            super.onAuthenticationFailure(request, response, exception);
        }
    }

