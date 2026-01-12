package isp.bad;

// THE PROBLEM: A cheap HP Printer.
public class HpPrinter implements SmartDevice {
    public void print() {
        System.out.println("Printing...");
    }

    // ISP VIOLATION: I am forced to implement fax(), but I can't fax!
    public void fax() {
        throw new UnsupportedOperationException("I can't fax!");
    }

    // ISP VIOLATION: I am forced to implement scan(), but I can't scan!
    public void scan() {
        throw new UnsupportedOperationException("I can't scan!");
    }
}