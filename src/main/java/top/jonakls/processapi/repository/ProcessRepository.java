package top.jonakls.processapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.jonakls.processapi.entity.ProcessEntity;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessEntity, Integer> {

    @Query("SELECT p FROM process p WHERE p.radix = ?1")
    ProcessEntity findByRadix(String radix);


}
