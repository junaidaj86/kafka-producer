package com.opera.nexer.urlfeeder.dao;

import com.opera.nexer.urlfeeder.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLRepository extends JpaRepository<URL, String> {
}
