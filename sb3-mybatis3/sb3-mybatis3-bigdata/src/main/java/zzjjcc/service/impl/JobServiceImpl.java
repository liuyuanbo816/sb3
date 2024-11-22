package zzjjcc.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import zzjjcc.mapper.maria.hrdb.JobMapper;
import zzjjcc.model.Job;
import zzjjcc.service.JobService;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Resource
    JobMapper jobMapper;

    @Override
    public List<Job> getJobs() {
        return jobMapper.getJobs();
    }
}
