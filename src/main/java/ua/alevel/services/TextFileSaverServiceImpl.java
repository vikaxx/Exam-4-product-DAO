package ua.alevel.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.alevel.dao.impl.CustomerOrderDaoImpl;
import ua.alevel.datasource.DataSource;

import java.io.*;

@Service
public class TextFileSaverServiceImpl implements FileSaverService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerOrderDaoImpl.class);

    @Override
    public boolean saveToFile(String fileName, String data) {
        File file = new File(fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             PrintWriter printWriter = new PrintWriter(fileOutputStream)) {
            printWriter.write(data);
            printWriter.flush();
            return true;
        } catch (IOException e) {
            LOG.error("IO error: ", e);
        }
        return false;
    }
}
