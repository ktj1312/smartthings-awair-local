/**
 *  Awair Local (v.0.0.1)
 *
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

definition(
        name: "Awair Local",
        namespace: "ktj1312",
        author: "ktj1312",
        description: "Awair Local SmartApp",
        category: "My Apps"
)

preferences {
    page(name: "mainPage")
    page(name: "addDevicePage")
    page(name: "typePage")
    page(name: "deleteDevicePage")
}

def mainPage() {
    log.debug "Executing mainPage"
    dynamicPage(name: "mainPage", title: "Awair Manage", nextPage: null, uninstall: true, install: true) {
        section("Configure Awair API"){
            href "addDevicePage", title: "Add HA Device", description:""
            href "deleteDevicePage", title: "Delete HA Device", description:""
        }
    }
}

def typePage() {
    dynamicPage(name: "typePage", title: "Select a type", nextPage: "mainPage") {
        section("Configure Device"){
            input "awairType", "enum", title: "type", required: true, options: ["Omni", "Other"], defaultValue: "Default"
            input "awairAddress", "text", type: "text", title: "어웨어 IP 주소", description: "enter awair address must be [ip]:[port] ", required: true
        }
    }
}

def addDevicePage(){
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
        if(!addedDNIList.contains("awair-local-" + entity_id)){

            list.push("${entity_id}")
            log.debug list

        }
    }

    dynamicPage(name: "addDevicePage", nextPage: "typePage") {
        section ("Add Awair Device") {
            input(name: "selectedAddAwairDevice", title:"Select" , type: "enum", required: true, options: list, defaultValue: "None")
        }
    }
}

def deleteDevicePage(){
    log.debug "Executing Delete Page"

    def list = []
    list.push("None")
    def childDevices = getAllChildDevices()
    childDevices.each {childDevice->
        list.push(childDevice.label + " -> " + childDevice.deviceNetworkId)
    }
    dynamicPage(name: "deleteDevicePage", nextPage: "mainPage") {
        section ("Delete Awair Device") {
            input(name: "selectedDeleteAwairDevice", title:"Select" , type: "enum", required: true, options: list, defaultValue: "None")
        }
    }
}

def installed() {
    log.debug "Installed with settings: ${settings}"

    initialize()

    app.updateSetting("selectedAddAwairDevice", "None")
    app.updateSetting("selectedDeleteAwairDevice", "None")
}

def addAwairChildDevice(){

    if(settings.selectedAddAwairDevice){
        if(settings.selectedAddAwairDevice != "None"){
            def entity_id = settings.selectedAddAwairDevice
            def dni = "awair-local-" + entity_id

            def awairDevice = getAwairDeviceByEntityId(entity_id)

            if(awairDevice){
                def dth = "Awair-" + awairType + "-Local"
                def name = entity_id

                try{
                    def childDevice = addChildDevice("ktj1312", dth, dni, location.hubs[0].id, [
                            "label": name
                    ])

                    childDevice.setAwairSetting(awairAddress, entity_id)
                    log.debug awairDevice

                    childDevice.refresh()
                }catch(err){
                    log.error "Add Awair Device ERROR >> ${err}"
                }
            }else{
                log.error "Awair is not present"
            }
        }
    }
}

def deleteChildDevice(){
    if(settings.selectedDeleteAwairDevice){
        if(settings.selectedDeleteAwairDevice != "None"){
            log.debug "DELETE >> " + settings.selectedDeleteAwairDevice
            def nameAndDni = settings.selectedDeleteAwairDevice.split(" -> ")
            try{
                deleteChildDevice(nameAndDni[1])
            }catch(err){
                log.error err
            }
        }
    }
}

def getAwairDeviceByEntityId(entity_id){
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

def initialize() {
    log.debug "initialize"

    deleteChildDevice()
    addAwairChildDevice()

}

