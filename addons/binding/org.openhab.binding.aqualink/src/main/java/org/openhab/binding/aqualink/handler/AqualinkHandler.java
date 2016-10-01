/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.aqualink.handler;

import static org.openhab.binding.aqualink.AqualinkBindingConstants.*;

import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.RefreshType;
import org.openhab.binding.aqualink.internal.AqualinkLEDStatus;
import org.openhab.binding.aqualink.internal.AqualinkStatus;
import org.openhab.binding.aqualink.internal.AqualinkStatusListener;
import org.openhab.binding.aqualink.internal.AqualinkWebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link AqualinkHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Sriram Balakrishnan - Initial contribution
 */
public class AqualinkHandler extends BaseThingHandler implements AqualinkStatusListener {

    private Logger logger = LoggerFactory.getLogger(AqualinkHandler.class);
    private AqualinkWebSocketClient myClient;
    private AqualinkStatus lastStatus = new AqualinkStatus();
    private AqualinkLEDStatus lastLEDStatus = new AqualinkLEDStatus();

    public AqualinkHandler(Thing thing) {
        super(thing);
        myClient = new AqualinkWebSocketClient("ws://192.168.0.225:6500", this);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.debug("handleCommand for channel {}: {}", channelUID.getId(), command.toString());
        if (command == RefreshType.REFRESH) {
            switch (channelUID.getIdWithoutGroup()) {
                case CHANNEL_AUX1_SWITCH:
                    updateState(CHANNEL_AUX1_SWITCH,
                            (lastLEDStatus.getAux1().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_AUX2_SWITCH:
                    updateState(CHANNEL_AUX2_SWITCH,
                            (lastLEDStatus.getAux2().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_AUX3_SWITCH:
                    updateState(CHANNEL_AUX3_SWITCH,
                            (lastLEDStatus.getAux3().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_AUX4_SWITCH:
                    updateState(CHANNEL_AUX4_SWITCH,
                            (lastLEDStatus.getAux4().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_AUX5_SWITCH:
                    updateState(CHANNEL_AUX5_SWITCH,
                            (lastLEDStatus.getAux5().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_AUX6_SWITCH:
                    updateState(CHANNEL_AUX6_SWITCH,
                            (lastLEDStatus.getAux6().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_AUX7_SWITCH:
                    updateState(CHANNEL_AUX7_SWITCH,
                            (lastLEDStatus.getAux7().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_SPA_SWITCH:
                    updateState(CHANNEL_SPA_SWITCH,
                            (lastLEDStatus.getSpa().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_PUMP_SWITCH:
                    updateState(CHANNEL_PUMP_SWITCH,
                            (lastLEDStatus.getPump().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_SOLAR_HEATER:
                    updateState(CHANNEL_SOLAR_HEATER,
                            (lastLEDStatus.getSolar_heater().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_FREEZE_PROTECTION_FLAG:
                    updateState(CHANNEL_FREEZE_PROTECTION_FLAG,
                            (lastStatus.getFreeze_protection().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;

                case CHANNEL_POOL_HEATER:
                    updateState(CHANNEL_POOL_HEATER,
                            (lastLEDStatus.getPool_heater().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_SPA_HEATER:
                    updateState(CHANNEL_SPA_HEATER,
                            (lastLEDStatus.getSpa_heater().equals("on")) ? OnOffType.ON : OnOffType.OFF);
                    break;
                case CHANNEL_AIR_TEMPERATURE:
                    updateState(CHANNEL_AIR_TEMPERATURE, new DecimalType(lastStatus.getAir_temp()));
                    break;
                case CHANNEL_POOL_TEMPERATURE:
                    updateState(CHANNEL_POOL_TEMPERATURE, new DecimalType(lastStatus.getPool_temp()));
                    break;
                case CHANNEL_SPA_TEMPERATURE:
                    updateState(CHANNEL_SPA_TEMPERATURE, new DecimalType(lastStatus.getSpa_temp()));
                    break;
                case CHANNEL_FREEZE_PROTECT_SET_POINT:
                    updateState(CHANNEL_FREEZE_PROTECT_SET_POINT, new DecimalType(lastStatus.getFrz_protect_set_pnt()));
                    break;

                case CHANNEL_POOL_HEATER_SET_POINT:
                    updateState(CHANNEL_POOL_HEATER_SET_POINT, new DecimalType(lastStatus.getPool_htr_set_pnt()));
                    break;
                case CHANNEL_SPA_HEATER_SET_POINT:
                    updateState(CHANNEL_SPA_HEATER_SET_POINT, new DecimalType(lastStatus.getSpa_htr_set_pnt()));
                    break;

            }
        } else {
            switch (channelUID.getIdWithoutGroup()) {
                case CHANNEL_PUMP_SWITCH:
                    myClient.togglePoolSwitchState();
                    break;
                case CHANNEL_SPA_SWITCH:
                    myClient.toggleSpaSwitchState();
                    break;
                case CHANNEL_AUX1_SWITCH:
                    myClient.toggleAux1State();
                    break;
                case CHANNEL_AUX2_SWITCH:
                    myClient.toggleAux2State();
                    break;
                case CHANNEL_AUX3_SWITCH:
                    myClient.toggleAux3State();
                    break;
                case CHANNEL_AUX4_SWITCH:
                    myClient.toggleAux4State();
                    break;
                case CHANNEL_AUX5_SWITCH:
                    myClient.toggleAux5State();
                    break;
                case CHANNEL_AUX6_SWITCH:
                    myClient.toggleAux6State();
                    break;
                case CHANNEL_AUX7_SWITCH:
                    myClient.toggleAux7State();
                    break;
                case CHANNEL_POOL_HEATER:
                    myClient.togglePoolHeaterState();
                    break;
                case CHANNEL_SPA_HEATER:
                    myClient.toggleSpaHeaterState();
                    break;
                case CHANNEL_SOLAR_HEATER:
                    myClient.toggleSolarHeaterState();
                    break;

            }
        }
    }

    @Override
    public void initialize() {
        try {
            myClient.connect();
            logger.info("Myclient connected successfully");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, e.getMessage());
        }
    }

    @Override
    public void dispose() {
        try {
            myClient.disconnect();
        } catch (Exception e) {

        }
    }

    @Override
    public void aqualinkStatusChanged(AqualinkStatus aqualinkStatus) {
        if (!aqualinkStatus.getAir_temp().equals(lastStatus.getAir_temp())) {
            updateState(CHANNEL_AIR_TEMPERATURE, new DecimalType(aqualinkStatus.getAir_temp()));
            lastStatus.setAir_temp(aqualinkStatus.getAir_temp());
        }
        if (!aqualinkStatus.getPool_temp().equals(lastStatus.getPool_temp())) {
            updateState(CHANNEL_POOL_TEMPERATURE, new DecimalType(aqualinkStatus.getPool_temp()));
            lastStatus.setPool_temp(aqualinkStatus.getPool_temp());
        }
        if (!aqualinkStatus.getSpa_temp().equals(lastStatus.getSpa_temp())) {
            updateState(CHANNEL_SPA_TEMPERATURE, new DecimalType(aqualinkStatus.getSpa_temp()));
            lastStatus.setSpa_temp(aqualinkStatus.getSpa_temp());
        }
        if (!aqualinkStatus.getFrz_protect_set_pnt().equals(lastStatus.getFrz_protect_set_pnt())) {
            updateState(CHANNEL_FREEZE_PROTECT_SET_POINT, new DecimalType(aqualinkStatus.getFrz_protect_set_pnt()));
            lastStatus.setFrz_protect_set_pnt(aqualinkStatus.getFrz_protect_set_pnt());
        }
        if (!aqualinkStatus.getPool_htr_set_pnt().equals(lastStatus.getPool_htr_set_pnt())) {
            updateState(CHANNEL_POOL_HEATER_SET_POINT, new DecimalType(aqualinkStatus.getPool_htr_set_pnt()));
            lastStatus.setPool_htr_set_pnt(aqualinkStatus.getPool_htr_set_pnt());
        }

        if (!aqualinkStatus.getSpa_htr_set_pnt().equals(lastStatus.getSpa_htr_set_pnt())) {
            updateState(CHANNEL_SPA_HEATER_SET_POINT, new DecimalType(aqualinkStatus.getSpa_htr_set_pnt()));
            lastStatus.setSpa_htr_set_pnt(aqualinkStatus.getSpa_htr_set_pnt());
        }

        if (!aqualinkStatus.getFreeze_protection().equals(lastStatus.getFreeze_protection())) {
            updateState(CHANNEL_FREEZE_PROTECTION_FLAG,
                    (aqualinkStatus.getFreeze_protection().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastStatus.setFreeze_protection(aqualinkStatus.getFreeze_protection());
        }

        updateStatus(ThingStatus.ONLINE);
    }

    @Override
    public void aqualinkLEDStatusChanged(AqualinkLEDStatus aqualinkLEDStatus) {
        if (!aqualinkLEDStatus.getAux1().equals(lastLEDStatus.getAux1())) {
            updateState(CHANNEL_AUX1_SWITCH, (aqualinkLEDStatus.getAux1().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setAux1(aqualinkLEDStatus.getAux1());
        }
        if (!aqualinkLEDStatus.getAux2().equals(lastLEDStatus.getAux2())) {
            updateState(CHANNEL_AUX2_SWITCH, (aqualinkLEDStatus.getAux2().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setAux2(aqualinkLEDStatus.getAux2());
        }
        if (!aqualinkLEDStatus.getAux3().equals(lastLEDStatus.getAux3())) {
            updateState(CHANNEL_AUX3_SWITCH, (aqualinkLEDStatus.getAux3().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setAux3(aqualinkLEDStatus.getAux3());
        }
        if (!aqualinkLEDStatus.getAux4().equals(lastLEDStatus.getAux4())) {
            updateState(CHANNEL_AUX4_SWITCH, (aqualinkLEDStatus.getAux4().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setAux1(aqualinkLEDStatus.getAux4());
        }
        if (!aqualinkLEDStatus.getAux5().equals(lastLEDStatus.getAux5())) {
            updateState(CHANNEL_AUX5_SWITCH, (aqualinkLEDStatus.getAux5().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setAux5(aqualinkLEDStatus.getAux5());
        }
        if (!aqualinkLEDStatus.getAux6().equals(lastLEDStatus.getAux6())) {
            updateState(CHANNEL_AUX6_SWITCH, (aqualinkLEDStatus.getAux6().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setAux6(aqualinkLEDStatus.getAux6());
        }
        if (!aqualinkLEDStatus.getAux7().equals(lastLEDStatus.getAux7())) {
            updateState(CHANNEL_AUX7_SWITCH, (aqualinkLEDStatus.getAux7().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setAux7(aqualinkLEDStatus.getAux7());
        }
        if (!aqualinkLEDStatus.getPump().equals(lastLEDStatus.getPump())) {
            updateState(CHANNEL_PUMP_SWITCH, (aqualinkLEDStatus.getPump().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setPump(aqualinkLEDStatus.getPump());
        }
        if (!aqualinkLEDStatus.getSpa().equals(lastLEDStatus.getSpa())) {
            updateState(CHANNEL_SPA_SWITCH, (aqualinkLEDStatus.getSpa().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setSpa(aqualinkLEDStatus.getSpa());
        }
        if (!aqualinkLEDStatus.getPool_heater().equals(lastLEDStatus.getPool_heater())) {
            updateState(CHANNEL_POOL_HEATER,
                    (aqualinkLEDStatus.getPool_heater().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setPool_heater(aqualinkLEDStatus.getPool_heater());
        }
        if (!aqualinkLEDStatus.getSpa_heater().equals(lastLEDStatus.getSpa_heater())) {
            updateState(CHANNEL_SPA_HEATER,
                    (aqualinkLEDStatus.getSpa_heater().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setSpa_heater(aqualinkLEDStatus.getSpa_heater());
        }
        if (!aqualinkLEDStatus.getSolar_heater().equals(lastLEDStatus.getSolar_heater())) {
            updateState(CHANNEL_SOLAR_HEATER,
                    (aqualinkLEDStatus.getSolar_heater().equals("on")) ? OnOffType.ON : OnOffType.OFF);
            lastLEDStatus.setSolar_heater(aqualinkLEDStatus.getSolar_heater());
        }
        updateStatus(ThingStatus.ONLINE);
    }

    @Override
    public void aqualinkConnectionDropped(String error) {
        updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, error);

    }

    @Override
    public void aqualinkConnectionEstablished() {
        updateStatus(ThingStatus.ONLINE);

    }
}
