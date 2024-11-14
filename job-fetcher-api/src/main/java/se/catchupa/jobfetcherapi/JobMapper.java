package se.catchupa.jobfetcherapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.catchupa.jobfetcherapi.api.JobRequest;
import se.catchupa.jobfetcherapi.api.JobResponse;
import se.catchupa.jobfetcherapi.database.JobEntity;
import se.catchupa.jobfetcherapi.service.JobDto;

public class JobMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JobDto toDto(JobRequest request){
        return new JobDto(request.jobId(), request.name(), request.companyName(), request.location(), request.posted(), request.url(), request.metadata());
    }

    public static JobDto toDto(JobEntity entity){
        return new JobDto(entity.getJobId(), entity.getName(), entity.getCompanyName(), entity.getLocation(), entity.getPosted(), entity.getUrl(), entity.getMetadata());
    }

    public static JobEntity toEntity(JobDto jobDto) {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobId(jobDto.jobId());
        jobEntity.setName(jobDto.name());
        jobEntity.setCompanyName(jobDto.companyName());
        jobEntity.setLocation(jobDto.location());
        jobEntity.setPosted(jobDto.posted());
        jobEntity.setUrl(jobDto.url());
        jobEntity.setMetadata(jobDto.metadata());
        return jobEntity;
    }

    public static JobResponse toResponse(int status, JobDto dto){

        return new JobResponse(status, toJson(dto));
    }

    private static String toJson(Object o){
        try{
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse object to json", e);
        }
    }

}
