package com.snuquill.paperdx.manage.accesslog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiAccessLogRepository extends JpaRepository<ApiAccessLog, String> {
}
