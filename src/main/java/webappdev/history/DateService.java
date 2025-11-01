package webappdev.history;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import org.springframework.stereotype.Service;


@Service
public class DateService {

    private final DateRepository dateRepository;

    @Autowired
    public DateService(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    public List<Date> getAllDates() {
        return dateRepository.findAll();
    }

    public Date getDateById(Long id) {
        Optional<Date> dateOptional = dateRepository.findById(id);
        return dateOptional.orElse(null);
    }

    public Date saveDate(Date date) {
        return dateRepository.save(date);
    }

    public boolean deleteDateById(Long id) {
        if (id == null) {
            return false;
        }
        dateRepository.deleteById(id);
        return true;
    }

    // You can add more methods as needed
    public List<Date> getDatesByUserId(Long userId) {
        return dateRepository.findDateByUserId(userId);
    }
}