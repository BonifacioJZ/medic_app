package com.bonifacio.medic_app;

import com.bonifacio.medic_app.persitence.entities.ERole;
import com.bonifacio.medic_app.persitence.entities.PermissionEntity;
import com.bonifacio.medic_app.persitence.entities.RoleEntity;
import com.bonifacio.medic_app.persitence.entities.UserEntity;
import com.bonifacio.medic_app.persitence.repositories.IRoleRepository;
import com.bonifacio.medic_app.persitence.repositories.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Set;
import java.util.List;
@SpringBootApplication
public class MedicAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IRoleRepository roleRepository){
		return args -> {
			PermissionEntity createPermission = PermissionEntity.builder()
					.permission("CREATE")
					.build();
			PermissionEntity readPermission = PermissionEntity.builder()
					.permission("READ")
					.build();
			PermissionEntity updatePermission = PermissionEntity.builder()
					.permission("UPDATE")
					.build();
			PermissionEntity deletePermission = PermissionEntity.builder()
					.permission("DELETE")
					.build();
			PermissionEntity refactorPermission = PermissionEntity.builder()
					.permission("REFACTOR")
					.build();
			PermissionEntity createUser = PermissionEntity.builder()
					.permission("CREATE_USER")
					.build();
			RoleEntity admin = RoleEntity.builder()
					.roleEnum(ERole.ADMIN)
					.permissions(Set.of(createPermission,readPermission,updatePermission,deletePermission,refactorPermission))
					.build();
			RoleEntity user = RoleEntity.builder()
					.roleEnum(ERole.USER)
					.permissions(Set.of(createPermission,readPermission))
					.build();
			RoleEntity medic = RoleEntity.builder()
					.roleEnum(ERole.MEDIC)
					.permissions(Set.of(createPermission,createUser,readPermission,deletePermission,updatePermission))
					.build();
			RoleEntity secretary = RoleEntity.builder()
					.roleEnum(ERole.SECRETARY)
					.permissions(Set.of(createPermission,readPermission,deletePermission,updatePermission))
					.build();
			RoleEntity nurse = RoleEntity.builder()
							.roleEnum(ERole.NURSE)
					        .permissions(Set.of(createPermission,readPermission,updatePermission))
					        .build();
			RoleEntity dev = RoleEntity.builder()
							.roleEnum(ERole.DEV)
					        .permissions(Set.of(createPermission,readPermission,updatePermission,deletePermission,refactorPermission))
									.build();
			roleRepository.saveAll(List.of(admin,user,medic,secretary,nurse,dev));
		};
	}

}
