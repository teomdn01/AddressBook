package com.example;

import com.example.domain.Contact;
import com.example.repository.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ContactRepositoryTests {
    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testAddNew() {
        Contact contact = new Contact();
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("src/test/java/com/example/photos/parrot.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", bos);
            byte[] data = bos.toByteArray();
           // Blob picture = new SerialBlob(data);
            contact.setPicture(data);

        } catch (IOException e) {
            e.printStackTrace();
            Assert.isTrue(false, "Error when uploading photo");
        }
        contact.setName("Parrot");
        contact.setAddress("str. Caraibe - nr.12");

        Contact savedContact = contactRepository.save(contact);
        Assertions.assertNotNull(savedContact);
        Assertions.assertTrue(savedContact.getId() > 0);
    }
}
