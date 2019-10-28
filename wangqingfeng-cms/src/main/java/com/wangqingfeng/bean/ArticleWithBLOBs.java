package com.wangqingfeng.bean;

import java.io.Serializable;

public class ArticleWithBLOBs extends Article implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String content;

    private String summary;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

	@Override
	public String toString() {
		return "ArticleWithBLOBs [content=" + content + ", summary=" + summary + ", getContent()=" + getContent()
				+ ", getContentType()=" + getContentType() + "]";
	}

	
    
}