import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        UserService userService = new UserService();
        ProductService productService = new ProductService();
        AuthService authService = new AuthService(userService);


        Cart cart = new Cart();


        authService.loadUsers();
        loadProducts(productService);

        boolean running = true;
        while (running) {
            System.out.println("\n===== Marketplace =====");
            System.out.println("1. Register  2. Login  3. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch(choice) {
                case 1: registerMenu(sc, authService); break;
                case 2: loginMenu(sc, authService, userService, productService, cart, sc); break;
                case 3: running = false; break;
                default: System.out.println("Invalid choice!");
            }
        }


        authService.saveUsers();
        saveProducts(productService);

        System.out.println("Goodbye!");
    }

    // registering
    private static void registerMenu(Scanner sc, AuthService authService) {
        System.out.print("Username: "); String username = sc.nextLine();
        System.out.print("Password: "); String password = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Role (ADMIN/SELLER/BUYER): "); String role = sc.nextLine().toUpperCase();
        authService.register(username, password, email, role);
    }

    // logging in
    private static void loginMenu(Scanner sc, AuthService authService, UserService userService,
                                  ProductService productService, Cart cart, Scanner s) {
        System.out.print("Username: "); String username = sc.nextLine();
        System.out.print("Password: "); String password = sc.nextLine();
        User curr = authService.login(username, password);
        if (curr == null) {
            System.out.println("Login failed!");
            return;
        }

        switch(curr.getRole()) {
            case "ADMIN":
                adminMenu(sc, userService);
                break;
            case "SELLER":
                if(curr instanceof Seller){
                    sellerMenu(sc, productService, (Seller) curr, sc);
                } else {
                    System.out.println("Error: Seller object mismatch.");
                }
                break;
            case "BUYER":
                buyerMenu(sc, productService, cart);
                break;
            default:
                System.out.println("Unknown role!");
        }
    }

    // admin
    private static void adminMenu(Scanner sc, UserService userService) {
        boolean back = false;
        while(!back) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. List Users  2. Ban User  3. Unban User  4. Remove User  5. Back");
            System.out.print("Choice: "); int ch = sc.nextInt(); sc.nextLine();

            switch(ch) {
                case 1: userService.listAllUsers(); break;
                case 2: System.out.print("Username to ban: "); userService.banUser(sc.nextLine()); break;
                case 3: System.out.print("Username to unban: "); userService.unbanUser(sc.nextLine()); break;
                case 4:
                    System.out.print("Username to remove: ");
                    User u = userService.findUser(sc.nextLine());
                    if(u != null) userService.removeUser(u);
                    break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // seller
    private static void sellerMenu(Scanner sc, ProductService productService, Seller seller, Scanner s) {
        boolean back = false;
        while(!back) {
            System.out.println("\n--- Seller Menu ---");
            System.out.println("1. Add Product  2. Update Product  3. Remove Product  4. Browse Products  5. Back");
            System.out.print("Choice: "); int ch = sc.nextInt(); sc.nextLine();

            switch(ch) {
                case 1:
                    System.out.print("Product ID: "); String id = sc.nextLine();
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Price: "); double price = sc.nextDouble();
                    System.out.print("Quantity: "); int qty = sc.nextInt(); sc.nextLine();
                    Product p = new Product(id,name,price,qty,seller.getUsername());
                    productService.addProduct(p);
                    seller.addProductId(id);
                    System.out.println("Product added!");
                    break;
                case 2:
                    System.out.print("Product ID to update: "); String uid = sc.nextLine();
                    Product up = productService.findProduct(uid);
                    if(up == null || !up.getSellerUsername().equals(seller.getUsername())){
                        System.out.println("Cannot update!"); break;
                    }
                    System.out.print("New Price: "); up.setPrice(sc.nextDouble());
                    System.out.print("New Quantity: "); up.setQuantity(sc.nextInt()); sc.nextLine();
                    System.out.println("Updated!");
                    break;
                case 3:
                    System.out.print("Product ID to remove: "); String rid = sc.nextLine();
                    Product rp = productService.findProduct(rid);
                    if(rp == null || !rp.getSellerUsername().equals(seller.getUsername())){
                        System.out.println("Cannot remove!"); break;
                    }
                    productService.removeProduct(rp);
                    seller.removeProductId(rid);
                    System.out.println("Removed!");
                    break;
                case 4: productService.browseProducts(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // buying
    private static void buyerMenu(Scanner sc, ProductService productService, Cart cart) {
        boolean back = false;
        while(!back) {
            System.out.println("\n--- Buyer Menu ---");
            System.out.println("1. Browse Products  2. Add to Cart  3. View Cart  4. Checkout  5. Back");
            System.out.print("Choice: "); int ch = sc.nextInt(); sc.nextLine();

            switch(ch) {
                case 1: productService.browseProducts(); break;
                case 2:
                    System.out.print("Product ID to buy: "); String id = sc.nextLine();
                    Product p = productService.findProduct(id);
                    if(p == null || !p.isActive()){ System.out.println("Invalid product"); break; }
                    System.out.print("Quantity: "); int qty = sc.nextInt(); sc.nextLine();
                    cart.addItem(p, qty);
                    break;
                case 3: cart.viewCart(); break;
                case 4: cart.checkout(); break;
                case 5: back = true; break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private static void loadProducts(ProductService productService){
        try{
            BufferedReader br = new BufferedReader(new FileReader("products.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] p = line.split(",");
                if(p.length < 6) continue;
                Product prod = new Product(p[0], p[1], Double.parseDouble(p[2]),
                        Integer.parseInt(p[3]), p[4]);
                if(!Boolean.parseBoolean(p[5])) prod.deactivate();
                productService.addProduct(prod);
            }
            br.close();
        } catch(Exception e){
            System.out.println("Error loading products: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void saveProducts(ProductService productService){
        try{
            FileWriter fw = new FileWriter("products.txt");
            for(Product p : productService.getProducts()){
                fw.write(p.toFileString() + "\n");
            }
            fw.close();
        } catch(Exception e){
            System.out.println("Error saving products: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
