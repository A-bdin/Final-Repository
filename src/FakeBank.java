import java.util.HashMap;

class FakeBank {
    private HashMap<String, Double> accounts = new HashMap<>();

    public FakeBank() {
        accounts.put("1111-2222", 1000.0);
        accounts.put("3333-4444", 500.0);
    }

    public boolean pay(String card, double amount) {

        if (!accounts.containsKey(card)) {
            System.out.println("Card not found");
            return false;
        }

        double balance = accounts.get(card);

        if (balance >= amount) {
            accounts.put(card, balance - amount);
            System.out.println("Card payment successful");
            System.out.println("Remaining balance: " + (balance - amount));
            return true;
        }

        System.out.println("Insufficient balance");
        return false;
    }
}
