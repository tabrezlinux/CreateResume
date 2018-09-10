package com.example.tabrezahmad.createresume.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.webkit.JavascriptInterface;

import java.sql.Date;

@Entity
@ForeignKey(entity = BasicInfo.class,parentColumns = "uid",childColumns = "f_key",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE,deferred = false)
public class ProfessionalQualification {


        public static final String STATUS_PASSED = "passed";
        public static final String STATUS_PURSUING = "pursuing";
        public static final String MARKS_TYPE_GRADE = "grade";
        public static final String MARKS_TYPE_PERCENT = "percent";


        @PrimaryKey(autoGenerate = true)
        public Long uid;

        // uid of Basic_Info Entity (foreign key)
        @ColumnInfo(name = "f_key")
        public Long f_key;


        @ColumnInfo(name = "course")
        public String course;

        @ColumnInfo(name = "institute")
        public String institute;

        // PASSED or PASSING
        @ColumnInfo(name = "passing_status")
        public String passing_status;

        // STATUS is PASSED
        @ColumnInfo(name = "year")
        public Date year;

        // STATUS is PASSED
        @ColumnInfo(name = "marks")
        public Float marks;

        // STATUS is PASSED, type GRADE/PERCENTAGE
        @ColumnInfo(name = "marks_type")
        public String marks_type;

        @ColumnInfo(name="index_id")
        public int index_id;

        @JavascriptInterface
        public Long getUid() {
                return uid;
        }

        @JavascriptInterface
        public Long getF_key() {
                return f_key;
        }

        @JavascriptInterface
        public String getCourse() {
                return course;
        }

        @JavascriptInterface
        public String getInstitute() {
                return institute;
        }

        @JavascriptInterface
        public String getPassing_status() {
                return passing_status;
        }

        @JavascriptInterface
        public Date getYear() {
                return year;
        }

        @JavascriptInterface
        public Float getMarks() {
                return marks;
        }

        @JavascriptInterface
        public String getMarks_type() {
                return marks_type;
        }

        @JavascriptInterface
        public int getIndex_id() {
                return index_id;
        }
}
