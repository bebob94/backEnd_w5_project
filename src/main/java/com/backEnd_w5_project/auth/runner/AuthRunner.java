package com.backEnd_w5_project.auth.runner;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.backEnd_w5_project.auth.entity.ERole;
import com.backEnd_w5_project.auth.entity.Role;
import com.backEnd_w5_project.auth.repository.RoleRepository;
import com.backEnd_w5_project.auth.service.AuthService;
import com.backEnd_w5_project.auth.service.DispositivoService;
import com.backEnd_w5_project.auth.service.UserService;




@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;
	@Autowired UserService userService;
	@Autowired DispositivoService dispositivoService;
	@Autowired AuthService authService;
	
	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
//		setRoleDefault();
//	
//      dispositivoService.createAndSaveFakeDispositivi(10);
//      
//      userService.createAndSaveFakeUtente(10);
//      
//      dispositivoService.collegaDispositivoAdUser(1l,1l);
//      dispositivoService.collegaDispositivoAdUser(2l,3l);
//      dispositivoService.collegaDispositivoAdUser(3l,4l);
//      dispositivoService.collegaDispositivoAdUser(4l,6l);
	}
	
	
	
	
	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		Role moderator = new Role();
		moderator.setRoleName(ERole.ROLE_MODERATOR);
		roleRepository.save(moderator);
		
		adminRole = new HashSet<Role>();
		adminRole.add(admin);
		adminRole.add(moderator);
		adminRole.add(user);
		
		moderatorRole = new HashSet<Role>();
		moderatorRole.add(moderator);
		moderatorRole.add(user);
		
		userRole = new HashSet<Role>();
		userRole.add(user);
	}

}
