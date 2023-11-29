```jsx
//CustomerManagement.java

package onlinegrocery;
import java.util.Scanner;
import java.util.Random;

class Customer {
    private String customerName;
    private String email;
    private String password;
    private String address;
    private String contactNumber;
    private int customerID;

    public Customer(String customerName, String email, String password, String address, String contactNumber, int customerID) {
        this.customerName = customerName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNumber = contactNumber;
        this.customerID = customerID;
    }

    // Getter and Setter methods

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    // Validation methods
    public static boolean isValidCustomerName(String name) {
        return name.matches("[a-zA-Z]+") && name.length()<=50;
    }

    public static boolean isValidEmail(String email, Customer[] customers) {
    	for(int i=0;i<customers.length;i++)
    	{
//    		if(customers[i].getEmail().equalsIgnoreCase(email))
//    		{
//    			return false;
//    		}
    		if(customers[i]==null){
    			break;
    		}
    		else if(customers[i].getEmail().equalsIgnoreCase(email)){
    		 return false;
    		}
    	}
        return email.contains("@");
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{5,}$") && password.length()>=6 && password.length()<=12;
    }

    public static boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty() && address.length() <=100;
    }

    public static boolean isValidContactNumber(String contactNumber) {
        return contactNumber.matches("[0-9]+") && contactNumber != null && !contactNumber.trim().isEmpty() && contactNumber.length() == 10;
    }
}

public class CustomerManagement {
    static Customer[] customers = new Customer[100];
    static int customerCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            System.out.print("Customer Name: ");
            String customerName = scanner.nextLine();
            if (!Customer.isValidCustomerName(customerName)) {
                System.out.println("Alert: Customer Name must have alphabets only and should not be blank or null");
                continue;
            }

            System.out.print("Email: ");
            String email = scanner.nextLine();
            if (!Customer.isValidEmail(email,customers)) {
                System.out.println("Alert: Email id not valid and should not be blank or null or same");
                continue;
            }

            System.out.print("Password: ");
            String password = scanner.nextLine();
            if (!Customer.isValidPassword(password)) {
                System.out.println("Alert: Password is not matching the criteria and should not be blank or null");
                continue;
            }

            System.out.print("Address: ");
            String address = scanner.nextLine();
            if (!Customer.isValidAddress(address)) {
                System.out.println("Alert: Address should not be blank or null");
                continue;
            }

            System.out.print("Contact Number: ");
            String contactNumber = scanner.nextLine();
            if (!Customer.isValidContactNumber(contactNumber)) {
                System.out.println("Alert: Contact number must not have any alphabets and should not be blank or null and contains exact 10 characters. ");
                continue;
            }

            int customerID = 10000 + random.nextInt(90000);

            Customer customer = new Customer(customerName, email, password, address, contactNumber, customerID);
            customers[customerCount] = customer;
            customerCount++;

            System.out.println("Customer Registration is successful for Customer ID: " + customerID);

            System.out.print("Do you want to register another customer? (yes/no): ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        displayCustomers(customers, customerCount);

        // Rest of the code (unchanged)
        while(true)
        {
        	System.out.println("Do you want to update Details? (yes/no): ");
        	String response = scanner.nextLine();
        	if (response.equalsIgnoreCase("no"))
        		break;
        	System.out.println("Enter Customer Id to update details: ");
        	int updateCustomerID = scanner.nextInt();
        	scanner.nextLine();
        	Customer customerToUpdate = findCustomerById(customers, customerCount, updateCustomerID);
        	int index = findCustomerByIdIndex(customers, customerCount, updateCustomerID);
        	if (customerToUpdate != null) {
        		updateCustomerDetails(scanner, customerToUpdate, index);
        		System.out.println("Your Details updated successfully");
                displayCustomers(customers, customerCount);
    		}
    		else {
                System.out.println("Customer not found with ID: " + updateCustomerID);
            }
        }
        
        while(true)
        {
        	System.out.println("Do you want to view email Details? (yes/no): ");
        	String res = scanner.nextLine();
        	if (res.equalsIgnoreCase("no"))
        		break;
        	if (res.equalsIgnoreCase("yes")) {
        		viewCustomerDetailsByEmail(customers, scanner);
            }
        }
    }

    public static void updateCustomerDetails(Scanner scanner, Customer customer, int index) {
        System.out.print("Enter new Customer Name (Max 50 characters): ");
        String newName = scanner.nextLine();
        customer.setCustomerName(newName);

        System.out.print("Enter new Email (Unique): ");
        String newEmail = scanner.nextLine();
        customer.setEmail(newEmail);

        System.out.print("Enter new Password (Min 6 and Max 12 characters): ");
        String newPassword = scanner.nextLine();
        customer.setPassword(newPassword);

        System.out.print("Enter new Address (Max 100 characters): ");
        String newAddress = scanner.nextLine();
        customer.setAddress(newAddress);

        System.out.print("Enter new Contact Number (10 characters): ");
        String newContactNumber = scanner.nextLine();
        customer.setContactNumber(newContactNumber);

        customers[index] = customer;
    }

    // Rest of the code (unchanged)
    public static Customer findCustomerById(Customer[] customers, int customerCount, int customerID) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerID() == customerID) {
                return customers[i];
            }
        }
        return null;
    }
    public static int findCustomerByIdIndex(Customer[] customers, int customerCount, int customerID) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].getCustomerID() == customerID) {
                return i;
            }
        }
        return -1;
    }
    public static Customer findCustomerByEmail(Customer[] customers, int customerCount, String email) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getEmail() == email) {
                return customers[i];
            }
        }
        return null;
    }

    public static void displayCustomers(Customer[] customers, int customerCount) {
        System.out.println("Registered Customers:");
        for (int i = 0; i < customerCount; i++) {
            Customer customer = customers[i];
            System.out.println("Customer ID: " + customer.getCustomerID() + ", Name: " + customer.getCustomerName() + ", Email: " + customer.getEmail());
        }
    }
    
    public static void viewCustomerDetailsByEmail(Customer[] customers, Scanner sc) {
    	System.out.println("Enter customer eamil to view details: ");
    	String searchEmail=sc.nextLine();
    	int customerCount = customers.length;
    	boolean customerFound = false;

        for (int i = 0; i < customerCount; i++) {
            Customer customer = customers[i];

            if (customer.getEmail().equalsIgnoreCase(searchEmail)) {
                // Customer found, display details
                System.out.println("Customer Details for Email: " + searchEmail);
                System.out.println("Customer ID: " + customer.getCustomerID());
                System.out.println("Customer Name: " + customer.getCustomerName());
                System.out.println("Email: " + customer.getEmail());
                System.out.println("Address: " + customer.getAddress());
                System.out.println("Contact Number: " + customer.getContactNumber());
                customerFound = true;
                break; // No need to continue searching once found
            }
        }

        if (!customerFound) {
            System.out.println("No Such Customer Exist with the Given Email: " + searchEmail);
        }
    }
}
```

```jsx
//ProductManagement.java
package onlinegrocery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

class Product {
    private String productID;
    private String productName;
    private String productDescription;
    private int availableQuantities;
    private double price;

    public Product(String productID, String productName, String productDescription, int availableQuantities, double price) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.availableQuantities = availableQuantities;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getAvailableQuantities() {
        return availableQuantities;
    }

    public void setAvailableQuantities(int availableQuantities) {
        this.availableQuantities = availableQuantities;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    //Validation Methods
    public static boolean isValidProductName(String name) {
        return name.matches("[a-zA-Z]+") && name.length()<=50;
    }
    public static boolean isValidProductDescription(String description) {
        return description.length()<=100;
    }
    @Override
    public String toString() {
        return "Product ID: " + getProductID() + ", Name: " + getProductName() +
                ", Description: " + getProductDescription() + ", Quantities: " + getAvailableQuantities() +
                ", Price: " + getPrice();
    }
}

public class ProductManagement {
    public static final int MAX_PRODUCTS = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        List<Product> products = new ArrayList<>();

        int productCount = 0;

        while (productCount < MAX_PRODUCTS) {
            String productID = generateProductID(random);
            System.out.println("Generated Product ID: " + productID);

            System.out.print("Product Name: ");
            String productName = scanner.nextLine();
            if (!Product.isValidProductName(productName)) {
                System.out.println("Alert: Product Name must have alphabets only and should not be blank or null.");
                continue;
            }
            

            System.out.print("Product Description: ");
            String productDescription = scanner.nextLine();
            if (!Product.isValidProductDescription(productDescription)) {
                System.out.println("Alert: Product Description length must be less than 100 characters.");
                continue;
            }

            System.out.print("Available Quantities: ");
            int availableQuantities = scanner.nextInt();

            System.out.print("Price: ");
            double price = scanner.nextDouble();

            Product product = new Product(productID, productName, productDescription, availableQuantities, price);
            products.add(product);
            productCount++;

            System.out.println("Product added successfully");

            System.out.print("Do you want to add another product? (yes/no): ");
            scanner.nextLine(); // Consume the newline character
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        displayProducts(products);
        findCostliestProduct(products);
        displayProductsSortedByQuantity(products);
    }

    public static String generateProductID(Random random) {
        return "1-" + (1000 + random.nextInt(9000)) + "-" + (1000 + random.nextInt(9000)) + "-" + (1 + random.nextInt(9));
    }

    public static void displayProducts(List<Product> products) {
        System.out.println("Added Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void findCostliestProduct(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Product List is Empty");
            return;
        }

        Product costliestProduct = products.get(0);

        for (Product product : products) {
            if (product.getPrice() > costliestProduct.getPrice()) {
                costliestProduct = product;
            }
        }

        System.out.println("Costliest Product:");
        System.out.println(costliestProduct);
    }

    public static void displayProductsSortedByQuantity(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Product List is Empty");
            return;
        }

        // Sort the products based on quantities in ascending order
        products.sort((p1, p2) -> Integer.compare(p1.getAvailableQuantities(), p2.getAvailableQuantities()));

        System.out.println("Products Sorted by Quantity (Ascending):");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
```
