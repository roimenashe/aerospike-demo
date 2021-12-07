package com.example.aerospikedemo.model

import org.springframework.data.aerospike.mapping.Document

@Document
class HelloWorld(val value: String) {

}