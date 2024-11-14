package se.catchupa.jobfetcherapi.database;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")
public class JobEntity {

        public JobEntity() {}

        @Id
        @Column(name = "JOB_ID")
        private String jobId;

        @Column(name = "NAME")
        private String name;

        @Column(name = "COMPANY_NAME")
        private String companyName;

        @Column(name = "LOCATION")
        private String location;

        @Column(name = "POSTED")
        private String posted;

        @Column(name = "URL")
        private String url;

        @Column(name = "METADATA")
        private String metadata;

        // Getters and setters
        public String getJobId() {
                return jobId;
        }

        public void setJobId(String jobId) {
                this.jobId = jobId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCompanyName() {
                return companyName;
        }

        public void setCompanyName(String companyName) {
                this.companyName = companyName;
        }

        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }

        public String getPosted() {
                return posted;
        }

        public void setPosted(String posted) {
                this.posted = posted;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public String getMetadata() {
                return metadata;
        }

        public void setMetadata(String metadata) {
                this.metadata = metadata;
        }
}
