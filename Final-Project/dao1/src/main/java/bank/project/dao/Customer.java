package bank.project.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Collection;


 //pojo class of the customer
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements UserDetails {
    private int customerid;
    private String customername;
    private String customeraddress;
    private String customerstatus;
    private long customercontact;
    private String username;
    private String password;
    private int failedattempts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

