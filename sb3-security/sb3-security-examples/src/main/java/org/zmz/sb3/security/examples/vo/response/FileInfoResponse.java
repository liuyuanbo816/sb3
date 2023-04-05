package org.zmz.sb3.security.examples.vo.response;

public class FileInfoResponse {
    private String path;

    public FileInfoResponse(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
