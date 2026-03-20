package livercdss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

@GetMapping("/")
public String home() {
    return "welcome";
}
@GetMapping("/input")
public String input(){
    return "input";
}

@PostMapping("/predict")
public String predict(
        @RequestParam double age,
        @RequestParam double tb,
        @RequestParam double db,
        @RequestParam double alp,
        @RequestParam double sgpt,
        @RequestParam double sgot,
        @RequestParam double tp,
        @RequestParam double alb,
        Model model) {

    try {

        ProcessBuilder pb = new ProcessBuilder(
                "python",
                "predict.py",
                String.valueOf(age),
                String.valueOf(tb),
                String.valueOf(db),
                String.valueOf(alp),
                String.valueOf(sgpt),
                String.valueOf(sgot),
                String.valueOf(tp),
                String.valueOf(alb)
        );

        pb.directory(new File("C:\\Users\\Lenovo\\eclipse-workspace\\Mini Project\\demo"));

        Process process = pb.start();

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(process.getInputStream())
                );

        String output = reader.readLine();

         String[] parts = output.split(",");

         String result = parts[0];
         String percentage = parts[1];

         model.addAttribute("prediction", result);
         model.addAttribute("percentage", percentage);  

    } catch (Exception e) {
        e.printStackTrace();
    }

    return "result";
}

}