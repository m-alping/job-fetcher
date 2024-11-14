package se.catchupa.jobfetcherapi.service;

public record JobDto(
        String jobId,
        String name,
        String companyName,
        String location,
        String posted,
        String url,
        String metadata
) {
}
