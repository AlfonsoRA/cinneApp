package cineapp;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
     
    private Logger logger = Logger.getLogger(TestController.class);
     
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView model = new ModelAndView("Hello");
         
        logger.info("This is an info log entry");
        logger.error("This is an error log entry");
         
        return model;
    }
}
