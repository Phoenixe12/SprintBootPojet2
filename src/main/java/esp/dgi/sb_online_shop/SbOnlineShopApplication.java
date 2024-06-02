package esp.dgi.sb_online_shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import esp.dgi.sb_online_shop.domain.Customer;
import esp.dgi.sb_online_shop.domain.CustomerRepository;
import esp.dgi.sb_online_shop.domain.Item;
import esp.dgi.sb_online_shop.domain.ItemRepository;
import esp.dgi.sb_online_shop.domain.Order;
import esp.dgi.sb_online_shop.domain.OrderRepository;
import esp.dgi.sb_online_shop.domain.Product;
import esp.dgi.sb_online_shop.domain.ProductRepository;

import java.util.Arrays;

@SpringBootApplication
public class SbOnlineShopApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(SbOnlineShopApplication.class);
	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;
  private final ItemRepository itemRepository;
  private final ProductRepository productRepository;

public SbOnlineShopApplication(
        CustomerRepository customerRepository,
        OrderRepository orderRepository,
        ItemRepository itemRepository,
        ProductRepository productRepository
) {
		this.customerRepository = customerRepository;
		this.orderRepository = orderRepository;
    this.itemRepository = itemRepository;
    this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SbOnlineShopApplication.class, args);
		logger.info("Spring Boot online shop application started successfully at http://localhost:8080");
	}

	@Override
	public void run(String... args) throws Exception {
    // Create some customers
		Customer c1 = new Customer("Bugs Bunny", "New York", "user",  59000);
		Customer c2 = new Customer("Daffy Duck", "Los Angeles", "user", 37000);
		Customer c3 = new Customer("Porky Pig", "Miami", "user", 28000 );
    customerRepository.saveAll(Arrays.asList(c1,c2, c3));

    Order o1 = new Order(c1);
		Order o2 = new Order(c1);
		Order o3 = new Order(c2);
		Order o4 = new Order(c3);
    orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));

    // Create a product
		Product shirt = new Product("Shirt", "Nice shirt", "shirt.jpg", 200);
		Product phone = new Product("Phone", "Smartphone", "phone.jpg", 200);
		Product tv = new Product("TV", "Smart TV", "tv.jpg", 300);
		Product shoes = new Product("Shoes", "Running shoes", "shoes.jpg", 300);
    productRepository.saveAll(Arrays.asList(shirt, phone, tv, shoes));

    // Create an item
		// Create items and assign to orders
		Item i1 = new Item(1, shirt.getPrice(), o1, shirt);
		Item i2 = new Item(1, phone.getPrice(), o2, phone);
		Item i3 = new Item(1, tv.getPrice(), o2, tv);
		Item i4 = new Item(1, shoes.getPrice(), o2, shoes);
		Item i5 = new Item(1, tv.getPrice(), o3, tv);
		Item i6 = new Item(1, phone.getPrice(), o3, phone);
		Item i7 = new Item(1, shoes.getPrice(), o3, shoes);
		Item i8 = new Item(1, tv.getPrice(), o4, tv);
		Item i9 = new Item(1, shirt.getPrice(), o4, shirt);
		itemRepository.saveAll(Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8, i9));



		// Print all orders
		System.out.println("----- All Orders ------");
		for (Order o : orderRepository.findAll()) {
			System.out.println("Customer: " + o.getCustomer().getFullname() + ": Order " + o.getId() + ": Total: " + o.getTotal() + "$");
			for (Item item : o.getItems()) {
				System.out.println("\tItem: " + item.getQuantity() + " x " + item.getProduct().getName() + " (" + item.getProduct().getPrice() + "$)");
			}
		}
		System.out.println("-----             ------");

	}
}
