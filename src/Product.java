public class Product {
    private String id;
    private String name;
    private String sellerUsername;
    private double price;
    private int quantity;
    private boolean active;

    public Product(String id, String name, double price, int quantity, String sellerUsername){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerUsername = sellerUsername;
        this.active = true;
    }

    public String getId(){ return id; }
    public String getName(){ return name; }
    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price = price; }
    public int getQuantity(){ return quantity; }
    public void setQuantity(int qty){ this.quantity = qty; }
    public String getSellerUsername(){ return sellerUsername; }
    public boolean isActive(){ return active; }
    public void deactivate(){ active=false; }

    public String toFileString(){
        return id+","+name+","+price+","+quantity+","+sellerUsername+","+active;
    }

    public void printProduct(){
        System.out.println(id+" | "+name+" | $"+price+" | Qty:"+quantity+" | Seller:"+sellerUsername);
    }
}
