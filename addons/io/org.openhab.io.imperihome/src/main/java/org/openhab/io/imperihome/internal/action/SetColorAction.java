/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.io.imperihome.internal.action;

import org.eclipse.smarthome.core.events.EventPublisher;
import org.eclipse.smarthome.core.items.Item;
import org.eclipse.smarthome.core.items.events.ItemCommandEvent;
import org.eclipse.smarthome.core.items.events.ItemEventFactory;
import org.eclipse.smarthome.core.library.types.HSBType;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.openhab.io.imperihome.internal.model.device.AbstractDevice;

/**
 * Items setting RGB color value.
 *
 * @author Pepijn de Geus - Initial contribution
 */
public class SetColorAction extends Action {

    public SetColorAction(EventPublisher eventPublisher) {
        super(eventPublisher);
    }

    @Override
    public boolean supports(AbstractDevice device, Item item) {
        return item.getAcceptedCommandTypes().contains(HSBType.class);
    }

    @Override
    public void perform(AbstractDevice device, Item item, String value) {
        if (value == null || value.length() != 8) {
            logger.error("Invalid parameter: '{}'. Format must be 'aarrggbb'.", value);
            return;
        }

        int r = Integer.parseInt(value.substring(2, 4), 16);
        int g = Integer.parseInt(value.substring(4, 6), 16);
        int b = Integer.parseInt(value.substring(6, 8), 16);

        ItemCommandEvent event;
        if (r == 0 && g == 0 && b == 0) {
            event = ItemEventFactory.createCommandEvent(item.getName(), OnOffType.OFF, COMMAND_SOURCE);
        } else {
            HSBType hsbValue = HSBType.fromRGB(r, g, b);
            event = ItemEventFactory.createCommandEvent(item.getName(), hsbValue, COMMAND_SOURCE);
        }

        eventPublisher.post(event);
    }

}
