package Demo.IO.chapter11;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.jar.Pack200;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/8 11:43
 */
public class PackProgressMonitor extends ProgressMonitor implements PropertyChangeListener {
    public PackProgressMonitor(Component parentComponent) {
        super(parentComponent, null, "Packing", -1, 100);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ((evt.getPropertyName().equals(Pack200.Packer.PROGRESS))) {
            String newValue = (String)evt.getNewValue();
            int value = Integer.parseInt(newValue);
            setProgress(value);
        }
    }
}
