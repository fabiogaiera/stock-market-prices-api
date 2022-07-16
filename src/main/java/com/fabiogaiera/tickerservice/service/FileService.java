package com.fabiogaiera.tickerservice.service;

import com.fabiogaiera.tickerservice.entity.HistoricalPrice;
import com.fabiogaiera.tickerservice.repository.HistoricalPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    private HistoricalPriceRepository historicalPriceRepository;

    //Heavy task
    public void storageCSVContent(byte[] csvContent) {

        Runnable runnableTask = () -> {

            String csvContentString = new String(csvContent);
            // Remove first line
            String csvContentStringWithFirstLineRemoved = csvContentString.substring(csvContentString.indexOf('\n') + 1);

            csvContentStringWithFirstLineRemoved.lines().forEach(line -> {

                String[] splittedLine = line.split(",");
                storageCSVLine(splittedLine);

            });

        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runnableTask);
        executorService.shutdown();

    }

    private void storageCSVLine(String[] splittedLine) {

        LocalDate date = LocalDate.parse(splittedLine[0]);
        BigDecimal open = new BigDecimal(splittedLine[1]);
        BigDecimal high = new BigDecimal(splittedLine[2]);
        BigDecimal low = new BigDecimal(splittedLine[3]);
        BigDecimal close = new BigDecimal(splittedLine[4]);
        BigDecimal adjustedClose = new BigDecimal(splittedLine[5]);
        Integer volume = Integer.parseInt(splittedLine[6]);
        //TODO associate ticker
        HistoricalPrice historicalPrice = new HistoricalPrice(date, open, high, low, close, adjustedClose, volume, null);
        historicalPriceRepository.save(historicalPrice);

    }

    @Autowired
    public void setHistoricalPriceRepository(HistoricalPriceRepository historicalPriceRepository) {
        this.historicalPriceRepository = historicalPriceRepository;
    }

}