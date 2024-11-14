package se.catchupa.jobfetcherapi.api;

public record JobRequest(
        String jobId,
        String name,
        String companyName,
        String location,
        String posted,
        String url,
        String metadata
) {
}
