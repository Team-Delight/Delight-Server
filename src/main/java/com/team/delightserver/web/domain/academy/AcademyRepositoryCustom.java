package com.team.delightserver.web.domain.academy;

import java.util.List;

public interface AcademyRepositoryCustom {
    List<Academy> findByName(String name);
}
