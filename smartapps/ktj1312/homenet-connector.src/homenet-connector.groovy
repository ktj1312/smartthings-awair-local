/**
 *  HomeNet Connector (v.0.0.1)
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
 */

import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import groovy.transform.Field


@Field
        attrList = ["switch", "button", "contact", "motion", "acceleration", "presence", "carbonDioxide", "carbonMonoxide", "energy", "illuminance", "power", "shock", "humidity", "lqi",
                    "rssi", "temperature", "thermostatSetpoint", "threeAxis", "touch", "voltage", "water"]

@Field
        CAPABILITY_MAP = [
                "accelerationSensors": [
                        name: "Acceleration Sensor",
                        capability: "capability.accelerationSensor",
                        attributes: [
                                "acceleration"
                        ]
                ],
                "alarm": [
                        name: "Alarm",
                        capability: "capability.alarm",
                        attributes: [
                                "alarm"
                        ],
                        action: "actionAlarm"
                ],
                "beacon": [
                        name: "Beacon",
                        capability: "capability.beacon",
                        attributes: [
                                "presence"
                        ]
                ],
                "button": [
                        name: "Button",
                        capability: "capability.button",
                        attributes: [
                                "button"
                        ]
                ],
                "carbonDioxideMeasurement": [
                        name: "Carbon Dioxide Measurement",
                        capability: "capability.carbonDioxideMeasurement",
                        attributes: [
                                "carbonDioxide"
                        ]
                ],
                "carbonMonoxideDetector": [
                        name: "Carbon Monoxide Detector",
                        capability: "capability.carbonMonoxideDetector",
                        attributes: [
                                "carbonMonoxide"
                        ]
                ],
                "colorControl": [
                        name: "Color Control",
                        capability: "capability.colorControl",
                        attributes: [
                                "hue",
                                "saturation",
                                "color"
                        ],
                        action: "actionColor"
                ],
                "colorTemperature": [
                        name: "Color Temperature",
                        capability: "capability.colorTemperature",
                        attributes: [
                                "colorTemperature"
                        ],
                        action: "actionColorTemperature"
                ],
                "consumable": [
                        name: "Consumable",
                        capability: "capability.consumable",
                        attributes: [
                                "consumable"
                        ],
                        action: "actionConsumable"
                ],
                "contactSensors": [
                        name: "Contact Sensor",
                        capability: "capability.contactSensor",
                        attributes: [
                                "contact"
                        ]
                ],
                "doorControl": [
                        name: "Door Control",
                        capability: "capability.doorControl",
                        attributes: [
                                "door"
                        ],
                        action: "actionOpenClosed"
                ],
                "energyMeter": [
                        name: "Energy Meter",
                        capability: "capability.energyMeter",
                        attributes: [
                                "energy"
                        ]
                ],
                "garageDoors": [
                        name: "Garage Door Control",
                        capability: "capability.garageDoorControl",
                        attributes: [
                                "door"
                        ],
                        action: "actionOpenClosed"
                ],
                "illuminanceMeasurement": [
                        name: "Illuminance Measurement",
                        capability: "capability.illuminanceMeasurement",
                        attributes: [
                                "illuminance"
                        ]
                ],
                "levels": [
                        name: "Switch Level",
                        capability: "capability.switchLevel",
                        attributes: [
                                "level"
                        ],
                        action: "actionLevel"
                ],
                "lock": [
                        name: "Lock",
                        capability: "capability.lock",
                        attributes: [
                                "lock"
                        ],
                        action: "actionLock"
                ],
                "motionSensors": [
                        name: "Motion Sensor",
                        capability: "capability.motionSensor",
                        attributes: [
                                "motion"
                        ],
                        action: "actionActiveInactive"
                ],
                "pHMeasurement": [
                        name: "pH Measurement",
                        capability: "capability.pHMeasurement",
                        attributes: [
                                "pH"
                        ]
                ],
                "powerMeters": [
                        name: "Power Meter",
                        capability: "capability.powerMeter",
                        attributes: [
                                "power"
                        ]
                ],
                "presenceSensors": [
                        name: "Presence Sensor",
                        capability: "capability.presenceSensor",
                        attributes: [
                                "presence"
                        ],
                        action: "actionPresence"
                ],
                "humiditySensors": [
                        name: "Relative Humidity Measurement",
                        capability: "capability.relativeHumidityMeasurement",
                        attributes: [
                                "humidity"
                        ]
                ],
                "relaySwitch": [
                        name: "Relay Switch",
                        capability: "capability.relaySwitch",
                        attributes: [
                                "switch"
                        ],
                        action: "actionOnOff"
                ],
                "shockSensor": [
                        name: "Shock Sensor",
                        capability: "capability.shockSensor",
                        attributes: [
                                "shock"
                        ]
                ],
                "signalStrength": [
                        name: "Signal Strength",
                        capability: "capability.signalStrength",
                        attributes: [
                                "lqi",
                                "rssi"
                        ]
                ],
                "sleepSensor": [
                        name: "Sleep Sensor",
                        capability: "capability.sleepSensor",
                        attributes: [
                                "sleeping"
                        ]
                ],
                "smokeDetector": [
                        name: "Smoke Detector",
                        capability: "capability.smokeDetector",
                        attributes: [
                                "smoke"
                        ]
                ],
                "soundSensor": [
                        name: "Sound Sensor",
                        capability: "capability.soundSensor",
                        attributes: [
                                "sound"
                        ]
                ],
                "stepSensor": [
                        name: "Step Sensor",
                        capability: "capability.stepSensor",
                        attributes: [
                                "steps",
                                "goal"
                        ]
                ],
                "switches": [
                        name: "Switch",
                        capability: "capability.switch",
                        attributes: [
                                "switch"
                        ],
                        action: "actionOnOff"
                ],
                "soundPressureLevel": [
                        name: "Sound Pressure Level",
                        capability: "capability.soundPressureLevel",
                        attributes: [
                                "soundPressureLevel"
                        ]
                ],
                "tamperAlert": [
                        name: "Tamper Alert",
                        capability: "capability.tamperAlert",
                        attributes: [
                                "tamper"
                        ]
                ],
                "temperatureSensors": [
                        name: "Temperature Measurement",
                        capability: "capability.temperatureMeasurement",
                        attributes: [
                                "temperature"
                        ]
                ],
                "thermostat": [
                        name: "Thermostat",
                        capability: "capability.thermostat",
                        attributes: [
                                "temperature",
                                "heatingSetpoint",
                                "coolingSetpoint",
                                "thermostatSetpoint",
                                "thermostatMode",
                                "thermostatFanMode",
                                "thermostatOperatingState"
                        ],
                        action: "actionThermostat"
                ],
                "thermostatCoolingSetpoint": [
                        name: "Thermostat Cooling Setpoint",
                        capability: "capability.thermostatCoolingSetpoint",
                        attributes: [
                                "coolingSetpoint"
                        ],
                        action: "actionCoolingThermostat"
                ],
                "thermostatFanMode": [
                        name: "Thermostat Fan Mode",
                        capability: "capability.thermostatFanMode",
                        attributes: [
                                "thermostatFanMode"
                        ],
                        action: "actionThermostatFan"
                ],
                "thermostatHeatingSetpoint": [
                        name: "Thermostat Heating Setpoint",
                        capability: "capability.thermostatHeatingSetpoint",
                        attributes: [
                                "heatingSetpoint"
                        ],
                        action: "actionHeatingThermostat"
                ],
                "thermostatMode": [
                        name: "Thermostat Mode",
                        capability: "capability.thermostatMode",
                        attributes: [
                                "thermostatMode"
                        ],
                        action: "actionThermostatMode"
                ],
                "thermostatOperatingState": [
                        name: "Thermostat Operating State",
                        capability: "capability.thermostatOperatingState",
                        attributes: [
                                "thermostatOperatingState"
                        ]
                ],
                "thermostatSetpoint": [
                        name: "Thermostat Setpoint",
                        capability: "capability.thermostatSetpoint",
                        attributes: [
                                "thermostatSetpoint"
                        ]
                ],
                "threeAxis": [
                        name: "Three Axis",
                        capability: "capability.threeAxis",
                        attributes: [
                                "threeAxis"
                        ]
                ],
                "timedSession": [
                        name: "Timed Session",
                        capability: "capability.timedSession",
                        attributes: [
                                "timeRemaining",
                                "sessionStatus"
                        ],
                        action: "actionTimedSession"
                ],
                "touchSensor": [
                        name: "Touch Sensor",
                        capability: "capability.touchSensor",
                        attributes: [
                                "touch"
                        ]
                ],
                "valve": [
                        name: "Valve",
                        capability: "capability.valve",
                        attributes: [
                                "contact"
                        ],
                        action: "actionOpenClosed"
                ],
                "voltageMeasurement": [
                        name: "Voltage Measurement",
                        capability: "capability.voltageMeasurement",
                        attributes: [
                                "voltage"
                        ]
                ],
                "waterSensors": [
                        name: "Water Sensor",
                        capability: "capability.waterSensor",
                        attributes: [
                                "water"
                        ]
                ],
                "windowShades": [
                        name: "Window Shade",
                        capability: "capability.windowShade",
                        attributes: [
                                "windowShade"
                        ],
                        action: "actionOpenClosed"
                ]
        ]


definition(
        name: "HomeNet Connector",
        namespace: "ktj1312",
        author: "ktj1312",
        description: "A Connector between Homenet and ST",
        category: "My Apps",
        iconUrl: "http://www.icons101.com/icon_png/size_512/id_81659/Apt.png",
        iconX2Url: "http://www.icons101.com/icon_png/size_512/id_81659/Apt.png",
        iconX3Url: "http://www.icons101.com/icon_png/size_512/id_81659/Apt.png",
        oauth: true
)

preferences {
    page(name: "mainPage")
    page(name: "haDevicePage")
    page(name: "haAddDevicePage")
    page(name: "haTypePage")
    page(name: "haDeleteDevicePage")
}


def mainPage() {
    log.debug "Executing mainPage"
    dynamicPage(name: "mainPage", title: "HomeNet Manage", nextPage: null, uninstall: true, install: true) {
        section("Configure HomeNet API"){
            input "haAddress", "string", title: "HA address", required: true
            href "haDevicePage", title: "Get HA Devices", description:""
            href "haAddDevicePage", title: "Add HA Device", description:""
            href "haDeleteDevicePage", title: "Delete HA Device", description:""
        }
        section() {
            paragraph "View this SmartApp's configuration to use it in other places."
            href url:"${apiServerUrl("/api/smartapps/installations/${app.id}/config?access_token=${state.accessToken}")}", style:"embedded", required:false, title:"Config", description:"Tap, select, copy, then click \"Done\""
        }
    }
}

def haTypePage() {
    dynamicPage(name: "haTypePage", title: "Select a type", nextPage: "mainPage") {
        section("Configure HA API"){
            input "haAddType", "enum", title: "type", required: true, options: ["Switch", "Light","Thermostat"], defaultValue: "Default"
        }
    }
}

def haDevicePage(){
    log.debug "Executing haDevicePage"
    getDataList()

    dynamicPage(name: "haDevicePage", title:"Get HA Devices", refreshInterval:5) {
        section("Please wait for the API to answer, this might take a couple of seconds.") {
            if(state.latestHttpResponse) {
                if(state.latestHttpResponse == 200) {
                    paragraph "Connected \nOK: 200"
                } else {
                    paragraph "Connection error \nHTTP response code: " + state.latestHttpResponse
                }
            }
        }
    }
}

def haAddDevicePage(){
    def addedDNIList = []
    def childDevices = getAllChildDevices()

    childDevices.each {childDevice->
        addedDNIList.push(childDevice.deviceNetworkId)
    }

    def list = []
    list.push("None")

    log.debug state.dataList

    state.dataList.each {
        def entity_id = "${it.entity_id}"
        if(!addedDNIList.contains("ha-connector-" + entity_id)){
            if(entity_id.contains("light.") || entity_id.contains("switch.") || entity_id.contains("fan.") || entity_id.contains("cover.") || entity_id.contains("sensor.") || entity_id.contains("vacuum.") || entity_id.contains("device_tracker.") || entity_id.contains("climate.")){

                list.push("${entity_id}")
                log.debug list
            }
        }
    }

    dynamicPage(name: "haAddDevicePage", nextPage: "haTypePage") {
        section ("Add HA Devices") {
            input(name: "selectedAddHADevice", title:"Select" , type: "enum", required: true, options: list, defaultValue: "None")
        }
    }

}

def haDeleteDevicePage(){
    log.debug "Executing Delete Page"

    def list = []
    list.push("None")
    def childDevices = getAllChildDevices()
    childDevices.each {childDevice->
        list.push(childDevice.label + " -> " + childDevice.deviceNetworkId)
    }
    dynamicPage(name: "haDeleteDevicePage", nextPage: "mainPage") {
        section ("Delete HA Device") {
            input(name: "selectedDeleteHADevice", title:"Select" , type: "enum", required: true, options: list, defaultValue: "None")
        }
    }
}

def installed() {
    log.debug "Installed with settings: ${settings}"

    initialize()

    if (!state.accessToken) {
        createAccessToken()
    }

    app.updateSetting("selectedAddHADevice", "None")
    app.updateSetting("selectedDeleteHADevice", "None")
}

def stateChangeHandler(evt) {
    log.debug evt

    def device = evt.getDevice()
    if(device){
        def type = device.hasCommand("on") ? "switch" : "sensor"

        def theAtts = device.supportedAttributes
        def resultMap = [:]
        resultMap["friendly_name"] = device.displayName
        theAtts.each {att ->
            def item = {}
            try{
                def _attr = "${att.name}State"
                def val = device."$_attr".value
                resultMap["${att.name}"] = val
            }catch(e){
            }
        }

        def value = "${evt.value}"
        /*
        if("${evt.name}" == "lastCheckin"){
        	def existMotion = False
        	device.capabilities.each {cap ->
            	if("Motion Sensor".equalsIgnoreCase("${cap.name}")){
                	existMotion = True
                }
            }
            if(existMotion == True){
        		value = device.motionState.value
            }
        }
        */
        String pattern = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s+/]";
        String idString = "${type}.st_" + device.name.toLowerCase().replaceAll(pattern, "_") + "_" + device.deviceNetworkId.toLowerCase().replaceAll(pattern, "_");
        String ids = idString.replaceAll(" ", "_")

        def options = [
                "method": "POST",
                "path": ("/api/states/" + ids),
                "headers": [
                        "HOST": settings.haAddress,
                        "Content-Type": "application/json"
                ],
                "body":[
                        "state":value,
                        "attributes":resultMap
                ]
        ]

//        log.debug "ST -> HA >> [${device.displayName}(${device.deviceNetworkId}) : ${value}]"
        def myhubAction = new physicalgraph.device.HubAction(options, null, [callback: notifyCallback])
        sendHubCommand(myhubAction)
    }
}

def notifyCallback(physicalgraph.device.HubResponse hubResponse) {
    def msg, json, status
    try {
        msg = parseLanMessage(hubResponse.description)
//        log.debug(msg)
    } catch (e) {
        logger('warn', "Exception caught while parsing data: "+e);
    }
}

def updated() {
    log.debug "Updated with settings: ${settings}"

    // Unsubscribe from all events
    unsubscribe()
    // Subscribe to stuff
    initialize()

    CAPABILITY_MAP.each { key, capability ->
        capability["attributes"].each { attribute ->
            for (item in settings[key]) {
                if(settings[key]){
                    subscribe(item, attribute, stateChangeHandler)
                }
            }
        }
    }

    app.updateSetting("haAddType", "Default Sensor")
    app.updateSetting("selectedAddHADevice", "None")
    app.updateSetting("selectedDeleteHADevice", "None")
}

// Return list of displayNames
def getDeviceNames(devices) {
    def list = []
    devices.each{device->
        list.push(device.displayName)
    }
    list
}

def getHADeviceByEntityId(entity_id){
    log.debug entity_id
    def target
    log.debug state.dataList
    state.dataList.each {haDevice ->

        if(haDevice.entity_id == entity_id){
            target = haDevice
        }
    }
    target
}

def addHAChildDevice(){
//	String[] dth1_list = ["active", "inactive", "open", "closed", "dry", "wet", "clear", "detected", "not present", "present", "home", "not_home", "on", "off"]

    if(settings.selectedAddHADevice){
        if(settings.selectedAddHADevice != "None"){
            def entity_id = settings.selectedAddHADevice
            def dni = "ha-connector-" + entity_id

            def haDevice = getHADeviceByEntityId(entity_id)

            if(haDevice){
                def dth = "HomeNet " + haAddType
                def name = entity_id

                try{
                    def childDevice = addChildDevice("ktj1312", dth, dni, location.hubs[0].id, [
                            "label": name
                    ])

                    childDevice.setHASetting(settings.haAddress, entity_id)
                    log.debug haDevice
                    childDevice.setStatus(haDevice.state)

                    childDevice.refresh()
                }catch(err){
                    log.error "Add HA Device ERROR >> ${err}"
                }
            }else{
                log.error "haDevice is not present"
            }
        }
    }
}

def deleteChildDevice(){
    if(settings.selectedDeleteHADevice){
        if(settings.selectedDeleteHADevice != "None"){
            log.debug "DELETE >> " + settings.selectedDeleteHADevice
            def nameAndDni = settings.selectedDeleteHADevice.split(" -> ")
            try{
                deleteChildDevice(nameAndDni[1])
            }catch(err){

            }
        }
    }
}

def initialize() {
    log.debug "initialize"

    deleteChildDevice()
    addHAChildDevice()

}

def dataCallback(physicalgraph.device.HubResponse hubResponse) {
    def msg, json, status
    try {
        msg = parseLanMessage(hubResponse.description)

        status = msg.status
        json = msg.json
        state.dataList = json
        state.latestHttpResponse = status

    } catch (e) {
        logger('warn', "Exception caught while parsing data: "+e);
    }
}

def getDataList(){

    def options = [
            "method": "GET",
            "path": "/api/states",
            "headers": [
                    "HOST": settings.haAddress,
                    "Accept": "application/json"
            ]
    ]

    def myhubAction = new physicalgraph.device.HubAction(options, null, [callback: dataCallback])
    sendHubCommand(myhubAction)
}

def deviceCommandList(device) {
    device.supportedCommands.collectEntries { command->
        [
                (command.name): (command.arguments)
        ]
    }
}

def deviceAttributeList(device) {
    device.supportedAttributes.collectEntries { attribute->
        try {
            [
                    (attribute.name): device.currentValue(attribute.name)
            ]
        } catch(e) {
            [
                    (attribute.name): null
            ]
        }
    }
}

def updateDevice(){
    //log.debug "POST >>>> param:${params}"
    //log.debug request.JSON.entity_id
    def request = request.JSON
    //def dni = "ha-connector-" + params.entity_id
    def dni = "ha-connector-" + request.entity_id
    log.debug dni
    try{
        def device = getChildDevice(dni)
        if(device){
            log.debug "HA -> ST >> [${dni} : ${request}]"
            device.setStatus(request)
            if(params.unit){
                device.setUnitOfMeasurement(params.unit)
            }
        }
    }catch(err){
        log.error "${err}"
    }

    def deviceJson = new groovy.json.JsonOutput().toJson([result: true])
    render contentType: "application/json", data: deviceJson
}

def authError() {
    [error: "Permission denied"]
}

def renderConfig() {
    def configJson = new groovy.json.JsonOutput().toJson([
            description: "HomeNet Connector API",
            platforms: [
                    [
                            platform: "SmartThings HomeNet Connector",
                            name: "HomeNet Connector",
                            app_url: apiServerUrl("/api/smartapps/installations/"),
                            app_id: app.id,
                            access_token:  state.accessToken
                    ]
            ],
    ])

    def configString = new groovy.json.JsonOutput().prettyPrint(configJson)
    render contentType: "text/plain", data: configString
}

mappings {
    if (!params.access_token || (params.access_token && params.access_token != state.accessToken)) {
        path("/config")                         { action: [GET: "authError"] }
        path("/update")                         { action: [GET: "authError"]  }
        path("/getSTDevices")                   { action: [GET: "authError"]  }
        path("/get") {
            action: [
                    GET: "authError",
                    POST: "authError"
            ]
        }

    } else {
        path("/config")                         { action: [GET: "renderConfig"]  }
        path("/update")                         { action: [POST: "updateDevice"]  }
        path("/getSTDevices")                   { action: [GET: "getSTDevices"]  }
        path("/get") {
            action: [
                    GET: "getSTDevice",
                    POST: "updateSTDevice"
            ]
        }
    }
}