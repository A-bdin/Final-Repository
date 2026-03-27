import java.util.ArrayList;

class Bkash {

    private class Account {
        String phone;
        double balance;
        String pin;

        Account(String phone, double balance, String pin) {
            this.phone = phone;
            this.balance = balance;
            this.pin = pin;
        }
    }

    private ArrayList<Account> accounts = new ArrayList<>();

    public Bkash() {
        accounts.add(new Account("01711111111", 1000.0, "1234"));
    }

    private Account findAccount(String phone) {
        for (Account acc : accounts) {
            if (acc.phone.equals(phone)) {
                return acc;
            }
        }
        return null;
    }

    public boolean pay(String phone, String pin, double amount) {

        Account acc = findAccount(phone);

        if (acc == null) {
            System.out.println("Account not found");
            return false;
        }

        if (!acc.pin.equals(pin)) {
            System.out.println("Wrong PIN");
            return false;
        }

        if (acc.balance >= amount) {
            acc.balance -= amount;
            System.out.println("bKash payment successful");
            System.out.println("Remaining balance: " + acc.balance);
            return true;
        }

        System.out.println("Not enough balance");
        return false;
    }
}
