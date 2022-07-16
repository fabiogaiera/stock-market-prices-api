package com.fabiogaiera.tickerservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);


    //Heavy task
    public void storageCSVContent(byte[] csvContent) {

        Runnable runnableTask = () -> {

            String str = new String(csvContent);

        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runnableTask);
        executorService.shutdown();

    }

}