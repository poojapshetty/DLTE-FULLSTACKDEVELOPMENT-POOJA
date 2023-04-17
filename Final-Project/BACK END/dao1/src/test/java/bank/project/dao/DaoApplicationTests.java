package bank.project.dao;

import bank.project.dao.BankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DaoApplicationTests {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    BankService bankService;

    @Test
    public void testListAll() {
       Payee p1=new Payee:"Shreeni",1234567,2);
        Payee p2 = new Customer(2, "3432", "Nidhi", "Karkala", "Active", "nidhi", 776567785, 2);
        List<Customer> tempList = Stream.of(p1, p2).collect(Collectors.toList());
        when(jdbcTemplate.query(eq("select * from customer"), any(RowMapper.class))).thenReturn(tempList);
        assertEquals(p2, bankService.listPayee().get(1));

    }

    //    @Test
//    public void testListOne(){
//        Customer c1=new Customer(2,"3432","Nidhi","Karkala","Active","nidhi",776567785,2);
//        String username="nidhi";
//
//        when(jdbcTemplate.queryForObject(eq("select * from customer where username=?"), eq(new Object[]{username}), any(RowMapper.class)))
//                .thenReturn(c1);
//
//        Customer c=bankService.readUserName("nidhi").get();
//        assertEquals(c1,c);
//
    @Test
    void testListPayees(){
        Payee payee1=new Payee(87,"",87654567L,2);
        Payee payee2=new Payee(88,"Abhihsek",765456L,2);
        Customer customer4 = new Customer(2, "Anusha", "Manglore", "inactive", 76545L, "anu", "anu12", 0);
        List<Payee> payeeList=Stream.of(payee1,payee2).collect(Collectors.toList());
        when(jdbcTemplate.query(eq("select * from payee join customer on customer.customer_id = payee.customer_id where customer.username=?"),any(RowMapper.class),eq(customer4.getUsername()))).thenReturn(payeeList);
        //logger.info(payeeList+"is correct");
        assertEquals(payeeList,bankService.listPayee(customer4.getUsername()));
    }

    @Test
    public void testGetUsername() {

        Customer c1 = new Customer(1, "7477 ", "Manvith", "Udupi", "Inactive", "manvith", 878773435, 3);
        String username = "manvith";
        when(jdbcTemplate.queryForObject(eq("select * from CUSTOMER where USERNAME=?"), any(RowMapper.class), eq(username)))
                .thenReturn(c1);
        Customer customer1 = bankService.getByUsername("manvith");
        assertEquals(c1.getUsername(), customer1.getUsername());

    }
}




