package com.demo.webapplication.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.webapplication.configuration.RepositoryConfiguration;
import com.demo.webapplication.domain.State;
import com.demo.webapplication.domain.User;
import com.demo.webapplication.domain.UserProfile;
import com.demo.webapplication.repositories.UserProfileRepository;
import com.demo.webapplication.repositories.UserRepository;
import com.demo.webapplication.utils.DataGenerator;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolationException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private UserProfileRepository userProfileRepository;
	
	private User user;

	@Before
	public void init() {
		user = new User();
		user.setFirstName("attilio");
		user.setLastName("caravelli");
		user.setEmail("test@gmail.com");
		user.setSsoId(user.getEmail());
		user.setState(State.LOCKED.toString());
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String pw = encoder.encode("test");
		user.setPassword(pw);
	}

	@Test
	public void saveNewAndUpdatedUser_Test(){
		
		//save customer, verify has ID value after save
		assertNull(user.getId()); //null before save
		userRepository.save(user);
		assertNotNull(user.getId()); //not null after save   

		UserProfile[] profiles = DataGenerator.generateTwoUserProfiles();
		userProfileRepository.save(profiles[0]);
		Set<UserProfile> setUserProfile = DataGenerator.generateSetUserProfile(profiles[0]);
		user.setUserProfiles(setUserProfile);
		
		//updating the profile set
		userRepository.save(user);
		
		//fetch from DB
		User fetchedUser = userRepository.findOne(user.getId());

		//should not be null
		assertNotNull(fetchedUser);
		
		UserProfile[] profileExpected = setUserProfile.toArray(new UserProfile[setUserProfile.size()]);
		
		UserProfile[] profileFetched = fetchedUser.getUserProfiles().toArray(new UserProfile[fetchedUser.getUserProfiles().size()]);
		
		assertArrayEquals("Profile set is not equal", profileFetched, profileExpected);
		
		//should equal (by assumption two users are egual by ID)
		assertEquals(user, fetchedUser);
	
		//update description and save
		fetchedUser.setSsoId("testUpdated@gmail.com");
		userRepository.save(fetchedUser);
		
		//get from DB, should be updated
		User fetchedUpdatedUser = userRepository.findOne(fetchedUser.getId());
		assertEquals(fetchedUser, fetchedUpdatedUser);

		//verify count of customers in DB
		long userCount = userRepository.count();
		assertEquals(userCount, 1);

		//get all customers, list should only have one
		Iterable<User> users = userRepository.findAll();

		assertEquals(sizeOf(users), 1);
	}

	@Test(expected = ConstraintViolationException.class)
	public void customerCountryValidation_Test(){
		//setup customer
		user.setSsoId("FreeText-NoEmailConstraint");
		userRepository.save(user);
	}

	private int sizeOf(Iterable<User> users) {
  	  int count = 0;
        for(@SuppressWarnings("unused") User p : users){
            count++;
        }
        return count;
  }
}
