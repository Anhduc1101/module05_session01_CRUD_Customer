package ra.model.entity;

import javax.persistence.*;

@Entity
@Table()
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String productName;
    @ManyToOne
    @JoinColumn(name = "cusId",referencedColumnName = "id")
    private Customer customer;

    public Product() {
    }

    public Product(int id, String productName, Customer customer) {
        this.id = id;
        this.productName = productName;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
