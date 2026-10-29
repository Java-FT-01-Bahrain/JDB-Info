package org.example.kafkademo.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.kafkademo.model.Author;
import org.example.kafkademo.model.Book;
import org.example.kafkademo.repository.AuthorRepository;
import org.example.kafkademo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumer {

}
