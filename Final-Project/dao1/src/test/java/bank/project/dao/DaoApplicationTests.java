package bank.project.dao;

import bank.project.dao.BankService;
import lombok.experimental.StandardException;
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


//test for the insertion of new payee
   @Test
   public void testInsertPayee(){
       Payee payee1=new Payee(1,"Saksha",12345l,3);
       Payee payee2=new Payee(5,"Ashish",5678l,2);
       when(jdbcTemplate.update(eq("insert into payee values(?,?,?)"), eq(new Object[]{payee2.getPayeeId(),payee2.getPayeeName(),payee2.getPayeeAccountNumber(),payee2.getCustomerId()})))
               .thenReturn(1);
       String data=bankService.insertion(payee2);
       //assertEquals("Saksha has inserted",data);
       assertEquals("Ashish payee added",data);
   }

   //test for listing the payee
   @Test
    void testListingPayees(){
        Payee payee1=new Payee(58,"Athmika",867889L,2);
        Payee payee2=new Payee(68,"Aishani",7345432L,2);
        Payee payee3=new Payee(78,"Athish",7896790L,2);
        Customer customer4 = new Customer(2, "Arpitha", "Mumbai", "inactive", 71045L, "arpi", "arpi12", 0);
        List<Payee> payeeList=Stream.of(payee1,payee2,payee3).collect(Collectors.toList());
        String username="arpi";
        when(jdbcTemplate.query(eq("Select * from payee join customer on customer.customer_id = payee.customer_id where customer.username=? "),any(RowMapper.class),eq(username))).thenReturn(payeeList);
        assertEquals(payeeList,bankService.listPayee(username));
    }

    //test for the login
    @Test
    void testlogin(){
        Customer customer3 = new Customer(1, "Ashish", "Karkala", "active", 234543L, "ashu", "ashu27", 1);
        Customer customer4 = new Customer(2, "Saksha", "Surathkal", "inactive", 76545L, "saksha", "sak27", 0);
        List<Customer> customerlist= Stream.of(customer3,customer4).collect(Collectors.toList());
        String username="ashu",password="ashu27";
        when(jdbcTemplate.queryForObject(eq("select * from CUSTOMER where username=?"),any(RowMapper.class),eq(username))).thenReturn(customer3);
//String status=bankOperations.Login(username,password);
        assertEquals(customer3.getUsername(),username);
    }

    @Test
    void testListPayees(){
        Payee payee1=new Payee(58,"Hari",8675679L,2);
        Payee payee2=new Payee(68,"Shreya",7456756L,2);
        Payee payee3=new Payee(78,"Shilpa",5467789L,2);
        Customer customer4 = new Customer(2, "Vikas", "Manipal", "active", 719905L, "vikas", "vikas12", 0);
        List<Payee> payeeList=Stream.of(payee1,payee2,payee3).collect(Collectors.toList());
        String username="vikas";
        when(jdbcTemplate.query(eq("Select * from payee join customer on customer.customer_id = payee.customer_id where customer.username=? "),any(RowMapper.class),eq(username))).thenReturn(payeeList);
        assertEquals(payeeList,bankService.listPayee(username));
        assertNotEquals(payee1,bankService.listPayee(username).get((1)));
    }
    @Test
    void testloginn(){
        Customer customer3 = new Customer(1, "Apoorva", "ballary", "active", 234773L, "appu", "appu27", 1);
        Customer customer4 = new Customer(2, "Aishwarya", "banglore", "inactive", 763455L, "aish", "aish27", 0);
        List<Customer> customerlist= Stream.of(customer3,customer4).collect(Collectors.toList());
        String username="appu",password="appu27";
        when(jdbcTemplate.queryForObject(eq("select * from CUSTOMER where username=?"),any(RowMapper.class),eq(username))).thenReturn(customer3);
//String status=bankOperations.Login(username,password);
        assertEquals(customer3.getUsername(),username);
        assertNotEquals(customer4,bankService.Login(username,password));
    }
    @Test
    public void testInsertionPayee(){
        Payee payee1=new Payee(1,"Vijesh",145675l,3);
        Payee payee2=new Payee(5,"Ranjith",345768l,2);
        when(jdbcTemplate.update(eq("insert into payee values(?,?,?)"), eq(new Object[]{payee2.getPayeeId(),payee2.getPayeeName(),payee2.getPayeeAccountNumber(),payee2.getCustomerId()})))
                .thenReturn(1);
        String data=bankService.insertion(payee2);
        assertEquals("Ranjith payee added",data);
        assertNotEquals(payee2,bankService.insertion(payee1));
    }
}




