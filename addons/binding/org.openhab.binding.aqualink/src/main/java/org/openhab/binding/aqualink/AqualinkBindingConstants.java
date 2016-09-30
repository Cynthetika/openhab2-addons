/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.aqualink;

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link AqualinkBinding} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Sriram Balakrishnan - Initial contribution
 */
public class AqualinkBindingConstants {

    public static final String BINDING_ID = "aqualink";

    // List of all Thing Type UIDs
    public final static ThingTypeUID THING_TYPE_POOL = new ThingTypeUID(BINDING_ID, "pool");

    // List of all Channel ids
    public final static String CHANNEL_POOL_TEMPERATURE = "pooltemperature";
    public final static String CHANNEL_SPA_TEMPERATURE = "spatemperature";
    public final static String CHANNEL_AIR_TEMPERATURE = "airtemperature";

    public final static String CHANNEL_PUMP_SWITCH = "pumpswitch";
    public final static String CHANNEL_SPA_SWITCH = "spaswitch";
    public final static String CHANNEL_POOL_HEATER = "poolheater";
    public final static String CHANNEL_SPA_HEATER = "spaheater";
    public final static String CHANNEL_AUX1_SWITCH = "aux1switch";
    public final static String CHANNEL_AUX2_SWITCH = "aux2switch";
    public final static String CHANNEL_AUX3_SWITCH = "aux3switch";
    public final static String CHANNEL_AUX4_SWITCH = "aux4switch";
    public final static String CHANNEL_AUX5_SWITCH = "aux5switch";
    public final static String CHANNEL_AUX6_SWITCH = "aux6switch";
    public final static String CHANNEL_AUX7_SWITCH = "aux7switch";
}
