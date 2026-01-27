import java.util.ArrayList;

public class ProductService {

    private ArrayList<Product> products;

    public ProductService() {
        products = new ArrayList<>();
    }

    // add product
    public void addProduct(Product product) {
        products.add(product);
    }

    // remove product
    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Product findProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return products.get(i);
            }
        }
        return null;
    }

    // browsing products
    public void browseProducts() {
        System.out.println("----- Products -----");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.isActive()) {
                p.printProduct();
            }
        }
    }

    // List all products (optionally for admin)
    public void listAllProducts() {
        System.out.println("----- All Products -----");
        for (int i = 0; i < products.size(); i++) {
            products.get(i).printProduct();
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> loadedProducts) {
        this.products = loadedProducts;
    }
}

