package org.zmz.sb3.newfeatures.j17.record;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record Student(Integer sid, String name, String email) {

    public static final Logger LOG= LoggerFactory.getLogger(Student.class);

    public static void main(String[] args) {
        Student student = new Student(1, "bobi", "bobi@qq.com");
        LOG.info("{}",student);
    }
}
