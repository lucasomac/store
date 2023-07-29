package br.com.lucolimac.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.salesReport", query = "SELECT new br.com.lucolimac.model.SalesReport(Product.name, SUM(itens.quantity), MAX(o.createdDate)) FROM Order o JOIN o.orderItems itens JOIN itens.product p GROUP BY p.name ORDER BY itens.quantity DESC")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createdDate = LocalDate.now();
    private BigDecimal amount = BigDecimal.ZERO;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(Client client) {
        this.client = client;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        this.orderItems.add(orderItem);
        this.amount = this.amount.add(orderItem.getAmount());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
