package com.mywallet.wallet.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mywallet.wallet.domain.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

	@Query("SELECT COUNT(w)> 0 FROM Wallet w WHERE w.number = :number AND (:id IS NULL OR id <> :id)")
	boolean existsByNumber(@Param("number") UUID number, @Param("id") Long id);

	Optional<Wallet> findByNumber(UUID number);

	@Query("FROM Wallet e order by e.person.name, e.person.surname")
	List<Wallet> findAll();

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("FROM Wallet w WHERE w.number = :number")
	Optional<Wallet> findByNumberWithLock(@Param("number") UUID number);
}
