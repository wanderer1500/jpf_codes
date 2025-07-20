import gov.nasa.jpf.PropertyListenerAdapter;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.*;
import gov.nasa.jpf.Property;

public class NoNegativeBalanceProperty extends PropertyListenerAdapter implements Property {

    @Override
    public boolean check(Search search, VM vm) {
        for (ClassInfo ci : vm.getClassInfos()) {
            if (ci.getName().equals("Account")) {
                FieldInfo fi = ci.getDeclaredStaticField("balance");
                if (fi != null) {
                    ElementInfo ei = ci.getStaticElementInfo();
                    int balance = ei.getIntField(fi);
                    if (balance < 0) {
                        return false;  // Violation: balance shouldn't be negative
                    }
                }
            }
        }
        return true;  // Property holds
    }

    @Override
    public String getErrorMessage() {
        return "Account balance went negative!";
    }

    @Override
    public Property clone() {
        return this;
    }
}
