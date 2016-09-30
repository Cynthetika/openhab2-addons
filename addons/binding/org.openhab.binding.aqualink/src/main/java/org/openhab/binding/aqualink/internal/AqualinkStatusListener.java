package org.openhab.binding.aqualink.internal;

public interface AqualinkStatusListener {
    public void aqualinkStatusChanged(AqualinkStatus aqualinkStatus);

    public void aqualinkLEDStatusChanged(AqualinkLEDStatus aqualinkLEDStatus);
}
