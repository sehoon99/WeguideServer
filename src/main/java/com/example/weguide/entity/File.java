package com.example.weguide.entity;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class File {


    private Long id;
    private String fileName;

	@Lob
    private byte[] Data;

}
