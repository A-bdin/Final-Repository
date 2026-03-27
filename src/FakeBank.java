import java.util.ArrayList;

class FakeBank {

    private class Account {
        String card;
        double balance;

        Account(String card, double balance) {
            this.card = card;
            this.balance = balance;
        }
    }

    private ArrayList<Account> accounts = new ArrayList<>();

    public FakeBank() {
        accounts.add(new Account("1111-2222", 1000.0));
        accounts.add(new Account("3333-4444", 500.0));
    }

    private Account findAccount(String card) {
        for (Account acc : accounts) {
            if (acc.card.equals(card)) {
                return acc;
            }
        }
        return null;
    }

    public boolean pay(String card, double amount) {

        Account acc = findAccount(card);

        if (acc == null) {
            System.out.println("Card not found");
            return false;
        }

        if (acc.balance >= amount) {
            acc.balance -= amount;
            System.out.println("Card payment successful");
            System.out.println("Remaining balance: " + acc.balance);
            return true;
        }

        System.out.println("Insufficient balance");
        return false;
    }
}
