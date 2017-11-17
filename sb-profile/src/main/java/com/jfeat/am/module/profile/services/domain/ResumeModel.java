package com.jfeat.am.module.profile.services.domain;

import com.jfeat.am.module.profile.services.persistence.model.EduBackground;
import com.jfeat.am.module.profile.services.persistence.model.Resume;
import com.jfeat.am.module.profile.services.persistence.model.WorkExperience;

import java.util.List;

/**
 * Created by J4cob on 2017/11/9.
 */
public class ResumeModel extends Resume{
    List<EduBackground> eduBackgrounds;
    List<WorkExperience> workExperiences;

    public List<EduBackground> getEduBackgrounds() {
        return eduBackgrounds;
    }

    public void setEduBackgrounds(List<EduBackground> eduBackgrounds) {
        this.eduBackgrounds = eduBackgrounds;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }
}
