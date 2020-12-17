package Tests;

import classes.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user = new User("Murok", "Maratbek kyzy", "maratbekovamurok@gmail.com", "qwerty", "f", "+996500220812", "Pr. Aytmatov");

    @Test
    public void getFirstNameTest() {
        assertEquals("Murok", user.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        user.setFirstName("Murok");
        assertEquals("Murok", user.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        assertEquals("Maratbek kyzy", user.getLastName());
    }

    @Test
    public void setLastNameTest() {
        user.setLastName("Maratbek kyzy");
        assertEquals("Maratbek kyzy", user.getLastName());
    }

    @Test
    public void getUserNameTest() {
        assertEquals("maratbekovamurok@gmail.com", user.getUserName());
    }

    @Test
    public void setUserNameTest() {
        user.setUserName("maratbekovamurok@gmail.com");
        assertEquals("maratbekovamurok@gmail.com", user.getUserName());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("qwerty", user.getPassword());
    }

    @Test
    public void setPasswordTest() {
        user.setPassword("qwerty");
        assertEquals("qwerty", user.getPassword());
    }

    @Test
    public void getGenderTest() {
        assertEquals("f", user.getGender());
    }

    @Test
    public void setGenderTest() {
        user.setGender("f");
        assertEquals("f", user.getGender());
    }

    @Test
    public void getPhoneNumberTest() {
        assertEquals("+996500220812", user.getPhoneNumber());
    }

    @Test
    public void setPhoneNumberTest() {
        user.setPhoneNumber("+996500220812");
        assertEquals("+996500220812", user.getPhoneNumber());
    }

    @Test
    public void getAddressTest() {
        assertEquals("Pr. Aytmatov", user.getAddress());
    }

    @Test
    public void setAddressTest() {
        user.setAddress("Pr. Aytmatov");
        assertEquals("Pr. Aytmatov", user.getAddress());
    }

    @Test
    public void toStringTest() {
        assertEquals("User{firstName='Murok', lastName='Maratbek kyzy', userName='maratbekovamurok@gmail.com', password='qwerty', gender='f', phoneNumber='+996500220812', address='Pr. Aytmatov'}", user.toString());
    }
}