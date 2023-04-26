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

        //method for the login failure
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            ResourceBundle bundle=ResourceBundle.getBundle("banks");
            String userName=request.getParameter("username");
            String passWord=request.getParameter("password");
            Customer customer=bankService.getByUsername(userName);
            if(customer==null){
                exception=new LockedException(bundle.getString("notExist"));
                super.setDefaultFailureUrl("/web/login/?error="+bundle.getString("notExist"));
            }
            else{

             //2 attempt left
                bankService.decrementAttempts(customer.getCustomerid());
                int attempts=bankService.getAttempts(customer.getCustomerid());
                if(attempts==2){
                    logger.info(bundle.getString("wrongpass")+bundle.getString("attempt2"));
                    exception=new LockedException(bundle.getString("wrongpass")+ bundle.getString("attempt2"));
                    super.setDefaultFailureUrl("/web/login/?error="+ bundle.getString("wrongpass")+bundle.getString("attempt2"));
                }

                //1 attempt left
                else if(attempts==1){
                    logger.info(bundle.getString("wrongpass")+bundle.getString("attempt1"));
                    exception=new LockedException(bundle.getString("wrongpass")+ bundle.getString("attempt1"));
                    super.setDefaultFailureUrl("/web/login/?error="+ bundle.getString("wrongpass")+bundle.getString("attempt1"));
                }

                //deactivate the customer
                else{
                    logger.info(bundle.getString("deactivate"));
                    exception=new LockedException(bundle.getString("deactivate"));
                    bankService.updateStatus();
                    super.setDefaultFailureUrl("/web/login/?error=" + bundle.getString("deactivate"));
                }
            }
            super.onAuthenticationFailure(request, response, exception);
        }}