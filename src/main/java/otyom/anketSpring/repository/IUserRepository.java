package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    User findByName(String userName);
}
