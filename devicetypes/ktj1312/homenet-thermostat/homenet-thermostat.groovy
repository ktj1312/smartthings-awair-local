/**
 *  HomeNet Switch (v.0.0.1)
 *
 *  Authors
 *   - ktj1312@naver.com
 *  Copyright 2018
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

import groovy.json.JsonSlurper

metadata {
    definition (name: "HomeNet Thermostat", namespace: "ktj1312", author: "ktj1312") {
        capability "Actuator"
        capability "Temperature Measurement"
        capability "Thermostat"
        capability "Thermostat Mode"
        capability "Thermostat Heating Setpoint"
        capability "Thermostat Operating State"
        capability "Sensor"
        capability "Refresh"

        attribute "lastCheckin", "Date"

        command "setStatus"
    }

    simulator {
    }

    preferences {
        input name: "baseValue", title:"HA On Value" , type: "string", required: true, defaultValue: "on"
    }

    tiles {
        multiAttributeTile(name:"temperature", type: "generic", width: 6, height: 4){
            tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
                attributeState("temperature", label:'${currentValue}°', icon: "st.alarm.temperature.normal",
                        backgroundColors:[
                                // Fahrenheit color set
                                [value: 0, color: "#153591"],
                                [value: 5, color: "#1e9cbb"],
                                [value: 10, color: "#90d2a7"],
                                [value: 15, color: "#44b621"],
                                [value: 20, color: "#f1d801"],
                                [value: 25, color: "#d04e00"],
                                [value: 30, color: "#bc2323"],
                                [value: 44, color: "#1e9cbb"],
                                [value: 59, color: "#90d2a7"],
                                [value: 74, color: "#44b621"],
                                [value: 84, color: "#f1d801"],
                                [value: 95, color: "#d04e00"],
                                [value: 96, color: "#bc2323"]
                                // Celsius color set (to switch, delete the 13 lines above anmd remove the two slashes at the beginning of the line below)
                                //[value: 0, color: "#153591"], [value: 7, color: "#1e9cbb"], [value: 15, color: "#90d2a7"], [value: 23, color: "#44b621"], [value: 28, color: "#f1d801"], [value: 35, color: "#d04e00"], [value: 37, color: "#bc2323"]
                        ]
                )
            }
            tileAttribute("device.lastCheckin", key: "SECONDARY_CONTROL") {
                attributeState("default", label:'Last Update: ${currentValue}',icon: "st.Health & Wellness.health9")
            }
        }
        valueTile("heatingSetpoint", "device.heatingSetpoint", width:2, height:1, inactiveLabel: false, decoration: "flat") {
            state "heatingSetpoint", label:'${currentValue}° heat', backgroundColor:"#ffffff"
        }

        valueTile("lastOn_label", "", decoration: "flat") {
            state "default", label:'Last\nOn'
        }
        valueTile("lastOn", "device.lastOn", decoration: "flat", width: 3, height: 1) {
            state "default", label:'${currentValue}'
        }
        standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
            state "default", label:"", action:"refresh", icon:"st.secondary.refresh"
        }
        valueTile("lastOff_label", "", decoration: "flat") {
            state "default", label:'Last\nOff'
        }
        valueTile("lastOff", "device.lastOff", decoration: "flat", width: 3, height: 1) {
            state "default", label:'${currentValue}'
        }

        valueTile("ha_url", "device.ha_url", width: 3, height: 1) {
            state "val", label:'${currentValue}', defaultState: true
        }

        valueTile("entity_id", "device.entity_id", width: 3, height: 1) {
            state "val", label:'${currentValue}', defaultState: true
        }
    }
}

// parse events into attributes
def parse(String description) {
    log.debug "Parsing '${description}'"
}

def setStatus(String value){
    if(state.entity_id == null){
        return
    }
    log.debug "Status[${state.entity_id}] >> ${value}"

    def switchBaseValue = "on"
    if(baseValue){
        switchBaseValue = baseValue
    }

    def now = new Date().format("yyyy-MM-dd HH:mm:ss", location.timeZone)
    def _value = (switchBaseValue == value ? "on" : "off")

    if(device.currentValue("switch") != _value){
        sendEvent(name: (_value == "on" ? "lastOn" : "lastOff"), value: now, displayed: false )
    }
    sendEvent(name: "switch", value:_value)
    sendEvent(name: "lastCheckin", value: new Date().format("yyyy-MM-dd HH:mm:ss", location.timeZone), displayed: false)
    sendEvent(name: "entity_id", value: state.entity_id, displayed: false)
}

def setHASetting(url, password, deviceId){
    state.app_url = url
    state.app_pwd = password
    state.entity_id = deviceId

    sendEvent(name: "ha_url", value: state.app_url, displayed: false)
}

def refresh(){
    log.debug "Refresh"
    def options = [
            "method": "GET",
            "path": "/api/states/${state.entity_id}",
            "headers": [
                    "HOST": state.app_url,
                    "Content-Type": "application/json"
            ]
    ]
    sendCommand(options, callback)
}

def on(){
    commandToHA("on")
}

def off(){
    commandToHA("off")
}

def commandToHA(cmd){
    log.debug "Command[${state.entity_id}] >> ${cmd}"
    def temp = state.entity_id.split("\\.")
    def options = [
            "method": "POST",
            "path": "/api/services/" + temp[0] + (cmd == "on" ? "/turn_on" : "/turn_off"),
            "headers": [
                    "HOST": state.app_url,
                    "Content-Type": "application/json"
            ],
            "body":[
                    "entity_id":"${state.entity_id}"
            ]
    ]
    sendCommand(options, null)
}

def callback(physicalgraph.device.HubResponse hubResponse){
    def msg
    try {
        msg = parseLanMessage(hubResponse.description)
        def jsonObj = new JsonSlurper().parseText(msg.body)
        setStatus(jsonObj.state)
    } catch (e) {
        log.error "Exception caught while parsing data: "+e;
    }
}

def updated() {
}

def sendCommand(options, _callback){
    def myhubAction = new physicalgraph.device.HubAction(options, null, [callback: _callback])
    sendHubCommand(myhubAction)
}