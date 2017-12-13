package com.jfeat.am.module.feedback.services.domain.model;

import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedbackImage;

import java.util.List;

/**
 * Created by vincent on 2017/8/27.
 */
public class TFeedbackModel extends TFeedback {

    List<TFeedbackImage> images;

    public List<TFeedbackImage> getImages() {
        return images;
    }

    public void setImages(List<TFeedbackImage> images) {
        this.images = images;
    }
}
