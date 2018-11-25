package com.kryvoruchka.Repository;

import com.kryvoruchka.domain.Maker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakerRepository extends JpaRepository<Maker, String> {
}
