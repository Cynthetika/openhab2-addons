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
    public final static String CHANNEL_POOL_TEMPERATURE = "pool_temp";
    public final static String CHANNEL_SPA_TEMPERATURE = "spa_temp";
    public final static String CHANNEL_AIR_TEMPERATURE = "air_temp";

    public final static String CHANNEL_FREEZE_PROTECTION_FLAG = "freeze_protection";
    public final static String CHANNEL_FREEZE_PROTECT_SET_POINT = "frz_protect_set_pnt";

    public final static String CHANNEL_PUMP_SWITCH = "pump";
    public final static String CHANNEL_SPA_SWITCH = "spa";
    public final static String CHANNEL_POOL_HEATER = "pool_heater";
    public final static String CHANNEL_SPA_HEATER = "spa_heater";
    public final static String CHANNEL_SOLAR_HEATER = "solar_heater";
    public final static String CHANNEL_POOL_HEATER_SET_POINT = "pool_htr_set_pnt";
    public final static String CHANNEL_SPA_HEATER_SET_POINT = "spa_htr_set_pnt";

    public final static String CHANNEL_AUX1_SWITCH = "aux1";
    public final static String CHANNEL_AUX2_SWITCH = "aux2";
    public final static String CHANNEL_AUX3_SWITCH = "aux3";
    public final static String CHANNEL_AUX4_SWITCH = "aux4";
    public final static String CHANNEL_AUX5_SWITCH = "aux5";
    public final static String CHANNEL_AUX6_SWITCH = "aux6";
    public final static String CHANNEL_AUX7_SWITCH = "aux7";
}
