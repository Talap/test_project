package com.example.test_project.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@ApiModel(description = "Model of tickets user for parking")
@Entity
@Component
@Table(name="contact_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_contact_types",
        initialValue = 1,
        allocationSize = 1
)
public class ContactType {
    @Id
    @GeneratedValue(generator = "seq")
    @Column(updatable = false, nullable = false)
    @ApiModelProperty(notes = "Уникальный идентификатор", readOnly = true)
    private Long id;

    @Column(name="phone_type")
    @ApiModelProperty(notes = "Тип номера телефона")
    private String phoneType;
}
