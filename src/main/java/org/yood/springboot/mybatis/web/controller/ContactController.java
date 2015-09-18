package org.yood.springboot.mybatis.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yood.springboot.mybatis.entity.Contact;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.service.ContactService;
import org.yood.springboot.mybatis.service.UserService;
import org.yood.springboot.mybatis.web.exception.UnAuthorizedException;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class ContactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "users/{userId}/contacts",
                    method = RequestMethod.GET)
    public ResponseEntity<?> getAllByUser(@PathVariable int userId) throws IOException, SQLException {

        User user = userService.get(userId);
        if (null == user) {
            return ResponseEntity.notFound().build();
        }
        LOGGER.info("******* {} *******", user);
        return ResponseEntity.ok(contactService.getByUser(user.getId()));
    }


    @RequestMapping(value = "users/{userId}/contacts/{id}",
                    method = RequestMethod.GET)
    public ResponseEntity<?> getFromUser(@PathVariable int userId, @PathVariable int id) throws IOException, SQLException {
        Contact contact = contactService.get(id);
        if (null == contact) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contact);
    }

    @RequestMapping(value = "contacts/{id}",
                    method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable int id) throws SQLException, UnAuthorizedException {
        if (id <= 0) {
            throw UnAuthorizedException.newInstance();
        }
        Contact contact = contactService.get(id);
        if (null == contact) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contact);
    }

    @RequestMapping(value = "contacts",
                    method = RequestMethod.GET)
    public ResponseEntity<?> getAll() throws IOException, SQLException {
        return ResponseEntity.ok(contactService.getAll());
    }

    @RequestMapping(value = "contacts",
                    method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Contact contact) throws IOException, SQLException {
        contactService.add(contact);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "contacts",
                    method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Contact contact) throws IOException, SQLException {
        contactService.update(contact);
        return ResponseEntity.ok().build();
    }
}
