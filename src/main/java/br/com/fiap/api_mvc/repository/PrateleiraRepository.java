package br.com.fiap.api_mvc.repository;

import br.com.fiap.api_mvc.model.Prateleira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrateleiraRepository extends JpaRepository<Prateleira, Long> {
}
