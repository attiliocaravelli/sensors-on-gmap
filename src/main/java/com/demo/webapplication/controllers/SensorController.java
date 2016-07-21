package com.demo.webapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.webapplication.domain.Sensor;
import com.demo.webapplication.services.SensorService;

@Controller
public class SensorController {

	@Autowired
    private SensorService sensorService;
    
    @RequestMapping(value = "/sensor/all", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("sensors", sensorService.listAll());
        model.addAttribute("user", getPrincipal());
        return "sensors";
    }

    @RequestMapping("/sensor/{id}")
    public String showDetails(@PathVariable Integer id, Model model){
        model.addAttribute("sensor", sensorService.getById(id));
        model.addAttribute("user", getPrincipal());
        return "details";
    }
    
    @RequestMapping(value = "/sensor/map", method = RequestMethod.GET)
    public String showMap(Model model){
    	model.addAttribute("user", getPrincipal());
        return "sensorsmap";
    }
    
    @RequestMapping(value = "/sensor/json", method = RequestMethod.GET)
    public ResponseEntity<List<Sensor>> jsonSensors() {
        List<Sensor> sensors = makeCollection(sensorService.listAll());
        if(sensors.isEmpty()){
            return new ResponseEntity<List<Sensor>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Sensor>>(sensors, HttpStatus.OK);
    }
	
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}
    
    private List<Sensor> makeCollection(Iterable<Sensor> sensors) {
        List<Sensor> list = new ArrayList<>();
        for (Sensor sensor : sensors) {
            list.add(sensor);
        }
        return list;
    }
    
    private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}
