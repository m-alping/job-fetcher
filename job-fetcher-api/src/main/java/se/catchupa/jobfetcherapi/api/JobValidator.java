package se.catchupa.jobfetcherapi.api;

public class JobValidator {

    public static void validate(JobRequest jobRequest) {
        if (jobRequest == null) {
            throw new IllegalArgumentException("jobRequest");
        }

        if (jobRequest.jobId() == null) {
            throw new IllegalArgumentException("Invalid job request: Id is null");
        }

        if (jobRequest.name() == null) {
            throw new IllegalArgumentException("Invalid job request: Name is null");
        }
    }

}
