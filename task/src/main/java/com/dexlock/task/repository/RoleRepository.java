package com.dexlock.task.repository;

import com.dexlock.task.models.ERole;
import com.dexlock.task.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Long> {
Optional<Role> findByName(ERole name);

}
