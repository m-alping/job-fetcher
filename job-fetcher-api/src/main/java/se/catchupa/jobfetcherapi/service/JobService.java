package se.catchupa.jobfetcherapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.catchupa.jobfetcherapi.JobMapper;
import se.catchupa.jobfetcherapi.database.JobEntity;
import se.catchupa.jobfetcherapi.database.JobRepository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static se.catchupa.jobfetcherapi.JobMapper.toEntity;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobDto> findAll() {
        List<JobEntity> entities = jobRepository.findAll();
        return entities.stream().map(JobMapper::toDto).toList();
    }

    public void save(JobDto job) {
        JobEntity entity = toEntity(job);
        jobRepository.save(entity);
    }

    public void deleteById(String jobId) {
        jobRepository.deleteById(jobId);
    }
}

