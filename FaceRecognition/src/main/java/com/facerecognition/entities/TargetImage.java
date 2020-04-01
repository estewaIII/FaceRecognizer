package com.facerecognition.entities;

import com.amazonaws.services.rekognition.model.S3Object;

import java.util.Objects;

public class TargetImage {
    private Byte Bytes;
    private S3Object myS3Object;

    public TargetImage(){super();}

    public TargetImage(Byte bytes, S3Object myS3Object) {
        Bytes = bytes;
        this.myS3Object = myS3Object;
    }

    public Byte getBytes() {
        return Bytes;
    }

    public void setBytes(Byte bytes) {
        Bytes = bytes;
    }

    public S3Object getMyS3Object() {
        return myS3Object;
    }

    public void setMyS3Object(S3Object myS3Object) {
        this.myS3Object = myS3Object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetImage that = (TargetImage) o;
        return Objects.equals(Bytes, that.Bytes) &&
                Objects.equals(myS3Object, that.myS3Object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Bytes, myS3Object);
    }

    @Override
    public String toString() {
        return "TargetImage{" +
                "Bytes=" + Bytes +
                ", myS3Object=" + myS3Object +
                '}';
    }
}
