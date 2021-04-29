package edu.miu.cs.cs544.examples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "Orderss")
public class Order {
	
	@Id
	@GeneratedValue
	private int orderId;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name= "customer_id")
	Customer customer;
	
	@OneToMany
	@JoinTable(name= "Order_OrderLine",
		joinColumns= { @JoinColumn(name= "order_id") },
		inverseJoinColumns= { @JoinColumn(name= "orderLine_id") }
			)
	List<OrderLine> orderLines = new ArrayList<>();
	
	public Order() {}
	
	public Order(Customer c, List<OrderLine> orderLineList) {
		this.date = new Date();
		this.customer = c;
		this.orderLines = orderLineList;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String toString() {
		String customerInfo = "Customer: "+ this.customer.getFirstname()+ " "+ this.customer.getLastname()+ "\n";
		String productsInfo = " OrderLines- Products:\n";
		for (int i = 0; i < this.orderLines.size(); i++) {
			productsInfo+= "prod"+ i+ ": "+ this.orderLines.get(i).getProduct().getName()+ " ,Quantity: "+ this.orderLines.get(i).getQuantity()+ "\n";
		}
		String dateInfo = " Date: " + this.date.toString();
		return customerInfo+productsInfo+dateInfo;
	}

}
