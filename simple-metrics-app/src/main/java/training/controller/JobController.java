package training.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.web.bind.annotation.*;
import training.jobs.Job;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class JobController {
    private List<Job> jobs = new LinkedList<>();
    private Counter listCounter;

    public JobController() {
        this.listCounter = Metrics.counter("list_jobs");
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody List<Job> getAll() {
        listCounter.increment();
        return jobs;
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    public void addJob(@RequestBody Job job) {
        this.jobs.add(job);
    }
}