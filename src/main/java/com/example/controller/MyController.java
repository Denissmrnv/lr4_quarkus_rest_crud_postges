package com.example.controller;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class MyController {
    @Inject
    StudentRepository studentRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/students")
    public List<Student> getAllStudents()  {
        return studentRepository.findAll().list();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/students/{id}")
    public Student getStudentByID(@RestPath("id") long id)  {
        return studentRepository.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/students")
    @Transactional
    public String addStudent(Student student) {
        studentRepository.persist(student);
        return student.getName() + " " + student.getSurname() + " is added " +
                "to " + student.getGroup();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/students/{id}")
    @Transactional
    public void addStudent(@RestPath("id") long id) {
        System.out.println();
        studentRepository.deleteById(id);
    }
}
