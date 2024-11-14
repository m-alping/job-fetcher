# html_parser.py

import requests
from bs4 import BeautifulSoup
import json

def get_job_ids(url):
    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    # Extract job IDs from main job search page
    job_ids = [tag['data-entity-urn'].split(':')[-1] for tag in soup.find_all('div', {'class': 'job-search-card'})]
    return job_ids

def parse_job_data(job_id):
    job_url = f"https://www.linkedin.com/jobs-guest/jobs/api/jobPosting/{job_id}"
    job_response = requests.get(job_url)
    job_soup = BeautifulSoup(job_response.text, "html.parser")
    job_post = {"job_id": job_id}

    # Extract job title
    title_tag = job_soup.find("h2", {"class": "top-card-layout__title"})
    job_post["title"] = title_tag.text.strip() if title_tag else None

    if title_tag:
        print(f"Fetched job data for job with title: {title_tag.text.strip()}")
    else:
        print("No title found for this job.")

    # Extract company name
    company_tag = job_soup.find("a", {"class": "topcard__org-name-link topcard__flavor--black-link"})
    job_post["company_name"] = company_tag.text.strip() if company_tag else None

    # Extract job location
    location_tag = job_soup.find("span", {"class": "topcard__flavor topcard__flavor--bullet"})
    job_post["location"] = location_tag.text.strip() if location_tag else None

    # Extract posting date
    date_tag = job_soup.find("span", {"class": "posted-time-ago__text topcard__flavor--metadata"})
    job_post["posted_date"] = date_tag.text.strip() if date_tag else None

    # Extract job URL
    linkedin_tag = job_soup.find("a", href=lambda href: href and "linkedin.com/company" in href)
    job_post["url"] = linkedin_tag["href"] if linkedin_tag else None

    # Collect additional metadata
    metadata = {}
    description_tag = job_soup.find("div", {"class": "description__text description__text--rich"})
    if description_tag:
        metadata["description"] = description_tag.text.strip()

    # Extract criteria such as Seniority, Employment Type, Job Function, and Industry
    criteria_tags = job_soup.find_all("li", {"class": "description__job-criteria-item"})
    for criteria in criteria_tags:
        header = criteria.find("h3", {"class": "description__job-criteria-subheader"}).text.strip()
        value = criteria.find("span", {"class": "description__job-criteria-text"}).text.strip()
        metadata[header.lower().replace(" ", "_")] = value

    # Convert metadata dictionary to JSON string
    job_post["metadata"] = json.dumps(metadata)

    return job_post
