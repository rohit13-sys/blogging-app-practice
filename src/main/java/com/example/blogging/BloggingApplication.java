package com.example.blogging;

import com.example.blogging.repository.RoleRepository;
import com.example.blogging.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.blogging.entity.Role;

import java.util.List;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {


	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	@Override
	public void run(String... args) throws Exception {
		Role role=new Role();
		role.setId(Constants.NORMAL_USER_ID);
		role.setName(Constants.NORMAL_USER);

		Role role1=new Role();
		role1.setId(Constants.ADMIN_USER_ID);
		role1.setName(Constants.ADMIN_USER);

		Role role2=new Role();
		role2.setId(Constants.ACCOUNT_USER_ID);
		role2.setName(Constants.ACCOUNT_USER);

		roleRepository.saveAll(List.of(role,role1,role2));
	}
}
