package com.demo.webapplication.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.webapplication.domain.Alert;
import com.demo.webapplication.domain.Location;
import com.demo.webapplication.domain.Reading;
import com.demo.webapplication.domain.Sensor;
import com.demo.webapplication.domain.State;
import com.demo.webapplication.domain.User;
import com.demo.webapplication.domain.UserProfile;
import com.demo.webapplication.domain.UserProfileType;

public class DataGenerator {
	
	public static User[] generateThreeUsers(UserProfile[] profiles) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		User[] users = new User[3];
		for (int i = 0; i < 3; i++) {
			User user = new User();
			user.setFirstName("name user " + i);
			user.setLastName("last name user " + i);
			user.setEmail("test"+i+"@gmail.com");
			user.setSsoId(user.getEmail());
			if (i == 0) user.setState(State.LOCKED.toString());
			else user.setState(State.ACTIVE.toString());
			String pw = encoder.encode("test");
			user.setPassword(pw);
			Set<UserProfile> setUserProfile;
			if (i == 1) setUserProfile = generateSetUserProfile(profiles[0]);
			else setUserProfile = generateSetUserProfile(profiles[0],profiles[1]);
			user.setUserProfiles(setUserProfile);
			users[i] = user;
		}
        return users;
	}
	
	public static UserProfile[] generateTwoUserProfiles() {
		UserProfile[] profiles = new UserProfile[2];
		UserProfile admin = new UserProfile();
		admin.setType(UserProfileType.ADMIN.toString());
		profiles[0] = admin;
		UserProfile dba = new UserProfile();
		dba.setType(UserProfileType.DBA.toString());
		profiles[1] = dba;
		return profiles;
	}
	public static Set<UserProfile> generateSetUserProfile(UserProfile... profiles) {
		Set<UserProfile> set = new HashSet<>();
		for (UserProfile profile : profiles) 
			set.add(profile);
        return set;
	}
	
	public static Sensor[] generateThreeSensors(Location[] locations) {
		Sensor[] sensors = new Sensor[3];
		for (int i = 0; i < 3; i++) {
			Sensor sensor = new Sensor();
			sensor.setName("Sensor n. " + i);
			sensor.setDescription("Sensor Test " + i);
			sensor.setLocation(locations[i]);
			sensors[i] = sensor;
		}
        return sensors;
	}
	
	public static Location[] generateThreeLocations() {
		Location[] locations = new Location[3];
		for (int i = 0; i < 3; i++) {
			Location location = new Location();
			switch (i) {
				case 0:
				   location.setCity("BERLIN");
				   location.setLatitude(new BigDecimal(52.52000659999999));
				   location.setLongitude(new BigDecimal(13.404953999999975));
				   break;
				case 1:
				   location.setCity("HAMBURG");
			       location.setLatitude(new BigDecimal(53.5510846));
			       location.setLongitude(new BigDecimal(9.99368179999999)); 
				   break;
				case 2:
				   location.setCity("MUNICH");
			       location.setLatitude(new BigDecimal(48.1351253));
			       location.setLongitude(new BigDecimal(11.581980599999952));
				   break;
			}
			locations[i] = location;
		}
        return locations;
	}
	
	public static Reading[] generateThreeReadings(Sensor sensor) {
		Reading[] readings = new Reading[3];
		for (int i = 0; i < 3; i++) {
			Reading reading = new Reading();
			BigDecimal temperature = generateRandomBigDecimalFromRange(new BigDecimal(15.50), new BigDecimal(32.07));
			reading.setTemperature(temperature);
			reading.setTimestamp(new Date());
			reading.setUnit("C");
			reading.setSensor(sensor);
			readings[i] = reading;
		}
        return readings;
	}
	
	public static Alert[] generateThreeAlerts(Sensor sensor) {
		Alert[] alerts = new Alert[3];
		for (int i = 0; i < 3; i++) {
			Alert alert = new Alert();
			alert.setDescription("Sensor alert test " + i);
			alert.setTimestamp(new Date());
			alert.setSensor(sensor);
			alerts[i] = alert;
		}
        return alerts;
	}
	
	private static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
	    BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	    return randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
}
