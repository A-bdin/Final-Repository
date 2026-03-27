import java.util.HashMap;

class Bkash {
    private HashMap<String, Double> accounts = new HashMap<>();
    private HashMap<String, String> pins = new HashMap<>();

    public Bkash() {
        accounts.put("01711111111", 1000.0);
        pins.put("01711111111", "1234");
    }

    public boolean pay(String phone, String pin, double amount) {

        if (!accounts.containsKey(phone)) {
            System.out.println("Account not found");
            return false;
        }

        if (!pins.get(phone).equals(pin)) {
            System.out.println("Wrong PIN");
            return false;
        }

        double balance = accounts.get(phone);

        if (balance >= amount) {
            accounts.put(phone, balance - amount);
            System.out.println("bKash payment successful");
            System.out.println("Remaining balance: " + (balance - amount));
            return true;
        }

        System.out.println("Not enough balance");
        return false;
    }
}
