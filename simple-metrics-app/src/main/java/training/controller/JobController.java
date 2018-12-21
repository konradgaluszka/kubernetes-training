package training.controller;

import training.jobs.Job;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class JobController {
    private List<Job> jobs = new LinkedList<>();

    @RequestMapping(value = "/jobs", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody List<Job> getAll() {
        return jobs;
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    public void addJob(@RequestBody Job job) {
        this.jobs.add(job);
    }
}