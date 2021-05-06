package sgsits.cse.dis.moodle.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sgsits.cse.dis.moodle.model.MoodleTag;

@Repository
public interface MoodleTagRepo extends JpaRepository<MoodleTag, Long> {
	Optional<MoodleTag> findAllById(Long id);
}