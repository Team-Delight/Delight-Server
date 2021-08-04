package com.team.delightserver.web.domain.academy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademyRepository extends JpaRepository<Academy, Long>, AcademyRepositoryCustom {
}
