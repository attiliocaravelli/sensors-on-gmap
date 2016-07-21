package com.demo.webapplication.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.demo.webapplication.domain.Alert;
import com.demo.webapplication.domain.Location;
import com.demo.webapplication.domain.Reading;
import com.demo.webapplication.domain.Sensor;
import com.demo.webapplication.domain.User;
import com.demo.webapplication.domain.UserProfile;
import com.demo.webapplication.repositories.AlertRepository;
import com.demo.webapplication.repositories.LocationRepository;
import com.demo.webapplication.repositories.ReadingRepository;
import com.demo.webapplication.repositories.SensorRepository;
import com.demo.webapplication.repositories.UserProfileRepository;
import com.demo.webapplication.repositories.UserRepository;
import com.demo.webapplication.utils.DataGenerator;

@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
    private SensorRepository sensorRepository;

	@Autowired
    private AlertRepository alertRepository;

	@Autowired
    private LocationRepository locationRepository;

	@Autowired
    private ReadingRepository readingRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private UserProfileRepository userProfileRepository;
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	createTestSensors();
    	createTestUsers();
    }
    
    private void createTestSensors() {
    	Location[] locations = DataGenerator.generateThreeLocations();
        for (Location location : locations) locationRepository.save(location);
        	
        Sensor[] sensors = DataGenerator.generateThreeSensors(locations);
        for (Sensor sensor : sensors) {
        	sensorRepository.save(sensor);
        	Alert[] alerts = DataGenerator.generateThreeAlerts(sensor);
        	for (Alert alert : alerts) alertRepository.save(alert);
        	Reading[] readings = DataGenerator.generateThreeReadings(sensor);
        	for (Reading reading : readings) readingRepository.save(reading);
        	
        }
    }
    
    private void createTestUsers() {
    	UserProfile[] profiles = DataGenerator.generateTwoUserProfiles();
    	for (UserProfile profile: profiles) userProfileRepository.save(profile);
    	User[] users = DataGenerator.generateThreeUsers(profiles);
    	for (User user : users) userRepository.save(user);
    }
}
