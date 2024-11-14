package se.catchupa.jobfetcherapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.catchupa.jobfetcherapi.service.JobDto;
import se.catchupa.jobfetcherapi.service.JobService;

import java.util.List;

import static se.catchupa.jobfetcherapi.JobMapper.toDto;

@RestController
@RequestMapping("/rest/v1/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobDto> getAllJobs() {
        return jobService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody JobRequest jobRequest) {
        JobValidator.validate(jobRequest);
        JobDto jobDto = toDto(jobRequest);
        jobService.save(jobDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable String jobId) {
        jobService.deleteById(jobId);
        return ResponseEntity.ok().build();
    }
}
