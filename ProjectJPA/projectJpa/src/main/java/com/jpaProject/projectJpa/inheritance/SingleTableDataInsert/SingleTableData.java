package com.jpaProject.projectJpa.inheritance.SingleTableDataInsert;

import com.jpaProject.projectJpa.inheritance.SingleTable.DigitalProduct;
import com.jpaProject.projectJpa.repository.SingleTableProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class SingleTableData {

    @Autowired
     static SingleTableProductRepo repository;

    public static void main(String[] args) {




        DigitalProduct course =
                new DigitalProduct();

        course.setName(
                "Java Masterclass"
        );

        course.setPrice(
                new BigDecimal("999")
        );

        course.setDownloadUrl(
                "course.com/download"
        );

        course.setLicenseKey(
                "ABC123"
        );

        repository.save(course);


    }


}
