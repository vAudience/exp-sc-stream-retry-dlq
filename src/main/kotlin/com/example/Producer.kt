package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Output
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.messaging.MessageChannel
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*


@SpringBootApplication
class Producer

fun main(args: Array<String>) {
    SpringApplication.run(Producer::class.java, *args)
}


@Service
@EnableBinding(Source::class)
class MyProducer {

    @InboundChannelAdapter(value = Source.OUTPUT)
    fun timerMessageSource(): String {
        val sdfDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val data = sdfDate.format(Date())
        println(data)
        return data
    }

}

interface Source {
    companion object {
        const val OUTPUT = "input"
    }

    @Output(Source.OUTPUT)
    fun output(): MessageChannel
}
