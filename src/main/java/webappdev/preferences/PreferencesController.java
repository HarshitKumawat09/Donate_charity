package webappdev.preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api")
public class PreferencesController {
    private final PreferencesService preferencesService;

    @Autowired
    public PreferencesController(PreferencesService preferencesService) {
        this.preferencesService = preferencesService;

    }

    @PostMapping("/preferences/post") //GET
    @ResponseBody
    public ResponseEntity<?> getUserPreferences(@RequestBody PreferencesRequest request) {

        List<Preferences> causes = preferencesService.getPreferencesByUserId(request.getUserId());

        PreferencesResponse response = new PreferencesResponse(causes);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/preferences/update")
    @ResponseBody
    public ResponseEntity<?> updateUserPreferences(@RequestBody PreferencesRequest request) {
        System.out.println(request.getUserId());
        // delete current preferences
        List<Preferences> prefs = preferencesService.getPreferencesByUserId(request.getUserId());
        for (Preferences p : prefs) {
            preferencesService.deletePreferencesById(p.getId());
        }

        // save new preferences
        List<Preferences> causes = preferencesService.saveMultiple(request.getCauses(), request.getUserId());

        System.out.println("success");

        PreferencesResponse response = new PreferencesResponse(causes);
        return ResponseEntity.ok().body(response);
    }

}
