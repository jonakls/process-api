package top.jonakls.processapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "process")
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProcessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_id")
    private int id;
    @Column(name = "typecase")
    private String typeCase;
    private String defendant;
    private String plaintiff;
    @Column(unique = true)
    private String radix;
    //@OneToMany(targetEntity = ActuationObjectEntity.class)
    //@JoinColumn(name = "actuation_id")
    //private List<ActuationObjectEntity> actuation;
    //@OneToOne
    @Column(name = "lawyer_id")
    private Integer lawyer;
    //@ManyToMany(targetEntity = ClientObjectEntity.class)
    //@JoinColumn(name = "id")
    //private List<ClientObjectEntity> client;
}
