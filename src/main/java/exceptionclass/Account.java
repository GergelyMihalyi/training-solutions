package exceptionclass;

public class Account {

    private static final double INITIAL_MAX_SUBTRACT = 100000;

    private String accountNumber;
    private double balance;
    private double maxSubtract;

    public Account(String accountNumber, double balance) {
        if (accountNumber == null) {
            throw new IllegalArgumentException("Empty accountnumber");
        }
        if (isValidAccountNumber(accountNumber)) {
            throw new InvalidBankOperationException("Wrong account number format", ErrorCode.INVALID_ACCOUNTNUMBER);
        }
        this.accountNumber = accountNumber;
        this.balance = balance;
        maxSubtract = INITIAL_MAX_SUBTRACT;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public double getMaxSubtract() {
        return maxSubtract;
    }

    public void setMaxSubtract(double maxSubtract) {
        if (maxSubtract <= 0) {
            throw new InvalidBankOperationException("Invalid amount", ErrorCode.INVALID_AMOUNT);
        }
        this.maxSubtract = maxSubtract;
    }

    public void subtract(double subtract) {
        if (subtract > balance) {
            throw new InvalidBankOperationException("The balance is too low", ErrorCode.LOW_BALANCE);
        }
        if (subtract > maxSubtract || subtract <= 0) {
            throw new InvalidBankOperationException("Invalid amount", ErrorCode.INVALID_AMOUNT);
        }
        balance -= subtract;
    }

    public void deposit(double deposit) {
        if (deposit <= 0) {
            throw new InvalidBankOperationException("Invalid amount", ErrorCode.INVALID_AMOUNT);
        }
        balance += deposit;
    }

    private boolean isValidAccountNumber(String accountNumber) {
        int len = accountNumber.length();
        if (len != 16 && len != 24) {
            return false;
        }
        int chkmul[] = {9, 7, 3, 1};
        int sum = 0;
        int i = 0;
        char[] d = accountNumber.toCharArray();
        for (char ch : accountNumber.toCharArray()) {
            int number = Character.getNumericValue(ch);
            if (number < 0 || 9 < number) {
                return false;
            }
            sum += number * chkmul[i % 4];
            ++i;
            if (i == 8 && sum % 10 != 0) {
                return false;
            }
        }

        if (sum % 10 != 0) {
            return false;
        }

        return true;

    }
}
