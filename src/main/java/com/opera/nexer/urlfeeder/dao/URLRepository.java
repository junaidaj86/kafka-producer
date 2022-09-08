package com.opera.nexer.urlfeeder.dao;

import com.opera.nexer.urlfeeder.model.CloudEventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLRepository extends JpaRepository<CloudEventsEntity, String> {
}
