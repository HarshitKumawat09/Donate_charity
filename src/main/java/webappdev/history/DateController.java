package webappdev.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class DateController {

    private final DateService dateService;

    @Autowired
    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    @GetMapping("/date/all")
    public ResponseEntity<List<Date>> getAllDates() {
        List<Date> dates = dateService.getAllDates();
        return new ResponseEntity<>(dates, HttpStatus.OK);
    }

    public String findMostFrequentDate(List<Date> dates) {
        // Group the dates by their names and count occurrences
        Map<String, Long> dateCounts = dates.stream()
                .collect(Collectors.groupingBy(Date::getName, Collectors.counting()));

        // Find the max count
        Optional<Map.Entry<String, Long>> maxEntry = dateCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        // Check if maxEntry is present and return the corresponding date name
        return maxEntry.map(Map.Entry::getKey).orElse(" to be determined");
    }

    @PostMapping("date/top")
    @ResponseBody
    public ResponseEntity<?> getUserTop(@RequestBody Date date) {
        List<Date> dates = dateService.getDatesByUserId(date.getUid());
        String name = " " + findMostFrequentDate(dates);
        DateResponse response = new DateResponse();
        response.setData(name);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/date/post")
    public List<Date> getUserDates(@RequestBody Date date) {
        return dateService.getDatesByUserId(date.getUid());
    }

    @PostMapping("date/add")
    public ResponseEntity<Date> addDate(@RequestBody Date date) {
        Date savedDate = dateService.saveDate(date);
        return new ResponseEntity<>(savedDate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDate(@PathVariable Long id) {
        boolean result = dateService.deleteDateById(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}