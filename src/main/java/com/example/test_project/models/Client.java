package com.example.test_project.models;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.List;

@ApiModel(description = "Model of tickets user for parking")
@Entity
@Component
@Table(name="clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_clients",
        initialValue = 1,
        allocationSize = 1
)
public class Client extends AuditModel{
    @Column(name="first_name")
    @ApiModelProperty(notes = "Имя клиента")
    private String firstName;

    @Column(name="second_name")
    @ApiModelProperty(notes = "Фамилия Клиента")
    private String secondName;

    @Column(name="patronymic")
    @ApiModelProperty(notes = "Отчество клиента")
    private String patronymic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<ContactDetails> contactDetails;


    public Client(String firstName, String secondName, String patronymic) {
    }
}
