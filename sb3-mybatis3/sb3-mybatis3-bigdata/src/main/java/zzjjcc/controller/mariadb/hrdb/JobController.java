package zzjjcc.controller.mariadb.hrdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import zzjjcc.service.mariadb.hrdb.JobService;

@Controller
public class JobController {

    JobService jobService;

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

}
