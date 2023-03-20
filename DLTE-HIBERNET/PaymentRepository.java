package dlte.hibernet.spring.springhibernet;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    @Query("Select upi from Payment ")
    List<Integer> findAllUpi();

    @Query("select payeeName from Payment where ifscCode=:ifscCode")
    List<String> findAllByIfscCode(String ifscCode);

    List<Payment> findByIfsc(String ifsc);
}



