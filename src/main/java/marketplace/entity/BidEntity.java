package marketplace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bid")
@Data
public class BidEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "bidPrice", table = "bid")
    private double bidPrice;

    @ManyToOne
    @JoinColumn(name = "buyerId", referencedColumnName = "id")
    private BuyerEntity buyer;
    
    @ManyToOne
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private ProjectEntity project;
}
