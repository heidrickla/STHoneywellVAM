/**
 *  Copyright 2015 SmartThings
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
 *  Honeywell VAM - Stay
 *
 *  Author: Josh Rizzo
 *
 *  Date: 2017-04-12
 */
metadata {
	definition (name: "Honeywell VAM - Stay", namespace: "smartthings/honeywell-vam", author: "Josh Rizzo") {
		capability "Actuator"
		capability "Switch"
		capability "Momentary"
		capability "Sensor"
	}

	// simulator metadata
	simulator {
	}

	// UI tile definitions
	tiles {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off", label: 'Push', action: "momentary.push", backgroundColor: "#ffffff", nextState: "on"
			state "on", label: 'Push', action: "momentary.push", backgroundColor: "#00A0DC"
		}
		main "switch"
		details "switch"
	}
}

def parse(String description) {
}

def push() {
	def params = [
      uri: "http://joshuarizzo.com/system_http_api/API_REV01/AdvancedSecurity/ArmWithCode?arming=STAY&ucode=8606&operation=set"
  ]

  try {
      httpGet(params) { resp ->
          resp.headers.each {
          log.debug "${it.name} : ${it.value}"
      }
      log.debug "response contentType: ${resp.contentType}"
      log.debug "response data: ${resp.data}"
      }
  } catch (e) {
      log.error "something went wrong: $e"
  }
}

def on() {
	push()
}

def off() {
	push()
}
