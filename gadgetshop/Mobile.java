public class Mobile extends Gadget {
    private int callingCredit;

    public Mobile(String model, double price, int weight, String size, int callingCredit) {
        super(model, price, weight, size);
        this.callingCredit = callingCredit;
    }

    public int getCallingCredit() {
        return callingCredit;
    }

    public void addCredit(int credit) {
        if (credit > 0) {
            callingCredit += credit;
            System.out.println("Added " + credit + " minutes of calling credit.");
        } else {
            System.out.println("Please enter a positive amount.");
        }
    }

    public void makeCall(String phoneNumber, int duration) {
        if (callingCredit >= duration) {
            callingCredit -= duration;
            System.out.println("Calling " + phoneNumber + " for " + duration + " minutes.");
        } else {
            System.out.println("Insufficient credit to make the call.");
        }
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Calling Credit: " + callingCredit + " minutes");
    }
}
