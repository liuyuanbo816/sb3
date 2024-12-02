package zzjjcc.service.mariadb.hrdb;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import zzjjcc.mapper.mariadb.hrdb.JobMapper;

@Service
public class JobService {
    @Resource
    JobMapper jobMapper;
}
