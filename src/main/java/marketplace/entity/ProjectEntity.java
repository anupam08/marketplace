package marketplace.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "project")
@Data
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", table = "project")
    private String name;
    
    @Column(name = "description", table = "project")
    private String description;
    
    @Column(name = "budget", table = "project")
    private double budget;
    
    @Column(name = "bidDeadline", table = "project")
    private LocalDate bidDeadline;
    
    @Column(name = "lowestBid", table = "project")
    private Double lowestBid = 0.0;
    
    @OneToOne
    @JoinColumn(name = "buyerId", referencedColumnName = "id")
    private BuyerEntity buyer;
    
    @OneToOne
    @JoinColumn(name = "sellerId", referencedColumnName = "id")
    private SellerEntity seller;
}
