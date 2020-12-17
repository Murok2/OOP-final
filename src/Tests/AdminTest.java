package Tests;

import classes.Admin;

import static org.junit.Assert.*;

public class AdminTest {
    Admin admin = new Admin(1, "maratbekovamurok@gmail.com", "qwerty");

    @org.junit.Test
    public void getIdTest() {
        assertEquals(1, admin.getId(), 1);
    }

    @org.junit.Test
    public void setIdTest() {
        admin.setId(1);
        assertEquals(1, admin.getId(), 1);
    }

    @org.junit.Test
    public void getEmailTest() {
        assertEquals("maratbekovamurok@gmail.com", admin.getEmail());
    }

    @org.junit.Test
    public void setEmailTest() {
        admin.setEmail("maratbekovamurok@gmail.com");
        assertEquals("maratbekovamurok@gmail.com", admin.getEmail());
    }

    @org.junit.Test
    public void getPasswordTest() {
        assertEquals("qwerty", admin.getPassword());
    }

    @org.junit.Test
    public void setPasswordTest() {
        admin.setPassword("qwerty");
        assertEquals("qwerty", admin.getPassword());
    }

    @org.junit.Test
    public void toStringTest() {
        assertEquals("adminInfo{id=1, email=maratbekovamurok@gmail.com, password=qwerty}", admin.toString());
    }
}