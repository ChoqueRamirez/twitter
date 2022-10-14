package com.willi.twitter.dto;

public class RetweetDTO {
    private Long targetUserId;
    private Long targetTwitId;

    public Long getTargetTwitId() {
        return targetTwitId;
    }

    public void setTargetTwitId(Long targetTwitId) {
        this.targetTwitId = targetTwitId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }
}
