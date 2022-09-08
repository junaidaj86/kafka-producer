package com.opera.nexer.urlfeeder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="url")
@ToString
public class CloudEventsEntity {

    @Id
    private String id;


    private String url;

    private String description;

    private String name;


}
