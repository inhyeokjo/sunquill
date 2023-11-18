package com.snuquill.paperdx.magazine.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
	Magazine findByVolumeNumber(int volumeNumber);

	@Query("select m from Magazine m order by m.publishDate desc")
	List<Magazine> findAllDesc(Pageable pageable);

	@Query("select max(m.volumeNumber) from Magazine m ")
	Optional<Integer> findLatestVolumeNumber();
}
