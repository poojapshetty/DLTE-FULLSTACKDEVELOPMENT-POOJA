package bank.project.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

@Service

public class BankService implements BankOperations, UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("bank");
    private Logger logger = LoggerFactory.getLogger(BankService.class);

    //service for listing all the customer
    public List<Customer> listAll() {
        return jdbcTemplate.query("select * from Customer", new CustomerMapper());
    }

    //service for get attempts
    @Override
    public int getAttempts(int id) {
        int attempts = jdbcTemplate.queryForObject("select failed_attempts from customer where customer_id=?", Integer.class, id);
        logger.info("Number of attempts:" + attempts);
        return attempts;
    }

    //service for decrementing the attempts of customer login
    @Override
    public void decrementAttempts(int id) {
        jdbcTemplate.update("update customer set failed_attempts=failed_attempts-1 where customer_id=?", id);
    }

    //service for setattempts
    @Override
    public void setAttempts(int id) {
        jdbcTemplate.update("update customer set attempts=3 where customer_id=?", id);
        logger.info("Set attempts to 3");
    }

    //service for updating the status of the customer
    @Override
    public void updateStatus() {
        jdbcTemplate.update("update customer set customer_status='Suspended' where failed_attempts=0");
        logger.info("Suspended accounts");
    }

    //service for listing the customer
    public List<Customer> listCustomers() {
        return jdbcTemplate.query("Select * from customer", new CustomerMapper());
    }

    //service for list customer by username
    @Override
    public Customer getByUsername(String username) {
        try {
            Customer customer = (jdbcTemplate.queryForObject("select * from customer where username=?", new CustomerMapper(), username));
            logger.info("List Customers by username");
            return customer;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public String Login(String username, String password) {
        return null;
    }

    //service for incrementfailedattempts
    public void incrementFailedAttempts(int id) {
        jdbcTemplate.update("update customer set failed_attempts=failed_attempts+1 where customer_id=?", id);
        jdbcTemplate.update("update customer set customer_status='suspended' where customer_id=?", id);
    }


    //service for listing payee after logging with the certain customerId
    public List<Payee> getByCustomerId(int customer_id) {
        return jdbcTemplate.query("select * from payee", new PayeeMapper());
    }

    //service for listing the payee
    public List<Payee> listPayee(String username) {
        logger.info(" Get by username ");
        return jdbcTemplate.query("Select * from payee join customer on customer.customer_id = payee.customer_id where customer.username=? ", new PayeeMapper(), username);

    }

    //service for insertion of new payee to the payee table
    public String insertion(Payee payee) {
        String information = payee.getPayeeName() + " payee added";
        jdbcTemplate.update("insert into payee values(payee_id_seq.nextval,?,?,?)", new Object[]{payee.getPayeeName(), payee.getPayeeAccountNumber(), payee.getCustomerId()});
        return information;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Entered loadByUsername() method");
        Customer customer = getByUsername(username);

        if (customer == null) {
            throw new UsernameNotFoundException(resourceBundle.getString("userNotExist"));
        }
        logger.info("Leaving loadByUsername() method");
        if (customer.getCustomerstatus().equalsIgnoreCase("suspended")) {
            throw new UsernameNotFoundException(resourceBundle.getString("deactivate"));
        }
        return customer;
    }

    //customerMapper
    class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomercontact(rs.getLong("customer_contact"));
            customer.setCustomerid(rs.getInt("customer_id"));
            customer.setCustomername(rs.getString("customer_name"));
            customer.setCustomeraddress(rs.getString("customer_address"));
            customer.setCustomerstatus(rs.getString("customer_status"));
            customer.setUsername(rs.getString("username"));
            customer.setPassword(rs.getString("password"));
            logger.info(customer.getCustomername() + "details received from database");
            return customer;
        }
    }

    //PayeeMapper
    class PayeeMapper implements RowMapper<Payee> {
        @Override
        public Payee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Payee payee = new Payee();
            payee.setPayeeId(rs.getInt("payee_id"));
            payee.setPayeeName(rs.getString("payee_name"));
            payee.setPayeeAccountNumber(rs.getLong("payee_account_number"));
            payee.setCustomerId(rs.getInt("customer_id"));
            logger.info(payee.getPayeeName() + " details received from DB");
            return payee;
        }
    }
}






