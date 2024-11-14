# main.py

import requests
from html_parser import get_job_ids, parse_job_data

# URL to fetch job postings
linkedin_url = "https://www.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/search?keywords=Java&location=Nord%C3%B6stra%20G%C3%B6teborg%2C%20V%C3%A4stra%20G%C3%B6talands%20l%C3%A4n%2C%20Sverige&pageNum=0&start=25"
# URL to post jobs to your backend API
api_endpoint = "http://localhost:8080/rest/v1/jobs"

# Get job IDs from LinkedIn
job_ids = get_job_ids(linkedin_url)

print(f"Fetched {len(job_ids)} job ids")

# Process each job posting and send it as a POST request
for job_id in job_ids:
    job_post = parse_job_data(job_id)

    # Prepare data in the structure required by JobRequest
    job_request = {
        "jobId": job_post["job_id"],
        "name": job_post["title"],
        "companyName": job_post["company_name"],
        "location": job_post["location"],
        "posted": job_post["posted_date"],
        "url": job_post["url"],
        "metadata": job_post["metadata"]
    }

    # Make POST request to the API
    response = requests.post(api_endpoint, json=job_request)

    # Check response status
    if response.status_code == 200:
        print(f"{job_request['name']} successfully posted to the API.")
    else:
        print(f"Failed to post {job_request['name']} to the API. Status code: {response.status_code}")
