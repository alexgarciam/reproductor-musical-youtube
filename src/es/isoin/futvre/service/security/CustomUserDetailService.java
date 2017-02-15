package es.isoin.futvre.service.security;

import org.hibernate.Hibernate;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.alex.futvre.persistence.Authority;
import es.alex.futvre.persistence.DaoFactory;
import es.alex.futvre.persistence.UserDao;
import es.alex.futvre.persistence.Usuario;

@Service("customUserDetailsService")
@Transactional(readOnly=true)
public class CustomUserDetailService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException, DataAccessException {		
		SecurityUser securityUser=null;
		UserDao user=DaoFactory.getUserDao();
		Usuario myuser=user.findUserByUsername(arg0);	
		if(myuser!=null){
			//convert roles        
	        GrantedAuthority[] auths=new  GrantedAuthority[myuser.getAuthorities().size()] ;
	        int cont=0;
	        for (Authority p : myuser.getAuthorities()) {
	        	Hibernate.initialize(p.getAuthority());        	
	        	GrantedAuthorityImpl gr=new GrantedAuthorityImpl(p.getAuthority());         	
	        	auths[cont]=gr;            
	        }
	        securityUser=new SecurityUser(myuser.getLogin(),myuser.getPassword(),myuser.isActived(),true,true,myuser.isAccountNonLocked(),auths);
	        return securityUser;
		}
		else{
			throw new UsernameNotFoundException("Username not found");
		}
		
	}
}
