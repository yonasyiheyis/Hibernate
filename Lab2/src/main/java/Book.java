import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Book {
	
	
	private int id;
	private String title;
	private String ISBN;
	private String author;
	private double price;
	private java.util.Date publish_date;
	
	public Book() {}
	
	public Book(int id, String title, String ISBN, String author, double price, java.util.Date publish_date) {
		this.title = title;
		this.ISBN = ISBN;
		this.author = author;
		this.price = price;
		this.publish_date = publish_date;
		this.id = id;
	}
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Temporal(TemporalType.DATE)
	public java.util.Date getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(java.util.Date publish_date) {
		this.publish_date = publish_date;
	}
	
	@Override
	public String toString() {
		return "Book [" + id + ", Title=" + this.title + ", ISBN=" + this.ISBN + ", Author="+ this.author + ", Price="+ this.price + ", Published="+ this.publish_date+"]";
	}
	
}
