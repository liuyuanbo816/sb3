package zzjjcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zzjjcc.model.Job;
import zzjjcc.service.JobService;

import java.util.List;

@Controller
public class JobController {

    JobService jobServiceImpl;

    @Autowired
    public void setJobServiceImpl(JobService jobServiceImpl) {
        this.jobServiceImpl = jobServiceImpl;
    }

    @GetMapping("/getJobs")
    @ResponseBody
    public List<Job> getJobs() {
        return jobServiceImpl.getJobs();
    }

}
